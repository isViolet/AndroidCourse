package com.example.software_engine.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.software_engine.R;
import com.example.software_engine.activity.LoginInterfaceActivity;
import com.example.software_engine.activity.MainActivity;
import com.example.software_engine.activity.PlantActivity;
import com.example.software_engine.activity.VideoActivity;
import com.example.software_engine.mysql.MysqlHelper;
import com.example.software_engine.mysql.UserVideoHelper;

import java.net.HttpURLConnection;
import java.net.URL;

public class UserFragment extends Fragment implements View.OnClickListener {

    private TextView tv_video, tv_plant, tv_sets, tv_add, user_name;

    public UserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user,container,false);
        TextView textView = view.findViewById(R.id.title);
        textView.setText("个人中心");
        init(view);
        return view;
    }

    private void init(View view) {

        tv_video = view.findViewById(R.id.video);
        tv_plant = view.findViewById(R.id.plant);
        tv_sets = view.findViewById(R.id.sets);
        tv_add = view.findViewById(R.id.addVideo);
        user_name = view.findViewById(R.id.user_name);

        tv_video.setOnClickListener(this);
        tv_sets.setOnClickListener(this);
        tv_plant.setOnClickListener(this);
        tv_add.setOnClickListener(this);

        user_name.setText("用户名：" + MainActivity.USER_NAME);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video:
                startActivity(new Intent(getContext(), VideoActivity.class));
                break;
            case R.id.plant:
                startActivity(new Intent(getContext(), PlantActivity.class));
                break;
            case R.id.sets:
                getActivity().finish();
                startActivity(new Intent(getContext(), LoginInterfaceActivity.class));
                break;
            case R.id.addVideo:

                final EditText editText = new EditText(getContext());
                AlertDialog.Builder inputDialog =
                        new AlertDialog.Builder(getContext());
                inputDialog.setTitle("上传视频").setView(editText);
                inputDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getContext(),
//                                        "添加视频信息成功"+editText.getText().toString(),
//                                        Toast.LENGTH_SHORT).show();
                                addVideo(editText);
                            }
                        }).show();
        }
    }

    private void addVideo(EditText editText){
        new Thread(new Runnable(){
            @Override
            public void run() {
                boolean flag = checkUrl(editText.getText().toString().trim(),5000);
                if (flag){
                    MysqlHelper mysqlHelper = new MysqlHelper();
                    String url = editText.getText().toString().trim();
                    if (!url.endsWith(".mp4")){
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "该视频必须是mp4格式文件", Toast.LENGTH_LONG).show();
                            }
                        });
                        return;
                    }
                    UserVideoHelper userVideoHelper = new UserVideoHelper(getContext(),MainActivity.USER_NAME);
                    if (!mysqlHelper.isExistName(userVideoHelper,MainActivity.USER_NAME + "video","userVideoUrl",url)){
                        mysqlHelper.insertUserPath(userVideoHelper,MainActivity.USER_NAME + "video",url);
                    }else {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "该视频已存在", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }else {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "视频网址无效", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    private Boolean checkUrl(String address,int waitMilliSecond)
    {
        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(waitMilliSecond);
            conn.setReadTimeout(waitMilliSecond);
            try {
                conn.connect();
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            int code = conn.getResponseCode();
            if ((code >= 100) && (code < 400)){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
