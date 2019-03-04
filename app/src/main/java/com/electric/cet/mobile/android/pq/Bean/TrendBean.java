package com.electric.cet.mobile.android.pq.Bean;

import java.util.List;

public class TrendBean {
    private int code;
    private String msg;
    private List<TrendDataBean> data;

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

    public List<TrendDataBean> getData() {
        return data;
    }

    public void setData(List<TrendDataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TrendBean{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

    private class TrendDataBean {
        private int DeviceId;
        private String DeviceName;
        private List<VoltDataBean> VoltData;

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

        @Override
        public String toString() {
            return "TrendDataBean{" + "DeviceId=" + DeviceId + ", DeviceName='" + DeviceName + '\'' + ", VoltData=" + VoltData + '}';
        }
    }

    private class VoltDataBean{
        private int PhaseType;
        List<DataListBean> DataList;

        public int getPhaseType() {
            return PhaseType;
        }

        public void setPhaseType(int phaseType) {
            PhaseType = phaseType;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            DataList = dataList;
        }

        @Override
        public String toString() {
            return "VoltDataBean{" + "PhaseType=" + PhaseType + ", DataList=" + DataList + '}';
        }
    }

    private class DataListBean{
        private String RecordTime;
        private int Value;

        public String getRecordTime() {
            return RecordTime;
        }

        public void setRecordTime(String recordTime) {
            RecordTime = recordTime;
        }

        public int getValue() {
            return Value;
        }

        public void setValue(int value) {
            Value = value;
        }

        @Override
        public String toString() {
            return "DataListBean{" + "RecordTime='" + RecordTime + '\'' + ", Value=" + Value + '}';
        }
    }
}
