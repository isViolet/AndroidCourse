package com.example.studyxml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText et_name;
    EditText et_pwd;
    Button button;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = findViewById(R.id.et_user);
        et_pwd = findViewById(R.id.et_pwd);
        button = findViewById(R.id.bt);
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
            verifyStoragePermissions(this);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String pwd = et_pwd.getText().toString();
                Person person = new Person(name,pwd);
                try {
                    XmlSerializer xmlSerializer = Xml.newSerializer();
                    File f = new File(Environment.getExternalStorageDirectory(),"person.xml");
                    FileOutputStream fileOutputStream = new FileOutputStream(f);
                    xmlSerializer.setOutput(fileOutputStream,"utf-8");
                    xmlSerializer.startDocument("utf-8",true);
                    xmlSerializer.startTag(null,"persons");
                    xmlSerializer.startTag(null,"person");
                    xmlSerializer.startTag(null,"name");
                    xmlSerializer.text(person.getName());
                    xmlSerializer.endTag(null,"name");
                    xmlSerializer.startTag(null,"pwd");
                    xmlSerializer.text(person.getPwd());
                    xmlSerializer.endTag(null,"pwd");
                    xmlSerializer.endTag(null,"person");
                    xmlSerializer.endTag(null,"persons");
                    xmlSerializer.endDocument();
                    xmlSerializer.flush();
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Xml初始化成功", Toast.LENGTH_SHORT).show();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

}
