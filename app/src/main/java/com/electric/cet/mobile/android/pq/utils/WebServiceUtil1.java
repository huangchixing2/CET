package com.electric.cet.mobile.android.pq.utils;

import android.app.Notification;
import android.os.Message;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by huangchixing on 2019/2/23.
 */

public class WebServiceUtil1 {
    //含有3个线程的线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);

    // 命名空间，默认是这个，可以更改，具体需要和后台人员确认
    private static final String NAMESPACE = "";

    /**
     * @param url                WebService服务器地址
     * @param methodName         WebService的调用方法名
     * @param properties         WebService的参数
     * @param webServiceCallBack 回调接口
     */

    public static void callWebService(String url, final String methodName,
                                      Map<String, String> properties,
                                      final WebServiceCallBack webServiceCallBack) {
        // 创建HttpTransportSE对象，传递WebService服务器地址
        final HttpTransportSE httpTransportSE = new HttpTransportSE(url);
        // 创建SoapObject对象
        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);
        // SoapObject添加参数
        if (properties != null) {
            for (Iterator<Map.Entry<String, String>> it = properties.entrySet()
                    .iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                soapObject.addProperty(entry.getKey(), entry.getValue());
            }
        }
        // 实例化SoapSerializationEnvelope，传入WebService的SOAP协议的版本号
        final SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);
        // 设置是否调用的是.Net开发的WebService
        soapEnvelope.setOutputSoapObject(soapObject);
        soapEnvelope.dotNet = true;
        httpTransportSE.debug = true;
        // 用于子线程与主线程通信的Handler
        final android.os.Handler handler = new android.os.Handler(){
        @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
            // 将返回值回调到callBack的参数中
            webServiceCallBack.callBack((SoapObject) msg.obj);
        }

        };
        // 开启线程去访问WebService
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                SoapObject resultSoapObject = null;
                try {
                    httpTransportSE.call(NAMESPACE + methodName, soapEnvelope);
                    if (soapEnvelope.getResponse() != null) {
                        // 获取服务器响应返回的SoapObject
                        Log.i("获取服务器返回的表示", soapEnvelope.getResponse() + "");//测试是成功返回true
                        resultSoapObject = (SoapObject) soapEnvelope.bodyIn;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } finally {
                    // 将获取的消息利用Handler发送到主线程
                    handler.sendMessage(handler.obtainMessage(0,
                            resultSoapObject));
                }
            }
        });
    }

    public interface WebServiceCallBack {
        public void callBack(SoapObject result);
    }
}



