package com.example.software_engine.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.software_engine.R;

public class PlantActivity extends Activity implements CalendarView.OnDateChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.me_plant);
        CalendarView calend = (CalendarView) findViewById(R.id.calendview);
        calend.setOnDateChangeListener(this);
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        Toast.makeText(PlantActivity.this, month+"", Toast.LENGTH_SHORT).show();
    }

}
