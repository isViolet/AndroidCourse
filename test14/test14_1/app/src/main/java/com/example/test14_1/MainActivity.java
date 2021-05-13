package com.example.test14_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.media.audiofx.AudioEffect.SUCCESS;
import static android.speech.tts.TextToSpeech.ERROR;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    ImageView imageView;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        editText = findViewById(R.id.et);
        button = findViewById(R.id.bt);
        imageView = findViewById(R.id.iv);
    }



    public void get(View view) {
        if (editText.getText().toString().isEmpty())
            return;
        path = editText.getText().toString().trim();
        new Thread(){
            private HttpURLConnection conn;
            private URL url;
            private Bitmap bitmap;
            public void run(){
//                getImageByHttpClient(path);
                getImageByHttpURLConnection(conn);
            }
        }.start();
    }

    private void getImageByHttpURLConnection(HttpURLConnection conn) {
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            int state = conn.getResponseCode();
            if (state == 200) {
                InputStream in = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                Message msg = new Message();
                msg.what = SUCCESS;
                msg.obj = bitmap;
                handler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = ERROR;
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = ERROR;
            handler.sendMessage(msg);
        }

//    private void getImageByHttpClient(String path){
//        HttpClient client = new DefaultHttpClient();
//        HttpGet get= new HttpGet(path);
//        try{
//            HttpResponse response= client.execute(get);
//            if(response.getStatusLine().getStatusCode()==200){
//                HttpEntity entity=response.getEntity();
//                InputStream in= entity.getContent();
//                Bitmap bitmap = BitmapFactory.decodeStream(in);
//                Message msg= new Message();
//                msg.what=SUCCESS;
//                msg.obj=bitmap;
//                handler.sendMessage(msg);
//            }else {
//                Message msg=new Message();
//                msg.what= ERROR;
//                handler.sendMessage(msg);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }


    }
    Handler handler=new Handler(){
        public void
        handleMessage(android.os .Message msg){
            if (msg. what==SUCCESS){
                Bitmap bitmap=(Bitmap )msg. obj;
                imageView.setImageBitmap(bitmap);
            }else if (msg. what==ERROR){
                Toast.makeText(MainActivity. this, "显示图片错误" , Toast .LENGTH_SHORT). show();
            }
        }
    };
}
