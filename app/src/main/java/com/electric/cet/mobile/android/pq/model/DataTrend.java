package com.electric.cet.mobile.android.pq.model;

/**
 * Created by xushasha
 * use
 */
public class DataTrend {
    //日期
    private String data;
    //电压
    private String voltage;
    //电流
    private String current;
    //功率因数
    private String power;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
