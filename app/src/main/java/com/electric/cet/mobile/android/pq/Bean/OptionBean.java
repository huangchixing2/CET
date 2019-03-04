package com.electric.cet.mobile.android.pq.Bean;

import java.util.List;

public class OptionBean {
    private int code;
    private String msg;
    List<OptionDataBean> data;

    private class OptionDataBean {
       private List<CitiesDataBean> Cities;
       private int ID;
       private String Name;
    }

    private class CitiesDataBean{
        private List<CountiesDataBean> Counties;
        private int ID;
        private String Name;

    }
    private class CountiesDataBean{
        private List<PowerSupplyDataBean> PowerSupply;
        private int ID;
        private String Name;
    }
        private class PowerSupplyDataBean{
            private int ID;
            private String Name;
        }
}
