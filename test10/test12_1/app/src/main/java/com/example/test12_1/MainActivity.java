package com.example.test12_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    user_Database user_database = new user_Database(this);
    ListView listView;
    List<userinfo> list;
    private EditText user_name;
    private EditText pasd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);
        //定义一个集合用于存在listview
        list = new ArrayList<userinfo>();
        user_name = findViewById(R.id.username);
        pasd = findViewById(R.id.pwd);
    }

    public void click1(View v){
        SQLiteDatabase sqLiteDatabase = user_database.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (user_name.getText().toString().trim().isEmpty()||pasd.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        values.put("username",user_name.getText().toString().trim());
        values.put("password",pasd.getText().toString().trim());
        long insert = sqLiteDatabase.insert("user",null,values);
        sqLiteDatabase.close();
        isOK(insert);
    }

    public void click2(View view){
        SQLiteDatabase sqLiteDatabase = user_database.getWritableDatabase();
        //返回值int代表删除的行数
        if (user_name.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        int delete = sqLiteDatabase.delete("user","username=?",new String[]{user_name.getText().toString().trim()});
        sqLiteDatabase.close();
//        Toast.makeText(this,"删除了"+delete+"行", Toast.LENGTH_SHORT).show();
        isOK(delete);
    }

    public void click3(View view){
        SQLiteDatabase sqLiteDatabase = user_database.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (user_name.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        values.put("password",pasd.getText().toString().trim());
        int upgrade = sqLiteDatabase.update("user",values,"username=?",new String[]{user_name.getText().toString().trim()});
//        Toast.makeText(this,"更新了"+upgrade+"行", Toast.LENGTH_SHORT).show();
        isOK(upgrade);
    }

    public void click4(View view){
        SQLiteDatabase sqLiteDatabase = user_database.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.query("info",null,"name=?",new String[]{"wangwu"},null,null,null);
        //查询所有
        Cursor cursor = sqLiteDatabase.query("user",null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0){
            ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
            ListView listView = findViewById(R.id.lv);
            while(cursor.moveToNext()){
                String name = cursor.getString(1);
                String pwd = cursor.getString(2);
                Map<String,Object> map = new HashMap<>();
                map.put("username", name);
                map.put("password", pwd);
                arrayList.add(map);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this,arrayList,R.layout.item_list,new String[]{"username","password"},new int[]{R.id.tvName,R.id.tvDesc});
            listView.setAdapter(simpleAdapter);
        }
    }
    private void isOK(long values){
        if (values>0){
            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        }
    }
}
