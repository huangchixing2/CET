package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

class CurrDataBean implements Serializable {
    private int PhaseType;
    private List<CurrDataList> DataList;

    public int getPhaseType() {
        return PhaseType;
    }

    public void setPhaseType(int phaseType) {
        PhaseType = phaseType;
    }

    public List<CurrDataList> getDataList() {
        return DataList;
    }

    public void setDataList(List<CurrDataList> dataList) {
        DataList = dataList;
    }
}
