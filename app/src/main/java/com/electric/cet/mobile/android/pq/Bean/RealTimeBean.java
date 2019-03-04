package com.electric.cet.mobile.android.pq.Bean;

import java.util.List;

public class RealTimeBean {
    private int code;
    private String msg;
    private List<RealTimeData> realTimeData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RealTimeData> getRealTimeData() {
        return realTimeData;
    }

    public void setRealTimeData(List<RealTimeData> realTimeData) {
        this.realTimeData = realTimeData;
    }

    @Override
    public String toString() {
        return "RealTimeBean{" + "code=" + code + ", msg='" + msg + '\'' + ", realTimeData=" + realTimeData + '}';
    }
}
