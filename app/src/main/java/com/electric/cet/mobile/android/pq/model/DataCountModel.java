package com.electric.cet.mobile.android.pq.model;

import java.io.Serializable;

/**
 * Created by xushasha
 * use
 */
public class DataCountModel implements Serializable {
    private String address;
    private int  num;
    private boolean statu;
    private boolean svc;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isStatu() {
        return statu;
    }

    public void setStatu(boolean statu) {
        this.statu = statu;
    }

    public boolean isSvc() {
        return svc;
    }

    public void setSvc(boolean svc) {
        this.svc = svc;
    }
}
