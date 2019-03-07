package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

public class StatisticsBean implements Serializable {
    private int code;
    private String msg;
    StatisticsDataBean data;

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

    public StatisticsDataBean getData() {
        return data;
    }

    public void setData(StatisticsDataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StatisticsBean{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

    private class StatisticsDataBean {
        private int DeviceId;
        private String DeviceName;
        private int RegulateNum;
        private Boolean VoltageRegulate;
        private Boolean ReactiveCompensation;
        private String Manufacture;
        private String Model;
        private int Capacity;
        private int PhaseTypeId;
        private Boolean Circuit;
        private String Location;

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

        public int getRegulateNum() {
            return RegulateNum;
        }

        public void setRegulateNum(int regulateNum) {
            RegulateNum = regulateNum;
        }

        public Boolean getVoltageRegulate() {
            return VoltageRegulate;
        }

        public void setVoltageRegulate(Boolean voltageRegulate) {
            VoltageRegulate = voltageRegulate;
        }

        public Boolean getReactiveCompensation() {
            return ReactiveCompensation;
        }

        public void setReactiveCompensation(Boolean reactiveCompensation) {
            ReactiveCompensation = reactiveCompensation;
        }

        public String getManufacture() {
            return Manufacture;
        }

        public void setManufacture(String manufacture) {
            Manufacture = manufacture;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String model) {
            Model = model;
        }

        public int getCapacity() {
            return Capacity;
        }

        public void setCapacity(int capacity) {
            Capacity = capacity;
        }

        public int getPhaseTypeId() {
            return PhaseTypeId;
        }

        public void setPhaseTypeId(int phaseTypeId) {
            PhaseTypeId = phaseTypeId;
        }

        public Boolean getCircuit() {
            return Circuit;
        }

        public void setCircuit(Boolean circuit) {
            Circuit = circuit;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        @Override
        public String toString() {
            return "StatisticsDataBean{" + "DeviceId=" + DeviceId + ", DeviceName='" + DeviceName + '\'' + ", RegulateNum=" + RegulateNum + ", VoltageRegulate=" + VoltageRegulate + ", ReactiveCompensation=" + ReactiveCompensation + ", Manufacture='" + Manufacture + '\'' + ", Model='" + Model + '\'' + ", Capacity=" + Capacity + ", PhaseTypeId=" + PhaseTypeId + ", Circuit=" + Circuit + ", Location='" + Location + '\'' + '}';
        }
    }
}
