package com.electric.cet.mobile.android.pq.db;

public interface SQLConfig {

	public static final String TB_DEVICEINFO = "DEVICEINFO";
	public static final String SQLNAME = "LowLineSys.db";

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
	public static final String CAPACITY = "PhaseTypeId";
	public static final String ISCIRCUITNORMAL = "IsCircuitNormal";
	public static final String INSTALLADDRESS = "InstallAddress";
	public static final String DEVICETYPEID = "DeviceTypeId";
	public static final String STATE = "State";
	public static final String CIRCUITID = "CircuitId";
	public static final String COURTS = "Courts";
	public static final String ISMANUFACTURENORMAL = "IsManufactureNormal";
	public static final String LOCATION = "Location";

//统计数据表
	public static final String CREATEDEVICEINFO = "CREATE TABLE IF NOT EXISTS "
			+ TB_DEVICEINFO
			+ " ("
			+ DEVICEID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ DEVICENAME
			+ " VARCHAR NULL,"
			+ CITYID
			+ " VARCHAR NULL,"
			+ COUNTYID
			+ " VARCHAR NULL,"
			+ POWERSUPPLYID
			+ " VARCHAR NULL,"
			+ ISINSTALLED
			+ " VARCHAR NULL,"
			+ ISONLINE
			+ " VARCHAR NULL,"
			+ ISUSABLE
			+ " VARCHAR NULL,"
			+ ISSIMCARDONLINE
			+ " VARCHAR NULL,"
			+ ISABNORMAL
			+ " VARCHAR NULL,"
			+ ISPOWERFAILURE
			+ " VARCHAR NULL,"
			+ LONGITUDE
			+ " VARCHAR NULL,"
			+ LATITUDE
			+ " VARCHAR NULL,"
			+ ADJUSTTIME
			+ " VARCHAR NULL,"
			+ ISVOLTAGEREGULATENORMAL
			+ " VARCHAR NULL,"
			+ ISREACTIVECOMPENSATIONNORMAL
			+ " VARCHAR NULL,"
			+ MANUFACTURE
			+ " VARCHAR NULL,"
			+ MODEL
			+ " VARCHAR NULL,"
			+ PHASETYPEID
			+ " VARCHAR NULL,"
			+ CAPACITY
			+ " VARCHAR NULL,"
			+ ISCIRCUITNORMAL
			+ " VARCHAR NULL,"
			+ INSTALLADDRESS
			+ " VARCHAR NULL,"
			+ DEVICETYPEID
			+ " VARCHAR NULL,"
			+ ISREACTIVECOMPENSATIONNORMAL
			+ " VARCHAR NULL,"
			+ STATE
			+ " VARCHAR NULL,"
			+ CIRCUITID
			+ " VARCHAR NULL,"
			+ COURTS
			+ " VARCHAR NULL,"
			+ ISMANUFACTURENORMAL
			+ " VARCHAR NULL,"
			+ LOCATION
			+ " VARCHAR NULL,"
			+ " INT DEFAULT 0 " + ")";


}
