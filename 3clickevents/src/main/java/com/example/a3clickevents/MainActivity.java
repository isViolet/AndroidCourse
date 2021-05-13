package com.example.a3clickevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = findViewById(R.id.bt2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.bt3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"该按钮使用了匿名内部类的方法",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void button1(View view) {
        Toast.makeText(this,"该按钮使用了onclick方法",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt2:
                Toast.makeText(this,"该按钮使用了implements View.OnClickListener方法",Toast.LENGTH_SHORT).show();
        }
    }
}
