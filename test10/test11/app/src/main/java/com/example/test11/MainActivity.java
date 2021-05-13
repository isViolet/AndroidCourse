package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText username;
    EditText paswd;
    Button register,show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        username = findViewById(R.id.username);
        paswd = findViewById(R.id.paswd);
        register = findViewById(R.id.register);
        show = findViewById(R.id.show);
        register.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                String name = username.getText().toString();
                String pwd = paswd.getText().toString();
                boolean flag = save_userMes(this,name,pwd);
                if (flag){
                    Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.show:
                Map<String, String> user = getUserMes(this);
                if (user!=null){
                    String name1 = user.get("username");
                    String pwd1 = user.get("paswd");
                    Toast.makeText(this,"用户名是：" + name1 + "\n" + "密码是：" + pwd1,Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private Map<String, String> getUserMes(Context context) {
        Map<String, String> user = new HashMap<String, String>();
        SharedPreferences preferences = context.getSharedPreferences("my_pre",MODE_PRIVATE);
        String name = preferences.getString("username",null);
        String pwd = preferences.getString("paswd",null);
        user.put("username",name);
        user.put("paswd",pwd);
        return user;
    }

    private boolean save_userMes(Context context, String name, String pwd){
        SharedPreferences preferences = context.getSharedPreferences("my_pre",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",name);
        editor.putString("paswd",pwd);
        editor.commit();
        return true;
    }
}
