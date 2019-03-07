package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

public class CurrDataList implements Serializable {
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
