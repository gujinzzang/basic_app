package com.example.basic_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class MemoDetailActivity extends MainActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_item);

        TextView tvTitle = findViewById(R.id.txtMemo);
        TextView tvTime = findViewById(R.id.txtDate);
        TextView tvName = findViewById(R.id.txtMemo2);
//        ImageView ivContents = findViewById(R.id.iv_contents);
//        TextView tvContents = findViewById(R.id.tv_contents);

        Intent intent = getIntent();
        if (intent != null) {
            Memo memo = (Memo) intent.getSerializableExtra("memo");

            if (memo != null) {
                Log.i("FAS", "메모: " + memo.toString());

                tvTitle.setText(memo.title);
                tvTime.setText(memo.getTime());
                tvName.setText(memo.name);
//                tvContents.setText(memo.contents);

//                Glide.with(this)
//                        .asBitmap()
//                        .load(memo.imageUrl) // 이미지 URL
//                        .apply(new RequestOptions()
//                                .priority(Priority.HIGH)
//                                .fitCenter()
//                                .error(R.mipmap.ic_launcher)
//                                .fallback(R.mipmap.ic_launcher))
//                        .into(ivContents);
            }
        }
    }
}