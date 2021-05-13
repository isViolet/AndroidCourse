package com.example.final_exam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class MyFragment3 extends Fragment implements View.OnClickListener{

    LinearLayout layout1,layout2,layout3,layout4;
    private Intent intent;

    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        layout1 = view.findViewById(R.id.mes_ll_1);
        layout2 = view.findViewById(R.id.mes_ll_2);
        layout3 = view.findViewById(R.id.mes_ll_3);
        layout4 = view.findViewById(R.id.mes_ll_4);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mes_ll_1:
                Toast.makeText(getContext(), "预约老师成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mes_ll_2:
                showInputDialog();
                break;
            case R.id.mes_ll_3:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url1 = Uri.parse("http://bbs.iecnu.com/forum-99-1.html\n");
                intent.setData(content_url1);
                startActivity(intent);
                break;
            case R.id.mes_ll_4:
                Toast.makeText(getContext(), "暂无优惠信息，请留意最新活动时间", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showInputDialog() {

        final EditText editText = new EditText(getContext());
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(getContext());
        inputDialog.setTitle("我要提建议！").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                "您的建议发送成功！",
                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
