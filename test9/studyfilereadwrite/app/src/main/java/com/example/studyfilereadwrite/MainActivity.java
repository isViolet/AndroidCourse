package com.example.studyfilereadwrite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private EditText editText;
    private String environment;
    private File sd_path;
    private String file_name = "test.txt";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_input);
        textView = findViewById(R.id.tvShow);
        environment = Environment.getExternalStorageState();
        sd_path = Environment.getExternalStorageDirectory();
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
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
        }
        verifyStoragePermissions(this);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
        Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
        // We don't have permission so prompt the user
        ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
        REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:

                if (!Environment.MEDIA_MOUNTED.equals(environment)){
                    Toast.makeText(this,"没有SD卡",Toast.LENGTH_SHORT).show();

                    return;
                }

                File file1 = new File(sd_path,"test.txt");
                String str = editText.getText().toString();

                try {

                    FileOutputStream out = new FileOutputStream(file1);
                    Log.i("adad","wadawd");
                    OutputStreamWriter outputStream = new OutputStreamWriter(out);

                    outputStream.write(str);
                    outputStream.flush();

                    outputStream.close();
                    out.close();

                    editText.setText("");
                    Toast.makeText(this,"写入成功",Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.bt2:
                if (!Environment.MEDIA_MOUNTED.equals(environment)){
                    Toast.makeText(this,"没有SD卡",Toast.LENGTH_SHORT).show();
                    return;
                }
                File file2 = new File(sd_path,file_name);
                try {
                    FileInputStream in = new FileInputStream(file2);
                    InputStreamReader reader = new InputStreamReader(in);
                    char[] input = new char[in.available()];
                    reader.read(input);
                    reader.close();
                    in.close();
                    textView.setText("" + new String(input));
                    Toast.makeText(this,"读入成功",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.getStackTrace();
                }
                break;
            case R.id.bt3:
                String myFile = "myFile.txt";
                try {
                    FileOutputStream fos = openFileOutput(myFile,MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write(editText.getText().toString());
                    Toast.makeText(this,"写入成功",Toast.LENGTH_SHORT).show();
                    osw.close();
                    fos.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case R.id.bt4:
                try {
                    FileInputStream in = openFileInput("myFile.txt");
                    InputStreamReader reader = new InputStreamReader(in);
                    char[] input = new char[in.available()];
                    reader.read(input);
                    reader.close();
                    in.close();
                    textView.setText("" + new String(input));
                    Toast.makeText(this,"读入成功",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.getStackTrace();
                }
                break;
        }
    }

}
