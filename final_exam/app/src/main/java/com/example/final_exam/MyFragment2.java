package com.example.final_exam;


import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class MyFragment2 extends Fragment {

    private ListView listView;

    public MyFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_star,container,false);
        listView = view.findViewById(R.id.select_teacher);
        init();
        return view;
    }

    private void init() {

        int[] pic = {R.drawable.aa,
                R.drawable.bb,
                R.drawable.cc,
                R.drawable.dd,
                R.drawable.ee,
                R.drawable.ff,
                R.drawable.gg,
                R.drawable.hh};

        String[] sen = {"人人必会的思维导图",
                "12节表达力提升课",
                "幽默让你备受欢迎",
                "恋爱安全 避坑指南",
                "大学生涯早规划",
                "职场十大基本功",
                "有用又有趣的经济学思维",
                "博弈论入门20讲"};

        ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
        for (int i = 0; i < pic.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("names", pic[i]);
            map.put("desc", sen[i]);
            arrayList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),arrayList,R.layout.select_teacher_item,new String[]{"names","desc"},new int[]{R.id.teach_pic,R.id.teach_sen});
        listView.setAdapter(simpleAdapter);
    }

}
