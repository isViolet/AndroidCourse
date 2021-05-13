package com.example.test13_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void save(View view) {
        Uri uri= Uri.parse("content://sms/");
        ContentResolver contentResolver=getContentResolver();
        Cursor cursor = contentResolver.query(uri,new String[]{"address","date","type","body"},null,null,null);
        List<SmsInfo> list=new ArrayList<SmsInfo>();
        while (cursor.moveToNext()){
            String address=cursor.getString(0);
            long date=cursor.getLong(1);
            int type=cursor.getInt(2);
            String body=cursor. getString(3);
            SmsInfo smsInfo=new SmsInfo(address,date,type,body);
            list.add(smsInfo);
        }
        cursor.close();
        Sms_Xml.beifen_sms(list,MainActivity.this);
    }
}
