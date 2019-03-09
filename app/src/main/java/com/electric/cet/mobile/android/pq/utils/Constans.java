package com.electric.cet.mobile.android.pq.utils;

public class Constans {

    public final static int INSTALL_NUM = 1;//安装数量
    public final static int ONLINE_NUM = 2;//在线数量
    public final static int USABLE_NUM = 3;//可用数量
    public final static int SIM_NUM = 4; //sim卡在线数量
    public final static int EXCEPT_NUM = 5; //功能异常数量
    public final static int POWEROFF_NUM = 6;//停电数量

    public static String URL_DEVICEINFO = "http://192.168.2.100/LowLineSys/device/data/all?token=123"; //所有信息接口url
    public static String URL_OPTION = "http://192.168.2.100/LowLineSys/device/data/options?token=123";//tree接口url
    public static String URL_LOGIN  = "http://192.168.2.100/LowLineSys/user/login"; //登录接口url
    public static String URL_BEFORE = "http://192.168.2.100/LowLineSys/device/";
    public static String URL_AFTER = "/data/realtime"; //实时数据url
    public static String URL_AFTERTREND = "/data/trend/"; //趋势数据url
    public static String URL_LOGINOUT = "http://192.168.2.100/LowLineSys/user/logout?token=123"; //退出登录接口
    public static String URL_USERDATA = "http://192.168.2.100/LowLineSys/user/data";  //用户管理接口
}
