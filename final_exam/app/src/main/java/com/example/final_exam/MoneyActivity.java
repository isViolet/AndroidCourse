package com.example.final_exam;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MoneyActivity extends Activity {
    private String phonenum="";
    private Boolean isSucceed=false;
    private String[] message = {};
    private TextView zong,yue,jifen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.money_layout);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phonenum=bundle.getString("phone");
        init();
    }
    //组件初始化
    private void init(){
        zong=(TextView)findViewById(R.id.zong);
        yue=(TextView)findViewById(R.id.yue);
        jifen=(TextView)findViewById(R.id.jifen);

        zong.setText("我的总额：40545577823723元");
        yue.setText("我的余额：204572882754154元");
        jifen.setText("我的积分：5242761");
    }

}

