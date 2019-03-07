package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;
import java.util.List;

public class CountiesDataBean implements Serializable {
    private List<CountiesDetailsDataBean> PowerSupply;
    private int ID;
    private String Name;

    public List<CountiesDetailsDataBean> getPowerSupply() {
        return PowerSupply;
    }

    public void setPowerSupply(List<CountiesDetailsDataBean> powerSupply) {
        PowerSupply = powerSupply;
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
