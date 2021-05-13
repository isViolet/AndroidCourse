package com.example.test8;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class setContentFragment extends Fragment {
    private View view;
    private TextView textView1, textView2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_layout,container,false);
        if (view!=null){
            init();
        }
        setText(((tongxin)getActivity()).getSettingText()[0]);
        return view;
    }

    private void init() {
        textView1 = view.findViewById(R.id.show_title);
        textView2 = view.findViewById(R.id.show_content);
    }

    public void setText(String[] text){
        textView1.setText(text[0]);
        textView2.setText(text[1]);
    }
}
