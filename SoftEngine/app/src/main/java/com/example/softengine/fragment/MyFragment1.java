package com.example.softengine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


import com.example.softengine.R;
import com.example.softengine.activity.IndexActivity;
import com.example.softengine.activity.MainActivity;


public class MyFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_index_entry, container, false);
        Button button = view.findViewById(R.id.bt_video);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent((MainActivity)getActivity(), IndexActivity.class));
            }
        });
        return view;
    }

}
