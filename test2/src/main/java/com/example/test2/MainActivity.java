package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("main_process", "onCreate");
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("user_name", "001");
                startActivity(intent);
            }
        });
    }

            @Override
            protected void onStart() {
                super.onStart();
                Log.i("main_process", "onStart");
            }

            @Override
            protected void onResume() {
                super.onResume();
                Log.i("main_process", "onResume");
            }

            @Override
            protected void onPause() {
                super.onPause();
                Log.i("main_process", "onPause");
            }

            @Override
            protected void onStop() {
                super.onStop();
                Log.i("main_process", "onStop");
            }

            @Override
            protected void onDestroy() {
                super.onDestroy();
                Log.i("main_process", "onDestroy");
            }

}
