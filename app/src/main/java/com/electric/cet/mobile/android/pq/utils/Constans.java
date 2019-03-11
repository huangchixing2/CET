package com.electric.cet.mobile.android.pq.utils;

public class Constans {

    public final static int INSTALL_NUM = 1;//安装数量
    public final static int ONLINE_NUM = 2;//在线数量
    public final static int USABLE_NUM = 3;//可用数量
    public final static int SIM_NUM = 4; //sim卡在线数量
    public final static int EXCEPT_NUM = 5; //功能异常数量
    public final static int POWEROFF_NUM = 6;//停电数量

    public static String URL_DEVICEINFO = "http://192.168.2.199/LowLineSys/device/data/all?token=123"; //所有信息接口url
    public static String URL_OPTION = "http://192.168.2.199/LowLineSys/device/data/options?token=123";//tree接口url
    public static String URL_LOGIN  = "http://192.168.2.199/LowLineSys/user/login"; //登录接口url
    public static String URL_BEFORE = "http://192.168.2.199/LowLineSys/device/";
    public static String URL_AFTER = "/data/realtime"; //实时数据url
    public static String URL_AFTERTREND = "/data/trend/"; //趋势数据url
    public static String URL_LOGINOUT = "http://192.168.2.199/LowLineSys/user/logout?token=123"; //退出登录接口
    public static String URL_USERDATA = "http://192.168.2.199/LowLineSys/user/data";  //用户管理接口

    public final static int COUNT_CODE = 1001;
    public final static int REALTIME_CODE = 1003;
    public final static int TREND_CODE = 1004;
    public final static int COLLECT_CODE = 1005; //台账数据返回码
    public final static int WORKING_CODE = 1006; //工况数据返回码

}
