package com.example.software_engine.mysql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MysqlHelper {

    public boolean isExistName(SQLiteOpenHelper myOpenHelper, String table_name, String field_name,String webUrl) {

        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        boolean isExist = false;
        try {
            sqLiteDatabase = myOpenHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query(table_name, null, field_name + "=?", new String[]{webUrl}, null, null, null);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (cursor != null && cursor.getCount() > 0) {
                isExist = true;
                cursor.close();
            }else {
                isExist = false;
            }
        }
        return isExist;
        //查询所有
//        Cursor cursor = sqLiteDatabase.query("Account_info",null,null,null,null,null,null);
    }

    public boolean insertWebPath(SQLiteOpenHelper userStarHelper, String table_name, String webUrl){
        SQLiteDatabase sqLiteDatabase = userStarHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("webUrl",webUrl);
        long insert = sqLiteDatabase.insert(table_name,null,values);
        sqLiteDatabase.close();
        return insert > 0;
    }

    public boolean insertUserPath(SQLiteOpenHelper userStarHelper, String table_name, String webUrl){
        SQLiteDatabase sqLiteDatabase = userStarHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userVideoUrl",webUrl);
        long insert = sqLiteDatabase.insert(table_name,null,values);
        sqLiteDatabase.close();
        return insert > 0;
    }

    public boolean deleteWebPath(SQLiteOpenHelper userStarHelper, String table_name, String webUrl){
        SQLiteDatabase sqLiteDatabase = userStarHelper.getWritableDatabase();
        //返回值int代表删除的行数
        int delete = sqLiteDatabase.delete(table_name,"webUrl=?",new String[]{webUrl});
        sqLiteDatabase.close();
        return delete > 0;
    }

    public List<String> getWebUrlList(SQLiteOpenHelper userStarHelper, String table_name){
        List<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = userStarHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(table_name,null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                String webUrl = cursor.getString(1);
                list.add(webUrl);
            }
        }
        return list;
    }
}
