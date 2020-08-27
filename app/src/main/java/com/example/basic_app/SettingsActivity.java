package com.example.basic_app;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

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
                    //((FirebaseInstanceIDService)FirebaseInstanceIDService.context).onNewToken("True");
                } else {
                    Toast.makeText(getApplicationContext(), "Push 비활성화", Toast.LENGTH_SHORT).show();
                    textView.setText("FCM 수신시 알림을 표시하지 않습니다.");
                   // ((FirebaseInstanceIDService)FirebaseInstanceIDService.context).onNewToken("False");

                }
            }
        });
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