package com.electric.cet.mobile.android.pq.Bean;

public class RealTimeData {
    private int DeviceId;
    private String DeviceName;
    private float BVoltageInput;
    private float CVoltageInput;
    private float ACurrentInput;
    private float BCurrentInput;
    private float CCurrentInput;
    private float APowerFactorInput;
    private float BPowerFactorInput;
    private float CPowerFactorInput;
    private float AVoltageOutput;
    private float BVoltageOutput;
    private float CVoltageOutput;
    private float ACurrentOutput;
    private float BCurrentOutput;
    private float CCurrentOutput;
    private float APowerFactorOutput;
    private float BBowerFactorOutput;
    private float CPowerFactorOutput;
    private float VoltageRegulate;
    private float ReactivePowerInput;

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

    public float getBVoltageInput() {
        return BVoltageInput;
    }

    public void setBVoltageInput(float BVoltageInput) {
        this.BVoltageInput = BVoltageInput;
    }

    public float getCVoltageInput() {
        return CVoltageInput;
    }

    public void setCVoltageInput(float CVoltageInput) {
        this.CVoltageInput = CVoltageInput;
    }

    public float getACurrentInput() {
        return ACurrentInput;
    }

    public void setACurrentInput(float ACurrentInput) {
        this.ACurrentInput = ACurrentInput;
    }

    public float getBCurrentInput() {
        return BCurrentInput;
    }

    public void setBCurrentInput(float BCurrentInput) {
        this.BCurrentInput = BCurrentInput;
    }

    public float getCCurrentInput() {
        return CCurrentInput;
    }

    public void setCCurrentInput(float CCurrentInput) {
        this.CCurrentInput = CCurrentInput;
    }

    public float getAPowerFactorInput() {
        return APowerFactorInput;
    }

    public void setAPowerFactorInput(float APowerFactorInput) {
        this.APowerFactorInput = APowerFactorInput;
    }

    public float getBBowerFactorInput() {
        return BPowerFactorInput;
    }

    public void setBBowerFactorInput(float BBowerFactorInput) {
        this.BPowerFactorInput = BBowerFactorInput;
    }

    public float getCPowerFactorInput() {
        return CPowerFactorInput;
    }

    public void setCPowerFactorInput(float CPowerFactorInput) {
        this.CPowerFactorInput = CPowerFactorInput;
    }

    public float getAVoltageOutput() {
        return AVoltageOutput;
    }

    public void setAVoltageOutput(float AVoltageOutput) {
        this.AVoltageOutput = AVoltageOutput;
    }

    public float getBVoltageOutput() {
        return BVoltageOutput;
    }

    public void setBVoltageOutput(float BVoltageOutput) {
        this.BVoltageOutput = BVoltageOutput;
    }

    public float getCVoltageOutput() {
        return CVoltageOutput;
    }

    public void setCVoltageOutput(float CVoltageOutput) {
        this.CVoltageOutput = CVoltageOutput;
    }

    public float getACurrentOutput() {
        return ACurrentOutput;
    }

    public void setACurrentOutput(float ACurrentOutput) {
        this.ACurrentOutput = ACurrentOutput;
    }

    public float getBCurrentOutput() {
        return BCurrentOutput;
    }

    public void setBCurrentOutput(float BCurrentOutput) {
        this.BCurrentOutput = BCurrentOutput;
    }

    public float getCCurrentOutput() {
        return CCurrentOutput;
    }

    public void setCCurrentOutput(float CCurrentOutput) {
        this.CCurrentOutput = CCurrentOutput;
    }

    public float getAPowerFactorOutput() {
        return APowerFactorOutput;
    }

    public void setAPowerFactorOutput(float APowerFactorOutput) {
        this.APowerFactorOutput = APowerFactorOutput;
    }

    public float getBBowerFactorOutput() {
        return BBowerFactorOutput;
    }

    public void setBBowerFactorOutput(float BBowerFactorOutput) {
        this.BBowerFactorOutput = BBowerFactorOutput;
    }

    public float getCPowerFactorOutput() {
        return CPowerFactorOutput;
    }

    public void setCPowerFactorOutput(float CPowerFactorOutput) {
        this.CPowerFactorOutput = CPowerFactorOutput;
    }

    public float getVoltageRegulate() {
        return VoltageRegulate;
    }

    public void setVoltageRegulate(float voltageRegulate) {
        VoltageRegulate = voltageRegulate;
    }

    public float getReactivePowerInput() {
        return ReactivePowerInput;
    }

    public void setReactivePowerInput(float reactivePowerInput) {
        ReactivePowerInput = reactivePowerInput;
    }

    @Override
    public String toString() {
        return "RealTimeData{" + "DeviceId=" + DeviceId + ", DeviceName='" + DeviceName + '\'' + ", BVoltageInput=" + BVoltageInput + ", CVoltageInput=" + CVoltageInput + ", ACurrentInput=" + ACurrentInput + ", BCurrentInput=" + BCurrentInput + ", CCurrentInput=" + CCurrentInput + ", APowerFactorInput=" + APowerFactorInput + ", BBowerFactorInput=" + BPowerFactorInput + ", CPowerFactorInput=" + CPowerFactorInput + ", AVoltageOutput=" + AVoltageOutput + ", BVoltageOutput=" + BVoltageOutput + ", CVoltageOutput=" + CVoltageOutput + ", ACurrentOutput=" + ACurrentOutput + ", BCurrentOutput=" + BCurrentOutput + ", CCurrentOutput=" + CCurrentOutput + ", APowerFactorOutput=" + APowerFactorOutput + ", BBowerFactorOutput=" + BBowerFactorOutput + ", CPowerFactorOutput=" + CPowerFactorOutput + ", VoltageRegulate=" + VoltageRegulate + ", ReactivePowerInput=" + ReactivePowerInput + '}';
    }
}