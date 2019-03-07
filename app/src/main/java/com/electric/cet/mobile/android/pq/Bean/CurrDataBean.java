package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

class CurrDataBean implements Serializable {
    private double PhaseType;
    private List<CurrDataList> DataList;

    public double getPhaseType() {
        return PhaseType;
    }

    public void setPhaseType(double phaseType) {
        PhaseType = phaseType;
    }


    public List<CurrDataList> getDataList() {
        return DataList;
    }

    public void setDataList(List<CurrDataList> dataList) {
        DataList = dataList;
    }
}
