package com.example.test7;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import android.annotation.SuppressLint;
import android.widget.Toast;

@SuppressLint("AppCompatCustomView")

public class newButton extends Button {

    public newButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(),"回调",Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
