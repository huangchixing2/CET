package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

public class PhaseTypeDataBean implements Serializable {
    private int ID;
    private String Name;

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
