package com.example.softengine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.softengine.adapter.MyAdapter;
import com.example.softengine.R;
import com.example.softengine.adapter.MyFragmentPagerAdapter;
import com.example.softengine.widget.VerticalViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private MyAdapter myAdapter;
    private List<View> lists;
    private ImageButton index_button;
    private ImageButton star_button;
    private ImageButton message_button;
    private ImageButton user_button;

    private LinearLayout layout1, layout2, layout3, layout4;
    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        mAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager());

        index_button = findViewById(R.id.index);
        star_button = findViewById(R.id.star);
        message_button = findViewById(R.id.message);
        user_button = findViewById(R.id.user);

        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);

        layout1.setBackgroundColor(Color.rgb(152, 251, 152));
        layout2.setBackgroundColor(Color.rgb(255, 251, 255));
        layout3.setBackgroundColor(Color.rgb(255, 251, 255));
        layout4.setBackgroundColor(Color.rgb(255, 251, 252));

        index_button.setOnClickListener(this);
        star_button.setOnClickListener(this);
        message_button.setOnClickListener(this);
        user_button.setOnClickListener(this);

        lists = new ArrayList<>();
        // 加载对应的布局文件
        View index=getLayoutInflater().inflate(R.layout.activity_index, null);
        View star=getLayoutInflater().inflate(R.layout.activity_star, null);
        View message=getLayoutInflater().inflate(R.layout.activity_message, null);
        View user=getLayoutInflater().inflate(R.layout.activity_user, null);

        lists.add(index);
        lists.add(star);
        lists.add(message);
        lists.add(user);

        Toast.makeText(this, ""+lists.size(), Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(lists);
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
                viewPager.setCurrentItem(1);
                layout2.setBackgroundColor(Color.rgb(152, 251, 152));
                layout1.setBackgroundColor(Color.rgb(255, 251, 255));
                layout3.setBackgroundColor(Color.rgb(255, 251, 255));
                layout4.setBackgroundColor(Color.rgb(255, 251, 252));
                break;
            case R.id.message:
                viewPager.setCurrentItem(2);
                layout3.setBackgroundColor(Color.rgb(152, 251, 152));
                layout2.setBackgroundColor(Color.rgb(255, 251, 255));
                layout1.setBackgroundColor(Color.rgb(255, 251, 255));
                layout4.setBackgroundColor(Color.rgb(255, 251, 252));
                break;
            case R.id.user:
                viewPager.setCurrentItem(3);
                layout4.setBackgroundColor(Color.rgb(152, 251, 152));
                layout2.setBackgroundColor(Color.rgb(255, 251, 255));
                layout3.setBackgroundColor(Color.rgb(255, 251, 255));
                layout1.setBackgroundColor(Color.rgb(255, 251, 252));
                break;
        }
    }

}
