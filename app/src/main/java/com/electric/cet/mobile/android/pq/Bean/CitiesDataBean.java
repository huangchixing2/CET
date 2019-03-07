package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

public class CitiesDataBean implements Serializable {

    private List<CountiesDataBean> Counties;
    private int ID;
    private String Name;

    public List<CountiesDataBean> getCounties() {
        return Counties;
    }

    public void setCounties(List<CountiesDataBean> counties) {
        Counties = counties;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
