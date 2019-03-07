package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

public class RealTimeBean implements Serializable {
    private int code;
    private String msg;
    private RealTimeData data;

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

    public RealTimeData getData() {
        return data;
    }

    public void setData(RealTimeData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RealTimeBean{" + "code=" + code + ", msg='" + msg + '\'' + ", realTimeData=" + data + '}';
    }
}
