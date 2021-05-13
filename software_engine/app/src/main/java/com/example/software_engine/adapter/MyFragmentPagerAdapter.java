package com.example.software_engine.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.software_engine.activity.MainActivity;
import com.example.software_engine.fragment.IndexFragment;
import com.example.software_engine.fragment.StarFragment;
import com.example.software_engine.fragment.MessageFragment;
import com.example.software_engine.fragment.UserFragment;
import com.example.software_engine.fragment.tempFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 5;
    private IndexFragment indexFragment = null;
    private StarFragment starFragment = null;
    private MessageFragment messageFragment = null;
    private UserFragment userFragment = null;
    private com.example.software_engine.fragment.tempFragment tempFragment = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        indexFragment = new IndexFragment();
        starFragment = new StarFragment();
        messageFragment = new MessageFragment();
        userFragment = new UserFragment();
        tempFragment = new tempFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = indexFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = tempFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = starFragment;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = messageFragment;
                break;
            case MainActivity.PAGE_FIVE:
                fragment = userFragment;
                break;
        }
        return fragment;
    }

}

