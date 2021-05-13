package com.example.test8;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class setTitleFragment extends Fragment {
    private View view;
    private String[] title;
    private String[][] contenta;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.title_layout,container,false);
        tongxin tongxin = (tongxin) getActivity();
        title = tongxin.get();
        contenta = tongxin.getSettingText();

        if (view!=null){
            init();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setContentFragment setContentFragment = (setContentFragment) ((tongxin) getActivity()).getSupportFragmentManager().findFragmentById(R.id.setcontent);
                setContentFragment.setText(contenta[position]);
            }
        });
        return view;
    }

    private void init() {
        listView = (ListView)view.findViewById(R.id.titlelist);
        if (title!=null){
            listView.setAdapter(new MyAdapter());
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return title[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getActivity(),R.layout.title_item_layout,null);
            TextView titleText = convertView.findViewById(R.id.titles);
            titleText.setText(title[position]);
            return convertView;
        }
    }
}
