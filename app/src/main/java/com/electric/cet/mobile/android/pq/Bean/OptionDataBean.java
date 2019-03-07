package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

public class OptionDataBean implements Serializable {
    private List<CountiesDataBean> Counties;
    private List<DeviceTypeDataBean> DeviceType;
    private List<SimCardStateDataBean> SIMCardState;
    private List<PhaseTypeDataBean> PhaseType;

    public List<CountiesDataBean> getCounties() {
        return Counties;
    }

    public void setCounties(List<CountiesDataBean> counties) {
        Counties = counties;
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
