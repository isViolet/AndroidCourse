package com.example.test8;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class tongxin extends FragmentActivity {
    private String title[] = {"标题一","标题二","标题三"};
    private String settingText[][] = {{"标题一","标题一的内容"},{"标题二","标题二的内容"},{"标题三","标题三的内容"}};

    public String[] get(){
        return title;
    }

    public String[][] getSettingText(){
        return settingText;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);

        setTitleFragment setTitleFragment = new setTitleFragment();
        setContentFragment setContentFragment = new setContentFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.settitle,setTitleFragment);
        fragmentTransaction.replace(R.id.setcontent,setContentFragment);
        fragmentTransaction.commit();
    }
}
