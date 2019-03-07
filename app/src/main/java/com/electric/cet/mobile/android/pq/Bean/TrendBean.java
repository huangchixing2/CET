package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

public class TrendBean implements Serializable {
    private int code;
    private String msg;
    private TrendDataBean data; //对象

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

    public TrendDataBean getData() {
        return data;
    }

    public void setData(TrendDataBean data) {
        this.data = data;
    }
}
