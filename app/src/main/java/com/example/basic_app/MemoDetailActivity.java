package com.example.basic_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

public class MemoDetailActivity extends MainActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);

        TextView tvTitle = findViewById(R.id.txtMemo);
        TextView tvTime = findViewById(R.id.txtDate);
        TextView tvName = findViewById(R.id.txtMemo2);
        TextView tvcontents = findViewById(R.id.tv_contents);
        ImageView ivContents = findViewById(R.id.iv_contents);

        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        Intent intent = getIntent();
        if (intent != null) {
            Memo memo = (Memo) intent.getSerializableExtra("memo");

            if (memo != null) {
                Log.i("FAS", "메모: " + memo.toString());

                tvTitle.setText(memo.title);
                tvTime.setText(memo.getTime());
                tvName.setText(memo.name);
                tvcontents.setText(memo.contents);

                Glide.with(this)
                        .asBitmap()
                        .load(memo.imageUrl) // 이미지 URL
                        .apply(new RequestOptions()
                                .priority(Priority.HIGH)
                                .fitCenter()
                                .error(R.drawable.android)
                                .fallback(R.drawable.android))
                        .into(ivContents);
            }
        }
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