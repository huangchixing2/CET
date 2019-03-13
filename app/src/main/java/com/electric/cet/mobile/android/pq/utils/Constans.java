package com.electric.cet.mobile.android.pq.utils;

public class Constans {

    public final static int INSTALL_NUM = 1;//安装数量
    public final static int ONLINE_NUM = 2;//在线数量
    public final static int USABLE_NUM = 3;//可用数量
    public final static int SIM_NUM = 4; //sim卡在线数量
    public final static int EXCEPT_NUM = 5; //功能异常数量
    public final static int POWEROFF_NUM = 6;//停电数量


    public final static String BASE_URL = "http://192.168.2.199";
    public final static String URL_DEVICEINFO =  BASE_URL + "/LowLineSys/device/data/all?token=123"; //所有信息接口url
    public final static String URL_OPTION = BASE_URL + "/LowLineSys/device/data/options?token=123";//tree接口url
    public final static String URL_LOGIN  = BASE_URL + "/LowLineSys/user/login"; //登录接口url
    public final static String URL_BEFORE = BASE_URL + "/LowLineSys/device/";
    public final static String URL_AFTER = "/data/realtime"; //实时数据url
    public final static String URL_AFTERTREND = "/data/trend/"; //趋势数据url
    public final static String URL_LOGINOUT = BASE_URL + "/LowLineSys/user/logout?token=123"; //退出登录接口
    public final static String URL_USERDATA = BASE_URL + "/LowLineSys/user/data";  //用户管理接口

    public final static int TYPE_ALL = 0;  //全部类型
    public final static int TYPE_LOW = 1;  //低压调压器
    public final static int TYPE_REACTIVE = 2;  //无功补偿装置
    public final static int TYPE_MOTIONLESS_REACTIVE = 3;//静止无功发生器
    public final static int TYPE_MIXTURE = 4; //混合型无功补偿装置
    public final static int TYPE_MIDDLE = 5;  //中压调压器
    public final static int TYPE_MIDDLE_MOTIONLESS = 6; //中压静止无功调压器
    public final static int TYPE_SERIES_COMPENSATION =7; //中压串补




//    public static String URL_DEVICEINFO = "http://192.168.2.199/LowLineSys/device/data/all?token=123"; //所有信息接口url
//    public static String URL_OPTION = "http://192.168.2.199/LowLineSys/device/data/options?token=123";//tree接口url
//    public static String URL_LOGIN  = "http://192.168.2.199/LowLineSys/user/login"; //登录接口url
//    public static String URL_BEFORE = "http://192.168.2.199/LowLineSys/device/";
//    public static String URL_AFTER = "/data/realtime"; //实时数据url
//    public static String URL_AFTERTREND = "/data/trend/"; //趋势数据url
//    public static String URL_LOGINOUT = "http://192.168.2.199/LowLineSys/user/logout?token=123"; //退出登录接口
//    public static String URL_USERDATA = "http://192.168.2.199/LowLineSys/user/data";  //用户管理接口

    public final static int COUNT_CODE = 1001;
    public final static int REALTIME_CODE = 1003;
    public final static int TREND_CODE = 1004;
    public final static int COLLECT_CODE = 1005; //台账数据返回码
    public final static int WORKING_CODE = 1006; //工况数据返回码

}
