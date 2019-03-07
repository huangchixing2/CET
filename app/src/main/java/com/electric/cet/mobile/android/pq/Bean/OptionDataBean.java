package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

public class OptionDataBean implements Serializable {
    private List<CitiesDataBean> Cities;
    private List<DeviceTypeDataBean> DeviceType;
    private List<SimCardStateDataBean> SIMCardState;
    private List<PhaseTypeDataBean> PhaseType;

    public List<CitiesDataBean> getCities() {
        return Cities;
    }

    public void setCities(List<CitiesDataBean> cities) {
        Cities = cities;
    }

    public List<DeviceTypeDataBean> getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(List<DeviceTypeDataBean> deviceType) {
        DeviceType = deviceType;
    }

    public List<SimCardStateDataBean> getSIMCardState() {
        return SIMCardState;
    }

    public void setSIMCardState(List<SimCardStateDataBean> SIMCardState) {
        this.SIMCardState = SIMCardState;
    }

    public List<PhaseTypeDataBean> getPhaseType() {
        return PhaseType;
    }

    public void setPhaseType(List<PhaseTypeDataBean> phaseType) {
        PhaseType = phaseType;
    }
}
