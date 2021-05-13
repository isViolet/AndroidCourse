package com.example.final_exam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class SetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.me_setting);

        LinearLayout linearLayout = findViewById(R.id.exit);
        LinearLayout linearLayout1 = findViewById(R.id.chat);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplication().onTerminate();
                finish();
            }
        });
    }

}
