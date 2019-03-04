package com.electric.cet.mobile.android.pq.Bean;

public class DataBean {
         private int DeviceId;
         private String DeviceName;
         private int CityId;
         private int CountyId;
         private int PowerSupplyId;
         private Boolean IsInstalled;
         private Boolean IsOnline;
         private Boolean IsUsable;
         private Boolean IsSIMCardOnline;
         private Boolean IsAbnormal;
         private Boolean IsPowerFailure;
         private int Longitude;
         private int Latitude;
         private int AdjustTime;
         private Boolean IsVoltageRegulateNormal;
         private Boolean IsReactiveCompensationNormal;
         private String Manufacture;
         private String Model;
         private int PhaseTypeId;
         private int Capacity;
         private Boolean IsCircuitNormal;
         private String InstallAddress;
         private int DeviceTypeId;
         private Boolean State;
         private int CircuitId;
         private String Courts;
         private Boolean IsManufactureNormal;
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

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
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

    public int getLongitude() {
        return Longitude;
    }

    public void setLongitude(int longitude) {
        Longitude = longitude;
    }

    public int getLatitude() {
        return Latitude;
    }

    public void setLatitude(int latitude) {
        Latitude = latitude;
    }

    public int getAdjustTime() {
        return AdjustTime;
    }

    public void setAdjustTime(int adjustTime) {
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

    public int getPhaseTypeId() {
        return PhaseTypeId;
    }

    public void setPhaseTypeId(int phaseTypeId) {
        PhaseTypeId = phaseTypeId;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
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
