package com.example.androidcourse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class MyBaseAdapter extends BaseAdapter {
    ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
    Context context;
    MyBaseAdapter(ArrayList<Map<String,Object>> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_baseadapter,null);
            viewHolder.ivImage = view.findViewById(R.id.ivName);
            viewHolder.tvDesc = view.findViewById(R.id.tvDesc);
            viewHolder.button = view.findViewById(R.id.button);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.ivImage.setImageResource(R.drawable.ic_launcher_background);
        viewHolder.tvDesc.setText(arrayList.get(i).get("names").toString());
        viewHolder.button.setText(arrayList.get(i).get("desc").toString());
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"点击按钮",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    static class ViewHolder{
        ImageView ivImage;
        TextView tvDesc;
        Button button;
    }
}
