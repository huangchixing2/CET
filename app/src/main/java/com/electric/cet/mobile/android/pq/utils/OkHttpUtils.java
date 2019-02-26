package com.electric.cet.mobile.android.pq.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {


    static final OkHttpClient client = new OkHttpClient();

    public static Handler mHandler = new Handler(){

        public void handleMessage(Message msg){
            if(msg.what==1){
                String ReturnMessage = (String) msg.obj;
                System.out.println("返回信息1----" + ReturnMessage);

            }



        }

    };

    /**
     *
     *  基于okhttp框架封装post方法
     *  需要加入依赖
     * @param request
     * @param url
     * @param request
     */

    public static Response response = null;

    public static void postLogin(String url, final Request request){


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Request request = new Request.Builder().url("http://192.168.2.102/LowLineSys/user/login").build();

                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui


                        String result = "请求结果：" + response.body().string();
//                        Log.d("请求结果是" ,"result = " + result);
                        System.out.println("result为" + result);
                        Log.d("请求结果是" ,"response = " + response);
                        String mToken = response.headers().get("token");
                        System.out.println("TOKEN是多少: " + mToken);
                        mHandler.obtainMessage(1,response.body().toString()).sendToTarget();
                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();




    }

    public static void GetDeviceInfo(String url, final Request request){


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

                        mHandler.obtainMessage(2,response.body().toString()).sendToTarget();
                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();




    }




//    public static void postRequest(String UserName,String EncryptPwd )  {
//        //建立请求表单，添加上传服务器的参数
//        RequestBody formBody = new FormBody.Builder()
//                .add("UserName",UserName)
//                .add("EncryptPwd ",EncryptPwd )
//                .build();
////        pc与客户端需要在同一个局域网，指定ip为PC的ip
//        //发起请求
//        final Request request = new Request.Builder()
//                .url("http://192.168.2.102/LowLineSys/user/login")
//                .post(formBody)
//                .build();
//        //新建一个线程，用于得到服务器响应的参数
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Response response = null;
//                try {
//
//                    response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
//
//                        String result = "请求结果："+response.body().string();
//                        System.out.println("result结果是 ：---" + result);
//
//                        System.out.println("登录成功");
//                        Message msg = Message.obtain();
//                        msg.obj = result;
//                        msg.what = 1;
//                        mHandler.sendMessage(msg);
//                    } else {
//                        throw new IOException("Unexpected code:" + response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }).start();
//
//    }

}
