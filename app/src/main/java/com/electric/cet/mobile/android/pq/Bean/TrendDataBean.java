package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

public class TrendDataBean implements Serializable {
    private int DeviceId;  //对象
    private String DeviceName;  //对象
    List<VoltDataBean> VoltData;  //集合
    List<CurrDataBean> CurrData; //集合
    List<PowerDataBean> PowerData;  //集合

    public int getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(int deviceId) {
        DeviceId = deviceId;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public List<VoltDataBean> getVoltData() {
        return VoltData;
    }

    public void setVoltData(List<VoltDataBean> voltData) {
        VoltData = voltData;
    }

    public List<CurrDataBean> getCurrData() {
        return CurrData;
    }

    public void setCurrData(List<CurrDataBean> currData) {
        CurrData = currData;
    }

    public List<PowerDataBean> getPowerData() {
        return PowerData;
    }

    public void setPowerData(List<PowerDataBean> powerData) {
        PowerData = powerData;
    }
}