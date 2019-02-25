package com.electric.cet.mobile.android.pq.utils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 2019/2/24.
 */

public class WebServiceTools {

    //定义一个获取某城市天气信息的方法：
    public static void getWether() {
        String result = "";
        SoapObject soapObject = new SoapObject("http://WebXml.com.cn/", "getRegionProvince");
//        soapObject.addProperty("theCityCode", "北京");
//        soapObject.addProperty("theUserID", "");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;

        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl");
        System.out.println("来电归属查询");
        try {
            httpTransportSE.call("http://WebXml.com.cn/getRegionProvince", envelope);


            System.out.println("调用WebService服务成功");
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("调用WebService服务失败");
        }

        //获得服务返回的数据,并且开始解析
        SoapObject object = (SoapObject) envelope.bodyIn;

        System.out.println("获得服务数据 object : " + object.getProperty(0).toString());
//        if (object.getClass().isArray()) {
//            int length = Array.getLength(object);
//            System.out.println("object 数据条数 : " + length);
//            Object[] os = new Object[length];
//            for (int i = 0; i < os.length; i++) {
//                os[i] = Array.get(object, i);
//            }
//        }
//        for(){
//
//        }
//        result = object.getProperty(1).toString();
        System.out.println("调用WebService服务返回" + result);

    }





}
