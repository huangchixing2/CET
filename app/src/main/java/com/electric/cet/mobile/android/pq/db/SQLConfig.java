package com.electric.cet.mobile.android.pq.db;

public interface SQLConfig {

    public static final String SQLNAME = "LowLineSys1.db";
//    public static final String CREATE_DEVICEDATA = "deviceInfo";

    public static final String USERCODE = "code";

    public static final String DEVICEID = "DeviceId";
    public static final String DEVICENAME = "DeviceName";
    public static final String CITYID = "CityId";
    public static final String COUNTYID = "CountyId";
    public static final String POWERSUPPLYID = "PowerSupplyId";
    public static final String ISINSTALLED = "IsInstalled";
    public static final String ISONLINE = "IsOnline";
    public static final String ISUSABLE = "IsUsable";
    public static final String ISSIMCARDONLINE = "IsSIMCardOnline";
    public static final String ISABNORMAL = "IsAbnormal";
    public static final String ISPOWERFAILURE = "IsPowerFailure";
    public static final String LONGITUDE = "Longitude";
    public static final String LATITUDE = "Latitude";
    public static final String ADJUSTTIME = "AdjustTime";
    public static final String ISVOLTAGEREGULATENORMAL = "IsVoltageRegulateNormal";
    public static final String ISREACTIVECOMPENSATIONNORMAL = "IsReactiveCompensationNormal";
    public static final String MANUFACTURE = "Manufacture";
    public static final String MODEL = "Model";
    public static final String PHASETYPEID = "PhaseTypeId";
    public static final String CAPACITY = "Capacity";
    public static final String ISCIRCUITNORMAL = "IsCircuitNormal";
    public static final String INSTALLADDRESS = "InstallAddress";
    public static final String DEVICETYPEID = "DeviceTypeId";
    public static final String STATE = "State";
    public static final String CIRCUITID = "CircuitId";
    public static final String COURTS = "Courts";
    public static final String ISMANUFACTURENORMAL = "IsManufactureNormal";
    public static final String LOCATION = "Location";

    //可选信息添加
    public static final String CITIES = "Cities";
    public static final String COUNTIES = "Counties";
    public static final String POWERSUPPLY = "PowerSupply";
    public static final String DEVICETYPE = "DeviceType";
    public static final String SIMCARDSTATE = "SIMCardState";
    public static final String PHASETYPE = "PhaseType";



    /**
     * //	public static final String CREATEDEVICEINFO = "CREATE TABLE IF NOT EXISTS "
     * //			+ TB_DEVICEINFO
     * //			+ " ("
     * //			+ DEVICEID
     * //			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
     * //			+ DEVICENAME
     * //			+ " VARCHAR NULL,"
     * //			+ CITYID
     * //			+ " VARCHAR NULL,"
     * //			+ COUNTYID
     * //			+ " INTEGER,"
     * //			+ POWERSUPPLYID
     * //			+ " INTEGER,"
     * //			+ ISINSTALLED
     * //			+ " VARCHAR NULL,"
     * //			+ ISONLINE
     * //			+ " VARCHAR NULL,"
     * //			+ ISUSABLE
     * //			+ " VARCHAR NULL,"
     * //			+ ISSIMCARDONLINE
     * //			+ " VARCHAR NULL,"
     * //			+ ISABNORMAL
     * //			+ " VARCHAR NULL,"
     * //			+ ISPOWERFAILURE
     * //			+ " VARCHAR NULL,"
     * //			+ LONGITUDE
     * //			+ " VARCHAR NULL,"
     * //			+ LATITUDE
     * //			+ " VARCHAR NULL,"
     * //			+ ADJUSTTIME
     * //			+ " VARCHAR NULL,"
     * //			+ ISVOLTAGEREGULATENORMAL
     * //			+ " VARCHAR NULL,"
     * //			+ ISREACTIVECOMPENSATIONNORMAL
     * //			+ " VARCHAR NULL,"
     * //			+ MANUFACTURE
     * //			+ " VARCHAR NULL,"
     * //			+ MODEL
     * //			+ " VARCHAR NULL,"
     * //			+ PHASETYPEID
     * //			+ " VARCHAR NULL,"
     * //			+ CAPACITY
     * //			+ " VARCHAR NULL,"
     * //			+ ISCIRCUITNORMAL
     * //			+ " VARCHAR NULL,"
     * //			+ INSTALLADDRESS
     * //			+ " VARCHAR NULL,"
     * //			+ DEVICETYPEID
     * //			+ " VARCHAR NULL,"
     * //			+ ISREACTIVECOMPENSATIONNORMAL
     * //			+ " VARCHAR NULL,"
     * //			+ STATE
     * //			+ " VARCHAR NULL,"
     * //			+ CIRCUITID
     * //			+ " VARCHAR NULL,"
     * //			+ COURTS
     * //			+ " VARCHAR NULL,"
     * //			+ ISMANUFACTURENORMAL
     * //			+ " VARCHAR NULL,"
     * //			+ LOCATION
     * //			+ " VARCHAR NULL,"
     * //			+ " INT DEFAULT 0 " + ")";
     **/


    // 创建设备的所有信息表
//    public static final String deviceData = "create table DeviceData(id integer primary key autoincrement, DeviceId real, DeviceName text, CityId real, CountyId real, PowerSupplyId real, IsInstalled integer, IsOnline integer, IsUsable integer, IsSIMCardOnline integer, IsAbnormal integer, IsPowerFailure integer, Longitude real, Latitude real, AdjustTime real, IsVoltageRegulateNormal integer, IsReactiveCompensationNormal integer, Manufacture text, Model text, PhaseTypeId real, Capacity real, IsCircuitNormal integer, InstallAddress text, DeviceTypeId integer, State integer, CircuitId real, Courts text, IsManufactureNormal integer, Location text)";

    public static final String deviceData = "create table DeviceData(id integer primary key autoincrement, DeviceId real, DeviceName text, CityId real, CountyId real, PowerSupplyId real, IsInstalled integer, IsOnline integer, IsUsable integer, IsSIMCardOnline integer, IsAbnormal integer, IsPowerFailure integer, Longitude decimal, Latitude decimal, AdjustTime real, IsVoltageRegulateNormal integer, IsReactiveCompensationNormal integer, Manufacture text, Model text, PhaseTypeId real, Capacity real, IsCircuitNormal integer, InstallAddress text, DeviceTypeId integer, State integer, CircuitId real, Courts text, IsManufactureNormal integer, Location text)";
    //创建可选的节点树信息表
    public static final String optionData = "create table OptionData(id integer primary key autoincrement,Cities text, Counties text, PowerSupply text, DeviceType text, SIMCardState text, PhaseType text)";




}
