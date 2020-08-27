package com.example.basic_app.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.basic_app.Memo;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ksj.db"; // 데이터베이스명
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_MEMO = "memo";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CONTENTS = "contents";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE = "image_url";
    private static final String COLUMN_TIME = "time";

    private static volatile DBHelper mInstance;

    public static DBHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBHelper.class) {
                if (mInstance == null) {
                    mInstance = new DBHelper(context);
                }
            }
        }
        return mInstance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME_MEMO + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_CONTENTS + " TEXT, "
                + COLUMN_NAME + " TEXT , "
                + COLUMN_IMAGE + " TEXT , "
                + COLUMN_TIME + " INTEGER "
                + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertMemo(Memo memo) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, memo.title);
        values.put(COLUMN_CONTENTS, memo.contents);
        values.put(COLUMN_NAME, memo.name);
        values.put(COLUMN_IMAGE, memo.imageUrl);
        values.put(COLUMN_TIME, System.currentTimeMillis());

        return getWritableDatabase().insert(TABLE_NAME_MEMO, null, values);
    }

    public long updateMemo(Memo memo) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, memo.title);
        values.put(COLUMN_CONTENTS, memo.contents);
        values.put(COLUMN_NAME, memo.name);
        values.put(COLUMN_IMAGE, memo.imageUrl);
        // values.put(COLUMN_TIME, System.currentTimeMillis());

        Log.d("FAS", "업데이트 메모: " + memo.toString());

        return getWritableDatabase().update(TABLE_NAME_MEMO, values,
                COLUMN_ID + "=" + memo.id, null);
    }

    public void deleteMemo(Memo memo) {
        getWritableDatabase().delete(TABLE_NAME_MEMO, COLUMN_ID + "=" + memo.id, null);
    }

    public ArrayList<Memo> getMemos() {
        ArrayList<Memo> items = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME_MEMO + " ORDER BY " + COLUMN_TIME + " DESC";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Memo memo = new Memo();
            memo.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            memo.title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            memo.contents = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENTS));
            memo.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            memo.imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            memo.time = cursor.getLong(cursor.getColumnIndex(COLUMN_TIME));

            items.add(memo);
        }

        if (!cursor.isClosed())
            cursor.close();

        return items;
    }

    public long getTotalCount() {
        String sql = "SELECT COUNT(*) FROM " + TABLE_NAME_MEMO;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();

        long count = cursor.getLong(0);
        if (cursor.isClosed())
            cursor.close();

        return count;
    }
}