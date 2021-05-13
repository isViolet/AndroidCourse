package com.example.final_exam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyFragment1 extends Fragment implements View.OnClickListener{

    private ImageView math,chinese,english,physical,politics,chemistry,biology,geography;
    private Intent intent;
    private Uri content_url;
    private int[] imageIds;
    private String[] titles;
    private ArrayList<ImageView> images;
    private ArrayList<View> dots;
    private TextView title;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private int oldPosition;
    private int currentItem;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_index, container, false);
        init(view);
        return view;

    }

    private void init(View index) {
        math=(ImageView)index.findViewById(R.id.math);
        math.setOnClickListener(this);
        chinese=(ImageView)index.findViewById(R.id.chinese);
        chinese.setOnClickListener(this);
        english=(ImageView)index.findViewById(R.id.english);
        english.setOnClickListener(this);
        physical=(ImageView)index.findViewById(R.id.physical);
        physical.setOnClickListener(this);
        politics=(ImageView)index.findViewById(R.id.politics);
        politics.setOnClickListener(this);
        chemistry=(ImageView)index.findViewById(R.id.chemistry);
        chemistry.setOnClickListener(this);
        biology=(ImageView)index.findViewById(R.id.biology);
        biology.setOnClickListener(this);
        geography=(ImageView)index.findViewById(R.id.geography);
        geography.setOnClickListener(this);

        //图片ID
        imageIds = new int[]{
                R.drawable.aa,
                R.drawable.bb,
                R.drawable.cc,
                R.drawable.dd,
                R.drawable.ee,
        };
        //图片标题
        titles = new String[]{
                "人人必会的思维导图",
                "12节表达力提升课",
                "幽默让你备受欢迎",
                "恋爱安全 避坑指南",
                "大学生涯早规划",
        };
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i =0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的点
        dots = new ArrayList<View>();
        dots.add(index.findViewById(R.id.dot_0));
        dots.add(index.findViewById(R.id.dot_1));
        dots.add(index.findViewById(R.id.dot_2));
        dots.add(index.findViewById(R.id.dot_3));
        dots.add(index.findViewById(R.id.dot_4));

        title = (TextView)index.findViewById(R.id.image_title);
        title.setText(titles[0]);
        mViewPager = (ViewPager)index.findViewById(R.id.vp);
        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                title.setText(titles[position]);
                initDot(position);
                oldPosition = position;
                currentItem = position;
            }

            private void initDot(int pos) {
                View view = dots.get(pos);
                for (int i = 0; i < dots.size(); i++){
                    dots.get(i).setBackgroundResource(R.drawable.dot_normal);
                    if (dots.get(i) == view){
                        view.setBackgroundResource(R.drawable.dot_focused);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private class ViewPagerAdapter extends PagerAdapter {
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
            view.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stu
            view.addView(images.get(position));
            return images.get(position);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔2秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 5, 4, TimeUnit.SECONDS);
    }
    //切换图片
    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            currentItem = (currentItem +1) % imageIds.length;
            //更新界面
            handler.obtainMessage().sendToTarget();
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            // TODO Auto-generated method stub
            //设置当前页面
            mViewPager.setCurrentItem(currentItem);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.math:
                intent = new Intent();
                //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse("https://www.bilibili.com/video/BV187411o7bt?from=search&seid=12464167324356412898");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.chinese:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url1 = Uri.parse("https://www.bilibili.com/video/BV1rE411G782?from=search&seid=2048800610436310746");
                intent.setData(content_url1);
                startActivity(intent);
                break;
            case R.id.english:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse("https://www.bilibili.com/video/BV1LE411p7CW?from=search&seid=17437507175758579572");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.physical:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse("https://www.bilibili.com/video/BV1E7411o7Bt?from=search&seid=10487067805764740394");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.politics:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse("https://www.bilibili.com/video/BV1kE411x7QP?from=search&seid=3227927967949862420");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.chemistry:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse("https://www.bilibili.com/video/BV18E411p7q4?from=search&seid=10822388522135950785");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.biology:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse("https://www.bilibili.com/video/BV16E411G7Z8?from=search&seid=8375457259170364850");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.geography:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse("https://www.bilibili.com/video/BV1jE411T7pS?from=search&seid=17297651821644958329");
                intent.setData(content_url);
                startActivity(intent);
                break;
        }
    }



}
