package com.electric.cet.mobile.android.pq.utils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.logging.Handler;

public class WebServiceUtil {
    private static SoapObject soapObject;
    Handler handler;

    /**
     * 调用WebService的方法
     */
    public static void getWebServiceInfo(String nameSpace, String methodName, final String endpoint, final String soapAction, final SoapObject soapObject, final OnGetResult onGetResult) {
        //开启子线程进行网络的更新
        new Thread(new Runnable() {
            public android.os.Handler handler;

            @Override
            public void run() {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.bodyOut = soapObject;
                //设置是否调用的事dotNet开发的webservice
                envelope.dotNet = true;
                HttpTransportSE transport = new HttpTransportSE(endpoint);
                try {
                    //调用webservice
                    transport.call(soapAction, envelope);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //获取返回的数据
                final SoapObject object = (SoapObject) envelope.bodyIn;
                //回调到主线程中进行UI的更新
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        onGetResult.returnCode(object);
                    }
                });


            }
        }).start();
    }

    /*
     *回调接口
     *
     */
    public interface OnGetResult {
        public void returnCode(SoapObject object);
    }

    /*
     *  单例模式获取到Object的实例
     *
     */
    public static SoapObject getObjectInstance(String Space, String method) {
        if (soapObject == null) {
            synchronized (WebServiceUtil.class) {
                if (soapObject == null) {
                    soapObject = new SoapObject(Space, method);
                }
            }
        }
        return soapObject;
    }


}
