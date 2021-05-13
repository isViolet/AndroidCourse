package com.example.test7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_content);
//        button = findViewById(R.id.bt);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "" + editText.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void Aysnc(View view) {
        startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
    }

//    public void getResource(View view) {
//        Toast.makeText(this, "" + editText.getText().toString(),Toast.LENGTH_SHORT).show();
//    }

}
