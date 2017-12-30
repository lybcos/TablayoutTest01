package com.example.administrator.tablayouttest.http;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/2.
 */

public class okHttp {
   private static String re;
    public  static  String okHttp01() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            re=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  re;
    }

    public void okHttp02() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("uesrname", "lyb")
                .add("password", "123")
                .build();
        Request request=new Request.Builder()
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String res=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
