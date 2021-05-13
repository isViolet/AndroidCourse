package com.example.software_engine.mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserVideoHelper extends SQLiteOpenHelper {

    private String user_name;

    public UserVideoHelper(Context context, String user_name) {
        super(context, "video.db", null, 1);
        this.user_name = user_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table " + user_name + "video" + "(id integer primary key autoincrement, userVideoUrl text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}

