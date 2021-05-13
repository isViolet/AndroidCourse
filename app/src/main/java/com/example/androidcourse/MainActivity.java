package com.example.androidcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();//实验一
//        initView2();//实验二
            initView3();
    }

    private void initView3(){
        String[] names = {"白起","曹操","韩信","李白"};
        String[] desc = {"古代将军","一代枭雄","青龙印","青丘"};
        ListView listView = findViewById(R.id.listView);
        ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
        for (int i = 0; i < names.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("names", names[i]);
            map.put("desc", desc[i]);
            arrayList.add(map);
        }
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(arrayList, this);
        listView.setAdapter(myBaseAdapter);
    }

    //基于simpleAdapter
    private void initView2() {
        String[] names = {"白起","曹操","韩信","李白"};
        String[] desc = {"古代将军","一代枭雄","青龙印","青丘"};
        ListView listView = findViewById(R.id.listView);
        ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
        for (int i = 0; i < names.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("names", names[i]);
            map.put("desc", desc[i]);
            arrayList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,arrayList,R.layout.item_list,new String[]{"names","desc"},new int[]{R.id.tvName,R.id.tvDesc});
        listView.setAdapter(simpleAdapter);
    }

    //实验一：基于arrayAdapter
    private void initView() {
        String[] arr = {"s","s"};
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arr);
        listView.setAdapter(arrayAdapter);
    }

}










