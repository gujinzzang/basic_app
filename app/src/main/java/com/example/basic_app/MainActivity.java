package com.example.basic_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.basic_app.DB.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMemo;
    private TextView tvEmpty;
    public Activity mActivity;

    DrawerLayout mDrawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_navi);

        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        //actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        //actionBar.setHomeAsUpIndicator(R.drawable.btn_menu); //뒤로가기 버튼 이미지 지정
        rvMemo = findViewById(R.id.rv_memo);
        tvEmpty = findViewById(R.id.tv_empty);

        Drawer();
        FloatingActionButton fab = findViewById(R.id.fab);
        ImageButton setbtn = findViewById(R.id.btn_set);
        ImageButton menubtn = findViewById(R.id.btn_menu);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //onAddClicked();
//                //Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
//                //startActivity(intent);
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, MemoAddActivity.class));
            }
        });

        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);

            }
        });

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else mDrawerLayout.closeDrawer(GravityCompat.END);
            }
        });
    }

    private void Drawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.nav_home){
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("http://www.fasol.co.kr/"));
//                    startActivity(intent);

                    Intent intent=new Intent(MainActivity.this,webActivity.class);
                    startActivity(intent);
                }
                else if(id == R.id.nav_gallery){
                    Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });
    }
    private MemoAdapter memoAdapter;
    public void refreshList() {
        if (memoAdapter == null) {
            memoAdapter = new MemoAdapter(this);
            rvMemo.setAdapter(memoAdapter);
        }
        memoAdapter.setItems(DBHelper.getInstance(this).getMemos());
        tvEmpty.setVisibility(memoAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

//    public void onAddClicked() {
//        Intent intent = new Intent(this, MemoAddActivity.class);
//        startActivity(intent);
//    }

//    TextView tvTitle = findViewById(R.id.txtMemo);
//    TextView tvTime = findViewById(R.id.txtDate);
//    TextView tvName = findViewById(R.id.txtMemo2);
//    ImageView ivContents = findViewById(R.id.iv_contents);
//    TextView tvContents = findViewById(R.id.tv_contents);

//    Intent intent = getIntent();
//        if (intent != null) {
//        Memo memo = (Memo) intent.getSerializableExtra("memo");
//
//        if (memo != null) {
//            Log.i("FAS", "메모: " + memo.toString());
//
//            tvTitle.setText(memo.title);
//            tvTime.setText(memo.getTime());
//            tvName.setText(memo.name);
//            tvContents.setText(memo.contents);
//
//            Glide.with(this)
//                    .asBitmap()
//                    .load(memo.imageUrl) // 이미지 URL
//                    .apply(new RequestOptions()
//                            .priority(Priority.HIGH)
//                            .fitCenter()
//                            .error(R.mipmap.ic_launcher)
//                            .fallback(R.mipmap.ic_launcher))
//                    .into(ivContents);
//        }
//    }
}