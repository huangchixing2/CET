package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

public class OptionBean implements Serializable {
    private int code;
    private String msg;
    private OptionDataBean data;

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

    public OptionDataBean getData() {
        return data;
    }

    public void setData(OptionDataBean data) {
        this.data = data;
    }
}
