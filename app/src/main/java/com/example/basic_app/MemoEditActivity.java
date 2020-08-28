package com.example.basic_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basic_app.DB.DBHelper;

public class MemoEditActivity extends AppCompatActivity {

    private EditText etTitle, etWriter, etText;
    private Button btnSave;
    private Button btnCancel;
    private Memo memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_edit);

        etTitle = findViewById(R.id.etTitle);
        etWriter = findViewById(R.id.etWriter);
        etText = findViewById(R.id.etText);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        Intent intent = getIntent();
        if (intent != null) {
            memo = (Memo) intent.getSerializableExtra("memo");

            if (memo != null) {
                Log.i("FAS", "메모: " + memo.toString());

                etTitle.setText(memo.title);
                etWriter.setText(memo.name);
                etText.setText(memo.contents);
            }
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String name = etWriter.getText().toString();
                String contents = etText.getText().toString();

                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(name) || TextUtils.isEmpty(contents)) {
                    Toast.makeText(v.getContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                memo.title = title;
                memo.contents = contents;
                memo.name = name;

                DBHelper.getInstance(v.getContext()).updateMemo(memo);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClicked();
            }
        });
    }

    public void onCancelClicked() {
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}