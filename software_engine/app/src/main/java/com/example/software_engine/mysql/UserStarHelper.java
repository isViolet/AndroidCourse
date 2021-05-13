package com.example.software_engine.mysql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class UserStarHelper extends SQLiteOpenHelper {

    private String user_name;
    Context context;

    public UserStarHelper(Context context, String user_name) {
        super(context, "video.db", null, 1);
        this.user_name = user_name;
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table " + user_name + "(id integer primary key autoincrement, webUrl text)";
        Toast.makeText(context,"sac",Toast.LENGTH_SHORT).show();
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
