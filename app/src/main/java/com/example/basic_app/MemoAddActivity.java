package com.example.basic_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.basic_app.DB.DBHelper;

public class MemoAddActivity extends MainActivity {

    private EditText etTitle;
    private EditText etName;
    private EditText etContents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_edit);

        etTitle = findViewById(R.id.etTitle);
        etName = findViewById(R.id.etWriter);
        etContents = findViewById(R.id.etText);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String name = etName.getText().toString();
                String contents = etContents.getText().toString();

                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(name) || TextUtils.isEmpty(contents)) {
                    Toast.makeText(v.getContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Memo memo = new Memo();
                memo.title = title;
                memo.contents = contents;
                memo.name = name;
                memo.imageUrl = Memo.generateImageUrl();

                DBHelper.getInstance(v.getContext()).insertMemo(memo);
                finish();
            }
        });
    }
}