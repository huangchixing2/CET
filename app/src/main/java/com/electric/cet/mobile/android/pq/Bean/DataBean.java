package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

public class DataBean implements Serializable {
    //设备信息，共28条
    private long DeviceId; //设备id
         private String DeviceName;//设备名称
         private long CityId; //地市id
         private long CountyId; //区县id
         private long PowerSupplyId;//供电所id
         private Boolean IsInstalled; //是否安装
         private Boolean IsOnline;//是否在线
         private Boolean IsUsable;//是否可用
         private Boolean IsSIMCardOnline;//simcard是否在线
         private Boolean IsAbnormal; //功能是否异常
         private Boolean IsPowerFailure;//是否停电
         private long Longitude; //经度
         private long Latitude; //纬度
         private long AdjustTime;//调整次数
         private Boolean IsVoltageRegulateNormal;//调压是否正常
         private Boolean IsReactiveCompensationNormal; //无功补偿是否正常
         private String Manufacture;//厂家信息
         private String Model; //型号
         private long PhaseTypeId; //相数
         private long Capacity; //容量大小
         private Boolean IsCircuitNormal; //线路是否正常
         private String InstallAddress;//安装地址
         private int DeviceTypeId; //设备类型id
         private Boolean State;//状态是否异常
         private long CircuitId;//线路ID
         private String Courts;//台区
         private Boolean IsManufactureNormal; //厂家是否正常
         private String Location;//详细位置

    public long getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(long deviceId) {
        DeviceId = deviceId;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public long getCityId() {
        return CityId;
    }

    public void setCityId(long cityId) {
        CityId = cityId;
    }

    public long getCountyId() {
        return CountyId;
    }

    public void setCountyId(long countyId) {
        CountyId = countyId;
    }

    public long getPowerSupplyId() {
        return PowerSupplyId;
    }

    public void setPowerSupplyId(long powerSupplyId) {
        PowerSupplyId = powerSupplyId;
    }

    public Boolean getInstalled() {
        return IsInstalled;
    }

    public void setInstalled(Boolean installed) {
        IsInstalled = installed;
    }

    public Boolean getOnline() {
        return IsOnline;
    }

    public void setOnline(Boolean online) {
        IsOnline = online;
    }

    public Boolean getUsable() {
        return IsUsable;
    }

    public void setUsable(Boolean usable) {
        IsUsable = usable;
    }

    public Boolean getSIMCardOnline() {
        return IsSIMCardOnline;
    }

    public void setSIMCardOnline(Boolean SIMCardOnline) {
        IsSIMCardOnline = SIMCardOnline;
    }

    public Boolean getAbnormal() {
        return IsAbnormal;
    }

    public void setAbnormal(Boolean abnormal) {
        IsAbnormal = abnormal;
    }

    public Boolean getPowerFailure() {
        return IsPowerFailure;
    }

    public void setPowerFailure(Boolean powerFailure) {
        IsPowerFailure = powerFailure;
    }

    public long getLongitude() {
        return Longitude;
    }

    public void setLongitude(long longitude) {
        Longitude = longitude;
    }

    public long getLatitude() {
        return Latitude;
    }

    public void setLatitude(long latitude) {
        Latitude = latitude;
    }

    public long getAdjustTime() {
        return AdjustTime;
    }

    public void setAdjustTime(long adjustTime) {
        AdjustTime = adjustTime;
    }

    public Boolean getVoltageRegulateNormal() {
        return IsVoltageRegulateNormal;
    }

    public void setVoltageRegulateNormal(Boolean voltageRegulateNormal) {
        IsVoltageRegulateNormal = voltageRegulateNormal;
    }

    public Boolean getReactiveCompensationNormal() {
        return IsReactiveCompensationNormal;
    }

    public void setReactiveCompensationNormal(Boolean reactiveCompensationNormal) {
        IsReactiveCompensationNormal = reactiveCompensationNormal;
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

    public long getPhaseTypeId() {
        return PhaseTypeId;
    }

    public void setPhaseTypeId(long phaseTypeId) {
        PhaseTypeId = phaseTypeId;
    }

    public long getCapacity() {
        return Capacity;
    }

    public void setCapacity(long capacity) {
        Capacity = capacity;
    }

    public Boolean getCircuitNormal() {
        return IsCircuitNormal;
    }

    public void setCircuitNormal(Boolean circuitNormal) {
        IsCircuitNormal = circuitNormal;
    }

    public String getInstallAddress() {
        return InstallAddress;
    }

    public void setInstallAddress(String installAddress) {
        InstallAddress = installAddress;
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

    public long getCircuitId() {
        return CircuitId;
    }

    public void setCircuitId(long circuitId) {
        CircuitId = circuitId;
    }

    public String getCourts() {
        return Courts;
    }

    public void setCourts(String courts) {
        Courts = courts;
    }

    public Boolean getManufactureNormal() {
        return IsManufactureNormal;
    }

    public void setManufactureNormal(Boolean manufactureNormal) {
        IsManufactureNormal = manufactureNormal;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public String toString() {
        return "DataBean{" + "DeviceId=" + DeviceId + ", DeviceName='" + DeviceName + '\'' + ", CityId=" + CityId + ", CountyId=" + CountyId + ", PowerSupplyId=" + PowerSupplyId + ", IsInstalled=" + IsInstalled + ", IsOnline=" + IsOnline + ", IsUsable=" + IsUsable + ", IsSIMCardOnline=" + IsSIMCardOnline + ", IsAbnormal=" + IsAbnormal + ", IsPowerFailure=" + IsPowerFailure + ", Longitude=" + Longitude + ", Latitude=" + Latitude + ", AdjustTime=" + AdjustTime + ", IsVoltageRegulateNormal=" + IsVoltageRegulateNormal + ", IsReactiveCompensationNormal=" + IsReactiveCompensationNormal + ", Manufacture='" + Manufacture + '\'' + ", Model='" + Model + '\'' + ", PhaseTypeId=" + PhaseTypeId + ", Capacity=" + Capacity + ", IsCircuitNormal=" + IsCircuitNormal + ", InstallAddress=" + InstallAddress + ", DeviceTypeId=" + DeviceTypeId + ", State=" + State + ", CircuitId=" + CircuitId + ", Courts='" + Courts + '\'' + ", IsManufactureNormal=" + IsManufactureNormal + ", Location='" + Location + '\'' + '}';
    }
}
