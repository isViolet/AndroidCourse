package com.example.final_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ViewPager viewPager;
    private ImageButton index_button;
    private ImageButton star_button;
    private ImageButton message_button;
    private ImageButton user_button;

    private TextView textView1,textView2,textView3,textView4;

    private LinearLayout layout1, layout2, layout3, layout4;
    private MyFragmentPagerAdapter mAdapter;
    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static String USER_NAME;

    private List<LinearLayout> layouts;
    private List<TextView> textViewList;
    private List<ImageButton> imageButtonList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        Bundle bundle = new Bundle();
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("data");
        USER_NAME = bundle.getString("user");
        init();
    }

    private void init() {

        mAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager());

        index_button = findViewById(R.id.index);
        star_button = findViewById(R.id.star);
        message_button = findViewById(R.id.message);
        user_button = findViewById(R.id.user);
        imageButtonList = new ArrayList<>();
        imageButtonList.add(index_button);
        imageButtonList.add(star_button);
        imageButtonList.add(message_button);
        imageButtonList.add(user_button);

        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layouts = new ArrayList<>();
        layouts.add(layout1);
        layouts.add(layout2);
        layouts.add(layout3);
        layouts.add(layout4);

        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        textView3 = findViewById(R.id.tv3);
        textView4 = findViewById(R.id.tv4);
        textViewList = new ArrayList<>();
        textViewList.add(textView1);
        textViewList.add(textView2);
        textViewList.add(textView3);
        textViewList.add(textView4);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);

        layout1.setBackgroundColor(getResources().getColor(R.color.selected));
        layout2.setBackgroundColor(Color.rgb(255, 251, 255));
        layout3.setBackgroundColor(Color.rgb(255, 251, 255));
        layout4.setBackgroundColor(Color.rgb(255, 251, 252));

        index_button.setOnClickListener(this);
        star_button.setOnClickListener(this);
        message_button.setOnClickListener(this);
        user_button.setOnClickListener(this);

        initViewBg(0);

        viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout1:
            case R.id.index:
            case R.id.tv1:
                viewPager.setCurrentItem(0);
                initViewBg(0);
                break;
            case R.id.layout2:
            case R.id.star:
            case R.id.tv2:
                viewPager.setCurrentItem(1);
                initViewBg(1);
                break;
            case R.id.layout3:
            case R.id.message:
            case R.id.tv3:
                viewPager.setCurrentItem(2);
                initViewBg(2);
                break;
            case R.id.layout4:
            case R.id.user:
            case R.id.tv4:
                viewPager.setCurrentItem(3);
                initViewBg(3);
                break;
        }
    }

    private void initViewBg(int pos){
        for (int i = 0; i < layouts.size(); i++){
            layouts.get(i).setBackgroundColor(Color.rgb(255, 251, 255));
            textViewList.get(i).setTextColor(getResources().getColor(R.color.black));
            if (i == pos){
                layouts.get(i).setBackgroundColor(getResources().getColor(R.color.selected));
                textViewList.get(i).setTextColor((getResources().getColor(R.color.text_selected)));
                initImageButton(i);
            }
        }
    }

    private void initImageButton(int po){
        if (po == 0){
            imageButtonList.get(0).setBackground(getResources().getDrawable(R.drawable.index_));
            imageButtonList.get(1).setBackground(getResources().getDrawable(R.drawable.star));
            imageButtonList.get(2).setBackground(getResources().getDrawable(R.drawable.message));
            imageButtonList.get(3).setBackground(getResources().getDrawable(R.drawable.user));
        }else if (po == 1){
            imageButtonList.get(0).setBackground(getResources().getDrawable(R.drawable.index));
            imageButtonList.get(1).setBackground(getResources().getDrawable(R.drawable.star_));
            imageButtonList.get(2).setBackground(getResources().getDrawable(R.drawable.message));
            imageButtonList.get(3).setBackground(getResources().getDrawable(R.drawable.user));
        }else if (po == 2){
            imageButtonList.get(0).setBackground(getResources().getDrawable(R.drawable.index));
            imageButtonList.get(1).setBackground(getResources().getDrawable(R.drawable.star));
            imageButtonList.get(2).setBackground(getResources().getDrawable(R.drawable.message_));
            imageButtonList.get(3).setBackground(getResources().getDrawable(R.drawable.user));
        }else {
            imageButtonList.get(0).setBackground(getResources().getDrawable(R.drawable.index));
            imageButtonList.get(1).setBackground(getResources().getDrawable(R.drawable.star));
            imageButtonList.get(2).setBackground(getResources().getDrawable(R.drawable.message));
            imageButtonList.get(3).setBackground(getResources().getDrawable(R.drawable.user_));
        }
    }

}
