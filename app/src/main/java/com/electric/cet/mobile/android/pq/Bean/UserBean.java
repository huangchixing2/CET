package com.electric.cet.mobile.android.pq.Bean;

public class UserBean {
    private int status;
    private  String msg;
    private String data;
    public int getStatus() {
        return status;
    }

    public int setStatus(int status) {
        this.status = status;
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
