package com.example.software_engine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.software_engine.R;
import com.example.software_engine.activity.MessageChatActivity;

import java.util.ArrayList;
import java.util.Map;

public class MyBaseAdapter extends BaseAdapter {
    ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
    Context context;
    public MyBaseAdapter(ArrayList<Map<String, Object>> arrayList, Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_message_list,null);
            viewHolder.ivImage = view.findViewById(R.id.avatar);
            viewHolder.tvDesc = view.findViewById(R.id.message_desc);
            viewHolder.tvName = view.findViewById(R.id.message_name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.ivImage.setBackgroundResource(R.drawable.user);
        viewHolder.tvName.setText(arrayList.get(i).get("names").toString());
        viewHolder.tvDesc.setText(arrayList.get(i).get("desc").toString());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MessageChatActivity.class).putExtra("name",arrayList.get(i).get("names").toString()));
            }
        });
        return view;
    }
    static class ViewHolder{
        ImageView ivImage;
        TextView tvDesc;
        TextView tvName;
    }
}
