package com.electric.cet.mobile.android.pq.Bean;

import java.util.List;

public class WorkConditionBean {
    private int code;
    private String msg;
    private List<WorkConditionDataBean> data;

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

    public List<WorkConditionDataBean> getData() {
        return data;
    }

    public void setData(List<WorkConditionDataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WorkConditionBean{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

    private class WorkConditionDataBean {
        private int DeviceId;
        private String DeviceName;
        private int DeviceTypeId;
        private Boolean State;
        private int CountyId;
        private int PowerSupplyId;
        private int CircuitId;
        private String Courts;
        private Boolean SIMCardState;
        private Boolean IsManufactureNormal;
        private String Model;
        private int Capacity;
        private int PhaseTypeId;
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

        public int getDeviceTypeId() {
            return DeviceTypeId;
        }

        public void setDeviceTypeId(int deviceTypeId) {
            DeviceTypeId = deviceTypeId;
        }

        public Boolean getState() {
            return State;
        }

        public void setState(Boolean state) {
            State = state;
        }

        public int getCountyId() {
            return CountyId;
        }

        public void setCountyId(int countyId) {
            CountyId = countyId;
        }

        public int getPowerSupplyId() {
            return PowerSupplyId;
        }

        public void setPowerSupplyId(int powerSupplyId) {
            PowerSupplyId = powerSupplyId;
        }

        public int getCircuitId() {
            return CircuitId;
        }

        public void setCircuitId(int circuitId) {
            CircuitId = circuitId;
        }

        public String getCourts() {
            return Courts;
        }

        public void setCourts(String courts) {
            Courts = courts;
        }

        public Boolean getSIMCardState() {
            return SIMCardState;
        }

        public void setSIMCardState(Boolean SIMCardState) {
            this.SIMCardState = SIMCardState;
        }

        public Boolean getManufactureNormal() {
            return IsManufactureNormal;
        }

        public void setManufactureNormal(Boolean manufactureNormal) {
            IsManufactureNormal = manufactureNormal;
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

        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        @Override
        public String toString() {
            return "WorkConditionDataBean{" + "DeviceId=" + DeviceId + ", DeviceName='" + DeviceName + '\'' + ", DeviceTypeId=" + DeviceTypeId + ", State=" + State + ", CountyId=" + CountyId + ", PowerSupplyId=" + PowerSupplyId + ", CircuitId=" + CircuitId + ", Courts='" + Courts + '\'' + ", SIMCardState=" + SIMCardState + ", IsManufactureNormal=" + IsManufactureNormal + ", Model='" + Model + '\'' + ", Capacity=" + Capacity + ", PhaseTypeId=" + PhaseTypeId + ", Location='" + Location + '\'' + '}';
        }
    }
}
