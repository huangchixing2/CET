package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

public class VoltDataBean implements Serializable {
    private int PhaseType;  //对象
    private List<VoteDateListBean> DataList; //集合

    public int getPhaseType() {
        return PhaseType;
    }

    public void setPhaseType(int phaseType) {
        PhaseType = phaseType;
    }

    public List<VoteDateListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<VoteDateListBean> dataList) {
        DataList = dataList;
    }
}
