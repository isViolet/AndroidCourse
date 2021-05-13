package com.example.intentevents;

import android.app.Activity;
import android.os.Bundle;

public class Second extends Activity {
    static String action = "com.example.intentevents.action.Second";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
    }
}
