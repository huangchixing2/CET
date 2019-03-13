package com.electric.cet.mobile.android.pq.utils;

public class ChangeTypeUtil {
    //设备类型定义
    private static final String[] deviceType = {"全部类型", "低压调压器", "无功补偿装置", "静止无功发生器", "混合型无功补偿装置", "中压调压器", "中压静止无功发生器", "中压串补"};

    public static String changI2S(int type) {
        String sType = "";
        switch (type) {
            case 1:
                sType = deviceType[1];
                break;
            case 2:
                sType = deviceType[2];
                break;
            case 3:
                sType = deviceType[3];
                break;
            case 4:
                sType = deviceType[4];
                break;
            case 5:
                sType = deviceType[5];
                break;
            case 6:
                sType = deviceType[6];
                break;
            case 7:
                sType = deviceType[7];
                break;
            default:
                sType = "未知";
                break;

        }
        return sType;
    }

    public static Integer changS2I(String type) {
        int iType = 0;
        if (type.equalsIgnoreCase(deviceType[1])) {
            iType = 1;
        } else if (type.equalsIgnoreCase(deviceType[2])) {
            iType = 2;
        } else if (type.equalsIgnoreCase(deviceType[3])) {
            iType = 3;
        } else if (type.equalsIgnoreCase(deviceType[4])) {
            iType = 4;
        } else if (type.equalsIgnoreCase(deviceType[5])) {
            iType = 5;
        } else if (type.equalsIgnoreCase(deviceType[6])) {
            iType = 6;
        } else if (type.equalsIgnoreCase(deviceType[7])) {
            iType = 7;
        }
        return iType;
    }
}