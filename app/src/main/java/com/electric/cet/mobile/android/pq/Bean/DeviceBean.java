package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

public class DeviceBean implements Serializable {
    private int code;
    private  String msg;
    private List <DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DeviceBean{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
