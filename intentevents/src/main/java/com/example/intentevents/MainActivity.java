package com.example.intentevents;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(MainActivity.this, Second.class);
//        startActivity(intent);
//        Intent intent = new Intent();
//        intent.setAction("12345");
//        startActivity(intent);
        startActivity(new Intent(Second.action));
    }

}