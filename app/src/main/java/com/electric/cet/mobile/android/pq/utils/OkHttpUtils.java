package com.electric.cet.mobile.android.pq.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.electric.cet.mobile.android.pq.Bean.LoginBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {


    static final OkHttpClient client = new OkHttpClient();

    public static Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String ReturnMessage = (String) msg.obj;
                System.out.println("返回信息1----" + ReturnMessage);

            }


        }

    };

    /**
     * 基于okhttp框架封装post方法
     * 需要加入依赖
     *
     * @param request
     * @param url
     * @param request
     */

    public static Response response = null;


    public static void postLogin(final Context context, String url, final Request request) {

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
                Log.i("请求情况：", "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i("响应状态", "响应成功");
                    String loginBody = response.body().string();
                    Gson gson = new Gson();
                    LoginBean loginData = gson.fromJson(loginBody, LoginBean.class);

                    int loginResultCode = loginData.getCode();
                    Log.i("resultcode", loginResultCode + "");
                    int ResponseCode = response.code();
                    //无法获取token
                    //响应成功,判断状态码
                    if (ResponseCode == 200) {
                        Log.i("登录状态", "登录成功");
                        String data = loginData.getData().toString(); //这个就是token
                        //保存token
                        //用sp工具保存token
                        SharedPreferences sp = context.getSharedPreferences("TokenData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("token", data);
                        editor.apply();


                        

                    }
                }

            }


        });
    }


//    public static void postLogin(String url, final Request request){
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
////                    Request request = new Request.Builder().url("http://192.168.2.102/LowLineSys/user/login").build();
//
//                    response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
//
//
//                        String result = "请求结果：" + response.body().string();
////                        Log.d("请求结果是" ,"result = " + result);
//                        System.out.println("result为" + result);
//                        Log.d("请求结果是" ,"response = " + response);
////                        String mToken = response.headers().get("token");
////                        System.out.println("TOKEN是多少: " + mToken);
//                        mHandler.obtainMessage(1,response.body().toString()).sendToTarget();
//                    } else {
//                        throw new IOException("Unexpected code:" + response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }

    public static void doGET(String url, final Request request) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        String result = "请求结果：" + response.body().string();
//                        Log.d("请求结果是" ,"result = " + result);
                        System.out.println("result为" + result);
                        System.out.println("信息返回的结果是多少" + response);

                        mHandler.obtainMessage(2, response.body().toString()).sendToTarget();
                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


}
