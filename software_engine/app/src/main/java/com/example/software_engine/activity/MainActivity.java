package com.example.software_engine.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.software_engine.adapter.MyFragmentPagerAdapter;
import com.example.software_engine.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private ImageButton index_button;
    private ImageButton star_button;
    private ImageButton message_button;
    private ImageButton user_button;

    public static String USER_NAME;

    private RelativeLayout layout1, layout2, layout3, layout4,temp;
    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        mAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager());

        index_button = findViewById(R.id.index);
        star_button = findViewById(R.id.star);
        message_button = findViewById(R.id.message);
        user_button = findViewById(R.id.user);

        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);
        layout4=findViewById(R.id.layout4);
        temp = findViewById(R.id.temp);

        layout1.setBackgroundColor(Color.rgb(152, 251, 152));
        layout2.setBackgroundColor(Color.rgb(255, 251, 255));
        layout3.setBackgroundColor(Color.rgb(255, 251, 255));
        layout4.setBackgroundColor(Color.rgb(255, 251, 252));

        index_button.setOnClickListener(this);
        star_button.setOnClickListener(this);
        message_button.setOnClickListener(this);
        user_button.setOnClickListener(this);

        viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.index:
                viewPager.setCurrentItem(0);
                layout1.setBackgroundColor(Color.rgb(152, 251, 152));
                layout2.setBackgroundColor(Color.rgb(255, 251, 255));
                layout3.setBackgroundColor(Color.rgb(255, 251, 255));
                layout4.setBackgroundColor(Color.rgb(255, 251, 252));
                break;
            case R.id.star:
                viewPager.setCurrentItem(2);
                layout2.setBackgroundColor(Color.rgb(152, 251, 152));
                layout1.setBackgroundColor(Color.rgb(255, 251, 255));
                layout3.setBackgroundColor(Color.rgb(255, 251, 255));
                layout4.setBackgroundColor(Color.rgb(255, 251, 252));
                break;
            case R.id.message:
                viewPager.setCurrentItem(3);
                layout3.setBackgroundColor(Color.rgb(152, 251, 152));
                layout2.setBackgroundColor(Color.rgb(255, 251, 255));
                layout1.setBackgroundColor(Color.rgb(255, 251, 255));
                layout4.setBackgroundColor(Color.rgb(255, 251, 252));
                break;
            case R.id.user:
                viewPager.setCurrentItem(4);
                layout4.setBackgroundColor(Color.rgb(152, 251, 152));
                layout2.setBackgroundColor(Color.rgb(255, 251, 255));
                layout3.setBackgroundColor(Color.rgb(255, 251, 255));
                layout1.setBackgroundColor(Color.rgb(255, 251, 252));
                break;
        }
    }

}
