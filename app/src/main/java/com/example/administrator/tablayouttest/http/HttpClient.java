package com.example.administrator.tablayouttest.http;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Administrator on 2017/11/2.
 */

public class HttpClient {
    String ur="";
    HttpsURLConnection con;
    BufferedReader reader;
    private final int succeed=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case succeed:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    public void HttpClient() {
        try {
            URL url = new URL(ur);
            con= (HttpsURLConnection) url.openConnection();
            con.setConnectTimeout(10000);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            if (con.getResponseCode() == 200) {
                Handler handler = new Handler();
                Message msg = new Message();
                handler.sendEmptyMessage(1);
            }
            InputStream input = con.getInputStream();
            reader= new BufferedReader(new InputStreamReader(input));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
