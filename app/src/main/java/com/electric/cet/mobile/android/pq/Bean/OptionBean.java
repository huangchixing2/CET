package com.electric.cet.mobile.android.pq.Bean;

import java.io.Serializable;

public class OptionBean implements Serializable {
    private int code;
    private String msg;
    private OptionDataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public OptionDataBean getData() {
        return data;
    }

    public void setData(OptionDataBean data) {
        this.data = data;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


//    private class OptionDataBean {
//       private List<CitiesDataBean> Cities;
//       private int ID;
//       private String Name;
//
//        public List<CitiesDataBean> getCities() {
//            return Cities;
//        }
//
//        public void setCities(List<CitiesDataBean> cities) {
//            Cities = cities;
//        }
//
//        public int getID() {
//            return ID;
//        }
//
//        public void setID(int ID) {
//            this.ID = ID;
//        }
//
//        public String getName() {
//            return Name;
//        }
//
//        public void setName(String name) {
//            Name = name;
//        }
//    }
//
//    private class CitiesDataBean{
//        private List<CountiesDataBean> Counties;
//        private int ID;
//        private String Name;
//
//        public List<CountiesDataBean> getCounties() {
//            return Counties;
//        }
//
//        public void setCounties(List<CountiesDataBean> counties) {
//            Counties = counties;
//        }
//
//        public int getID() {
//            return ID;
//        }
//
//        public void setID(int ID) {
//            this.ID = ID;
//        }
//
//        public String getName() {
//            return Name;
//        }
//
//        public void setName(String name) {
//            Name = name;
//        }
//    }
//    private class CountiesDataBean{
//        private List<PowerSupplyDataBean> PowerSupply;
//        private int ID;
//        private String Name;
//
//        public List<PowerSupplyDataBean> getPowerSupply() {
//            return PowerSupply;
//        }
//
//        public void setPowerSupply(List<PowerSupplyDataBean> powerSupply) {
//            PowerSupply = powerSupply;
//        }
//
//        public int getID() {
//            return ID;
//        }
//
//        public void setID(int ID) {
//            this.ID = ID;
//        }
//
//        public String getName() {
//            return Name;
//        }
//
//        public void setName(String name) {
//            Name = name;
//        }
//    }
//        private class PowerSupplyDataBean{
//            private int ID;
//            private String Name;
//
//            public int getID() {
//                return ID;
//            }
//
//            public void setID(int ID) {
//                this.ID = ID;
//            }
//
//            public String getName() {
//                return Name;
//            }
//
//            public void setName(String name) {
//                Name = name;
//            }
//        }
}
