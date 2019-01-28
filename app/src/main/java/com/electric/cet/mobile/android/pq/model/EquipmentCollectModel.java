package com.electric.cet.mobile.android.pq.model;

/**
 * Created by xushasha
 * use
 */
public class EquipmentCollectModel {
    //列表显示
    private boolean isSle;
    private String address;
    private String type;
    private boolean statu;

    //新增显示
    private String city;
    private String county;
    private String powersupply;
    //新增界面显示的类型
    private String style;
    private String route;
    private String zonearea;
    private String sim;
    private String vender;
    private String model;
    private String phases;
    private String capacity;

    public boolean isSle() {
        return isSle;
    }

    public void setSle(boolean sle) {
        isSle = sle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatu() {
        return statu;
    }

    public void setStatu(boolean statu) {
        this.statu = statu;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPowersupply() {
        return powersupply;
    }

    public void setPowersupply(String powersupply) {
        this.powersupply = powersupply;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getZonearea() {
        return zonearea;
    }

    public void setZonearea(String zonearea) {
        this.zonearea = zonearea;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPhases() {
        return phases;
    }

    public void setPhases(String phases) {
        this.phases = phases;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
