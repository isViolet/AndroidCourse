package com.example.softengine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softengine.R;
import com.example.softengine.activity.IndexActivity;
import com.example.softengine.activity.MainActivity;
import com.example.softengine.adapter.ListVideoAdapter;

import java.util.List;

import butterknife.BindView;

public class Fragment1 extends Fragment {

    @BindView(R.id.rv_page2)
    RecyclerView rvPage2;
    private List<String> urlList;
    private ListVideoAdapter videoAdapter;
    private PagerSnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    private int currentPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_index, container, false);

        return view;
    }
}
