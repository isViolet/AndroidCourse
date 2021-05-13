package com.example.final_exam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends Activity {
    private String phonenum="";
    String content[]={};
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.order_layout);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phonenum=bundle.getString("phone");
        list=new ArrayList<Map<String, Object>>();

        String[] name = {"《茶花女》",
                "《哈姆雷特》",
                "《日瓦戈医生》",
                "《冰心散文集》",
                "《麦田里的守望者》",
                "《本草纲目》",
                "《呐喊》",
                "《红与黑》"};

        int[] num = {102,
                575,
                454,
                1354,
                201,
                1254,
                244,
                246};

        int[] pri = {45,
                30,
                35,
                30,
                40,
                40,
                45,
                35};

        String[] state = {"在售",
                "在售",
                "在售",
                "在售/折扣",
                "在售",
                "在售",
                "在售/折扣",
                "在售/折扣"};

        for (int i = 0; i < pri.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("bookname",name[i]);
            map.put("booknum", num[i]);
            map.put("bookprice", pri[i]);
            map.put("ztai", state[i]);
            list.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(OrderActivity.this,
                list,R.layout.order1_layout,new String[]{"bookname","booknum","bookprice","ztai"},
                new int[]{R.id.bookname,R.id.booknum,R.id.bookprice,R.id.ztai});
        ListView listView=(ListView)findViewById(R.id.list_order);
        listView.setAdapter(adapter);
    }

}

