package com.example.basic_app;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.basic_app.DB.DBHelper;
import com.google.firebase.messaging.RemoteMessage;

public class SettingsActivity extends AppCompatActivity {

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        mActivity = this;

        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        Switch switchButton = findViewById(R.id.switch1);
        final TextView textView = findViewById(R.id.pushtext);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Push 활성화", Toast.LENGTH_SHORT).show();
                    textView.setText("FCM 수신시 알림을 표시합니다.");
                } else {
                    Toast.makeText(getApplicationContext(), "Push 비활성화", Toast.LENGTH_SHORT).show();
                    textView.setText("FCM 수신시 알림을 표시하지 않습니다.");
                    NotificationManager notificationManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.cancel(0);
                }
            }
        });
        TextView totaltext = findViewById(R.id.totalmemo);
        totaltext.append(String.format("%s개", DBHelper.getInstance(this).getTotalCount()));
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