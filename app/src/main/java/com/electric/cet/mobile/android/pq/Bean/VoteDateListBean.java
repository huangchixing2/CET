package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

class VoteDateListBean implements Serializable {
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
}
