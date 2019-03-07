package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

class VoteDateListBean implements Serializable {
    private String RecordTime;
    private double Value;

    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String recordTime) {
        RecordTime = recordTime;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }
}
