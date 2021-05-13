package com.example.software_engine.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.software_engine.R;
import com.example.software_engine.adapter.MyBaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageFragment extends Fragment {

    public MessageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message,container,false);
        TextView textView = view.findViewById(R.id.title);
        textView.setText("消息");
        init(view);
        return view;
    }

    private void init(View view) {
        ListView mesList = view.findViewById(R.id.mesList);
        String[] names = {"白起","曹操","韩信","李白"};
        String[] desc = {"古代将军","一代枭雄","青龙印","青丘"};
        int[] avatar = {R.drawable.user};
        ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
        for (int i = 0; i < names.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("names", names[i]);
            map.put("desc", desc[i]);
            map.put("avatar",avatar[0]);
            arrayList.add(map);
        }
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(arrayList, getContext());
        mesList.setAdapter(myBaseAdapter);
    }
}
