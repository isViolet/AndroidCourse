package com.example.software_engine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.software_engine.R;
import com.example.software_engine.adapter.ChatMessage;
import com.example.software_engine.adapter.ChatMessageAdapter;
import com.example.software_engine.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageChatActivity extends Activity {

    private List<ChatMessage> list;
    private ListView chatListView;
    private TextView chatTop;
    private EditText chat_input;
    private Button chat_send;
    private ChatMessageAdapter chatAdapter;
    private ChatMessage chatMessage = null;
    private Intent intent;
    public static String USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.message_main);
        initView();
        initListener();
        initData();
    }

    // 1.初始试图
    private void initView() {

        intent = getIntent();
        // 1.初始化
        chatListView = (ListView) findViewById(R.id.chat_listview);
        chat_input = (EditText) findViewById(R.id.chat_input_message);
        chat_send = (Button) findViewById(R.id.chat_send);
        chatTop = findViewById(R.id.chat_top_name);

    }

    // 2.设置监听事件
    private void initListener() {
        chat_send.setOnClickListener(onClickListener);
    }

    // 3.初始化数据
    private void initData() {

        USER_NAME = intent.getStringExtra("name");

        chatTop.setText(USER_NAME);

        list = new ArrayList<ChatMessage>();
        list.add(new ChatMessage("我已经通过了你的好友验证请求，现在可以聊天了!", ChatMessage.Type.INCOUNT, new Date()));
        chatAdapter = new ChatMessageAdapter(list);
        chatListView.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
    }

    // 4.发送消息聊天
    private void chat() {
        // 1.判断是否输入内容
        final String send_message = chat_input.getText().toString().trim();
        if (TextUtils.isEmpty(send_message)) {
            Toast.makeText(this, "对不起，您还未发送任何消息",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // 2.自己输入的内容也是一条记录，记录刷新
        ChatMessage sendChatMessage = new ChatMessage();
        sendChatMessage.setMessage(send_message);
        sendChatMessage.setData(new Date());
        sendChatMessage.setType(ChatMessage.Type.OUTCOUNT);
        list.add(sendChatMessage);
        chatAdapter.notifyDataSetChanged();
        chat_input.setText("");
        new Thread() {
            public void run() {
                ChatMessage chat = HttpUtils.sendMessage(send_message);
                Message message = new Message();
                message.what = 0x1;
                message.obj = chat;
                handler.sendMessage(message);
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x1) {
                if (msg.obj != null) {
                    chatMessage = (ChatMessage) msg.obj;
                }
                // 添加数据到list中，更新数据
                list.add(chatMessage);
                chatAdapter.notifyDataSetChanged();
            }
        };
    };

    // 点击事件监听
    OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.chat_send:
                    chat();
                    break;
            }
        }
    };
}

