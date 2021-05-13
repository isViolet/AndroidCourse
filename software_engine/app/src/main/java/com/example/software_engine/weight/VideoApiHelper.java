package com.example.software_engine.weight;

import com.example.software_engine.entity.UserBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VideoApiHelper {

    public static void apiAddUser(String userName, String userPwd, String userPhone, int userSex) {

        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setUserPwd(userPwd);
        userBean.setUserPhone(userPhone);
        userBean.setUserSex(userSex);

        //使用Gson 添加 依赖 compile 'com.google.code.gson:gson:2.8.1'
        Gson gson = new Gson();
        //使用Gson将对象转换为json字符串
        String json = gson.toJson(userBean);

        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                ,json);

        Request request = new Request.Builder()
                .url("http://192.168.43.77:8001/eduservice/video_userinfo/addUser")//请求的url
                .post(requestBody)
                .build();

        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("连接失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }
}

