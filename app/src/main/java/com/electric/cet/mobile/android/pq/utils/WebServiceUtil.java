package com.electric.cet.mobile.android.pq.utils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class WebServiceUtil {
    private static SoapObject soapObject;
    private static Handler handler = new Handler() {
        @Override
        public void publish(LogRecord record) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }
    };
    /**
     * 调用WebService的方法
     *
     */
    public static void getWebServiceInfo(String nameSpace, String methodName, final String endpoint, final String s<span style="white-space:pre"> </span>oapAction, final SoapObject soapObject, final OnGetResult onGetResult){
        //开启子线程进行网络的更新
        new Thread(new Runnable() {
            @Override
            public void run() {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.bodyOut = soapObject;
                //设置是否调用的事dotNet开发的webservice
                envelope.dotnet = true;
                HttpTransportSE transport = new HttpTransportSE(endPoint);
                try{
                    //调用webservice
                    transport.call(soapAction, envelope);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //获取返回的数据
                final SoapObject object = (SoapObject) envelope.bodyIn;
                //回调到主线程中进行UI的更新
                handler.post(new Runnable(){
                    @Override
                    public void run(){
                        return onGetResultCode(object);
                    }
                });
            }
        }).start();
    }
    /*
     *回调接口
     *
     */
    public  interface OnGetResult{
        public void returnCode(SoapObject object);
    }
    /*
     *  单例模式获取到Object的实例
     *
     */
    public static  SoapObject getObjectInstance(String Space, String method){
        if(soapObject == null){
            synchronized (WebServiceUtil.class){
                if(soapObject == null){
                    soapObject = new SoapObject(Space, method);
                }
            }
        }
        return soapObject;
    }


}
