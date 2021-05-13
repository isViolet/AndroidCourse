package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private EditText et_user;
    private EditText et_pwd;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2;
    private CheckBox cb1, cb2, cb3;
    private Spinner spinner;

    private String str_phone;
    private String str_pwd;
    private String str_sex;
    private String str_hobby;
    private String str_city;
    private String str_result;
    private String[] city = {"北京", "上海", "南京", "成都"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        str_phone = "";
        str_city = "";
        str_pwd = "";
        str_sex = "男生";
        str_hobby = "";
        str_result = "";
    }

    private void initView() {
        et_user = findViewById(R.id.et_username);
        et_pwd = findViewById(R.id.et_password);
        radioGroup = findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(this);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        cb1 = findViewById(R.id.checkbox1);
        cb2 = findViewById(R.id.checkbox2);
        cb3 = findViewById(R.id.checkbox3);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,city);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str_city = city[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void login(View view) {
        str_phone = et_user.getText().toString();
        str_pwd = et_pwd.getText().toString();
        if (cb1.isChecked()){
            str_hobby += cb1.getText().toString();
        }
        if (cb2.isChecked()){
            str_hobby += cb2.getText().toString();
        }
        if (cb3.isChecked()){
            str_hobby += cb3.getText().toString();
        }
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("phone", str_phone);
        bundle.putString("pwd", str_pwd);
        bundle.putString("sex", str_sex);
        bundle.putString("hobby", str_hobby);
        bundle.putString("city", str_city);
        intent.putExtra("data",bundle);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.radio1){
            str_sex = "男生";
        }else {
            str_sex = "女生";
        }
    }
}
