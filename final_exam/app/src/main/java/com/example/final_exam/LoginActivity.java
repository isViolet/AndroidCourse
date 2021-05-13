package com.example.final_exam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity implements View.OnClickListener {

    //获取布局文件当中的各个组件
    public Button login_btn;
    public EditText phone_edit,paswd_edit;
    public CheckBox reme_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        init();
    }
    //组件初始化
    private void init(){
        phone_edit=(EditText)findViewById(R.id.login_phonenumber);
        paswd_edit=(EditText)findViewById(R.id.login_paswd);
        reme_box=(CheckBox)findViewById(R.id.reme_paswd);
        login_btn=(Button)findViewById(R.id.login);
        login_btn.setOnClickListener(this);

        SharedPreferences preferences=getSharedPreferences("user_mes", Activity.MODE_PRIVATE);
        if(preferences!=null){
            phone_edit.setText(preferences.getString("phone", ""));
            paswd_edit.setText(preferences.getString("paswd", ""));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //登录
            case R.id.login:
                if (reme_box.isChecked()) {
                    //保存数据
                    SharedPreferences preferences=getSharedPreferences("user_mes", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("phone", phone_edit.getText().toString().equals("")?"":phone_edit.getText().toString());
                    editor.putString("paswd", paswd_edit.getText().toString().equals("")?"":paswd_edit.getText().toString());
                    //将数据提交
                    editor.commit();
                }
                Bundle bundle = new Bundle();
                bundle.putString("user",phone_edit.getText().toString().trim());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("data",bundle);
                startActivity(intent);
                finish();
            default:
                break;
        }

    }
}

