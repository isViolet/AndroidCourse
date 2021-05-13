package com.example.test15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private EditText mes;
    private Button send_btn;
    private String ip = "172.20.10.3";  //服务器端IP
    private int port= 2000;//瑞口号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void SendMes(String ip, int port, String mes) throws UnknownHostException, IOException {
        try {
            Socket socket= null;
            socket=new Socket(ip, port);
            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(mes);
            writer.flush();
            writer.close();
            socket.close();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void init(){
        mes = (EditText)findViewById(R.id.mes);
        send_btn = (Button)findViewById(R.id.send);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final String string= mes.getText().toString();
                    if (!TextUtils.isEmpty(string)){
                        new Thread(new Runnable(){
                            @Override
                            public void run() {
                                try {
                                    SendMes (ip,port,string);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }else {
                        Toast.makeText(MainActivity.this,"请先输入内容",Toast.LENGTH_SHORT).show();
                        mes.requestFocus();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"sc",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
