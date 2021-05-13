package com.example.test4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        textView = findViewById(R.id.result);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        textView.setText("phone:" + bundle.get("phone") + "\n" +
                "pwd:" + bundle.get("pwd") + "\n" +
                "sex:" + bundle.get("sex") + "\n" +
                "hobby:" + bundle.get("hobby") + "\n" +
                "city:" + bundle.get("city") + "\n");
    }

    public void toast(View view) {
        Toast.makeText(this, "你使用了Toast", Toast.LENGTH_SHORT).show();
    }

    public void AlertDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("你使用了AlertDialog")
                .setMessage("你使用了AlertDialog")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

    }
}
