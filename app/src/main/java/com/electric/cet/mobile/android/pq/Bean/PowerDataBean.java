package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

class PowerDataBean implements Serializable {
    private int PhaseType;
    private List<PowerDataListBean> DataList;

    public int getPhaseType() {
        return PhaseType;
    }

    public void setPhaseType(int phaseType) {
        PhaseType = phaseType;
    }

    public List<PowerDataListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<PowerDataListBean> dataList) {
        DataList = dataList;
    }
}
