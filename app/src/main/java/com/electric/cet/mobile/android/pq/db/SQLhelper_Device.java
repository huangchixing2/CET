package com.electric.cet.mobile.android.pq.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.electric.cet.mobile.android.pq.Bean.DataBean;

import java.util.List;


public class SQLhelper_Device extends SQLiteOpenHelper implements SQLConfig {

    private static final String TAG = "SQLhelper_Device";
    private Context mContext;

    public SQLhelper_Device(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "[CREATE_DEVICEDATA]:" + CREATE_DEVICEDATA);
        db.execSQL(deviceData);
//        Log.i(TAG, "[CREATE_REALTIMEDATA]:" + CREATE_REALTIMEDATA);
//        db.execSQL(CREATE_REALTIMEDATA);
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS "+CREATE_DEVICEDATA);
        onCreate(db);
    }

    private static SQLhelper_Device instance;
//使用单例模式
    public static SQLhelper_Device Instance(Context context) {
        if (instance == null){
            instance = new SQLhelper_Device(context, SQLConfig.SQLNAME, null, 3);
            Log.i(TAG, "1111");
        }
        return instance;
    }

    /**
     * 添加设备信息
     *
     * @param
     */
    public static void insertUserInfo(List<DataBean> deviceInfos) {
        SQLiteDatabase db = instance.getWritableDatabase();
        for(int i = 0;i< deviceInfos.size();i++){
            ContentValues values = new ContentValues();
            values.put(DEVICEID, deviceInfos.get(i).getDeviceId());
            values.put(DEVICENAME, deviceInfos.get(i).getDeviceName());
            values.put(CITYID, deviceInfos.get(i).getCityId());
            values.put(COUNTYID, deviceInfos.get(i).getCountyId());
            values.put(POWERSUPPLYID, deviceInfos.get(i).getPowerSupplyId());
            values.put(ISINSTALLED, deviceInfos.get(i).getInstalled());
            values.put(ISONLINE, deviceInfos.get(i).getOnline());
            values.put(ISUSABLE, deviceInfos.get(i).getUsable());
            values.put(ISSIMCARDONLINE, deviceInfos.get(i).getSIMCardOnline());
            values.put(ISABNORMAL, deviceInfos.get(i).getAbnormal());
            values.put(ISPOWERFAILURE, deviceInfos.get(i).getPowerFailure());
            values.put(LONGITUDE, deviceInfos.get(i).getLongitude());
            values.put(LATITUDE, deviceInfos.get(i).getLatitude());
            values.put(ADJUSTTIME, deviceInfos.get(i).getAdjustTime());
            values.put(ISVOLTAGEREGULATENORMAL, deviceInfos.get(i).getVoltageRegulateNormal());
            values.put(ISREACTIVECOMPENSATIONNORMAL, deviceInfos.get(i).getReactiveCompensationNormal());
            values.put(MANUFACTURE, deviceInfos.get(i).getManufacture());
            values.put(MODEL, deviceInfos.get(i).getModel());
            values.put(PHASETYPEID, deviceInfos.get(i).getPhaseTypeId());
            values.put(CAPACITY, deviceInfos.get(i).getCapacity());
            values.put(ISCIRCUITNORMAL, deviceInfos.get(i).getCircuitNormal());
            values.put(INSTALLADDRESS, deviceInfos.get(i).getInstallAddress());
            values.put(DEVICETYPEID, deviceInfos.get(i).getDeviceTypeId());
            values.put(STATE, deviceInfos.get(i).getState());
            values.put(CIRCUITID, deviceInfos.get(i).getCircuitId());
            values.put(COURTS, deviceInfos.get(i).getCourts());
            values.put(ISMANUFACTURENORMAL, deviceInfos.get(i).getManufactureNormal());
            values.put(LOCATION, deviceInfos.get(i).getLocation());
            db.insert("DeviceData", null, values);
        }

    }

    /**
     * 修改设备信息
     * @param
     * @param userCode
     * @param
     */
    public static void updateUserInfo(String userCode,DataBean deviceInfo){
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DEVICEID, deviceInfo.getDeviceId());
        values.put(DEVICENAME, deviceInfo.getDeviceName());
        values.put(CITYID, deviceInfo.getCityId());
        values.put(COUNTYID, deviceInfo.getCountyId());
        values.put(POWERSUPPLYID, deviceInfo.getPowerSupplyId());
        values.put(ISINSTALLED, deviceInfo.getInstalled());
        values.put(ISONLINE, deviceInfo.getOnline());
        values.put(ISUSABLE, deviceInfo.getUsable());
        values.put(ISSIMCARDONLINE, deviceInfo.getSIMCardOnline());
        values.put(ISABNORMAL, deviceInfo.getAbnormal());
        values.put(ISPOWERFAILURE, deviceInfo.getPowerFailure());
        values.put(LONGITUDE, deviceInfo.getLongitude());
        values.put(LATITUDE, deviceInfo.getLatitude());
        values.put(ADJUSTTIME, deviceInfo.getAdjustTime());
        values.put(ISVOLTAGEREGULATENORMAL, deviceInfo.getVoltageRegulateNormal());
        values.put(ISREACTIVECOMPENSATIONNORMAL, deviceInfo.getReactiveCompensationNormal());
        values.put(MANUFACTURE, deviceInfo.getManufacture());
        values.put(MODEL, deviceInfo.getModel());
        values.put(PHASETYPEID, deviceInfo.getPhaseTypeId());
        values.put(CAPACITY, deviceInfo.getCapacity());
        values.put(ISCIRCUITNORMAL, deviceInfo.getCircuitNormal());
        values.put(INSTALLADDRESS, deviceInfo.getInstallAddress());
        values.put(DEVICETYPEID, deviceInfo.getDeviceTypeId());
        values.put(STATE, deviceInfo.getState());
        values.put(CIRCUITID, deviceInfo.getCircuitId());
        values.put(COURTS, deviceInfo.getCourts());
        values.put(ISMANUFACTURENORMAL, deviceInfo.getManufactureNormal());
        values.put(LOCATION, deviceInfo.getLocation());
        db.update(CREATE_DEVICEDATA, values, "userCode=?", new String []{userCode});
    }

    /**
     * 查询设备信息
     *
     * @param
     * @param
     * @return
     */
    public static DataBean queryUserInfo() {
        SQLiteDatabase db = instance.getReadableDatabase();
        DataBean dataBean = null;
        Cursor cursor = db.query(CREATE_DEVICEDATA, null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            dataBean = new DataBean();
//            String code = cursor.getString(cursor.getColumnIndex(CODE));  //需要确定和添加
            int devid = cursor.getInt(cursor.getColumnIndex(DEVICEID));
            String devicename = cursor.getString(cursor.getColumnIndex(DEVICENAME));
            int cityid = cursor.getInt(cursor.getColumnIndex(CITYID));
            int countyid = cursor.getInt(cursor.getColumnIndex(COUNTYID));
            int powersupplyid = cursor.getInt(cursor.getColumnIndex(POWERSUPPLYID));
            String isinstalled = cursor.getString(cursor.getColumnIndex(ISINSTALLED));
            String isonline = cursor.getString(cursor.getColumnIndex(ISONLINE));
            String isusable = cursor.getString(cursor.getColumnIndex(ISUSABLE));
            String issimcardonline = cursor.getString(cursor.getColumnIndex(ISSIMCARDONLINE));
            String isabnormal = cursor.getString(cursor.getColumnIndex(ISABNORMAL));
            String ispowerfailure = cursor.getString(cursor.getColumnIndex(ISPOWERFAILURE));
            int longitude = cursor.getInt(cursor.getColumnIndex(LONGITUDE));
            int latitude = cursor.getInt(cursor.getColumnIndex(LATITUDE));
            int adjusttime = cursor.getInt(cursor.getColumnIndex(ADJUSTTIME));
            String isvoltageregulatenormal = cursor.getString(cursor.getColumnIndex(ISVOLTAGEREGULATENORMAL));
            String isreactivecompensationnormal = cursor.getString(cursor.getColumnIndex(ISREACTIVECOMPENSATIONNORMAL));
            String manufacture = cursor.getString(cursor.getColumnIndex(MANUFACTURE));
            String model = cursor.getString(cursor.getColumnIndex(MODEL));
            int phasetypeid = cursor.getInt(cursor.getColumnIndex(CAPACITY));
            int capacity = cursor.getInt(cursor.getColumnIndex(CAPACITY));
            String iscircuitnormal = cursor.getString(cursor.getColumnIndex(ISCIRCUITNORMAL));
            String installaddress = cursor.getString(cursor.getColumnIndex(INSTALLADDRESS));
            int devicetypeid = cursor.getInt(cursor.getColumnIndex(DEVICETYPEID));
            String state = cursor.getString(cursor.getColumnIndex(STATE));
            int circuitid = cursor.getInt(cursor.getColumnIndex(CIRCUITID));
            String courts = cursor.getString(cursor.getColumnIndex(COURTS));
            String ismanufacturenormal = cursor.getString(cursor.getColumnIndex(ISMANUFACTURENORMAL));
            String location = cursor.getString(cursor.getColumnIndex(LOCATION));

            dataBean.setDeviceId(devid);
            dataBean.setDeviceName(devicename);
            dataBean.setCityId(cityid);
            dataBean.setCountyId(countyid);
            dataBean.setPowerSupplyId(powersupplyid);
            dataBean.setInstalled(isinstalled.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setOnline(isonline.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setUsable(isusable.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setSIMCardOnline(issimcardonline.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setAbnormal(isabnormal.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setPowerFailure(ispowerfailure.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setLongitude(longitude);
            dataBean.setLatitude(latitude);
            dataBean.setAdjustTime(adjusttime);
            dataBean.setVoltageRegulateNormal(isvoltageregulatenormal.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setReactiveCompensationNormal(isreactivecompensationnormal.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setManufacture(manufacture);
            dataBean.setModel(model);
            dataBean.setPhaseTypeId(phasetypeid);
            dataBean.setCapacity(capacity);
            dataBean.setCircuitNormal(iscircuitnormal.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setInstallAddress(installaddress);
            dataBean.setDeviceTypeId(devicetypeid);
            dataBean.setState(state.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setCircuitId(circuitid);
            dataBean.setCourts(courts);
            dataBean.setManufactureNormal(ismanufacturenormal.trim().equalsIgnoreCase("true")?true:false);
            dataBean.setLocation(location);

        }
        return dataBean;
    }

    /**
     * 设备是否存在
     *
     * @param
     * @param usercode
     * @return
     */
    public static boolean isDeviceExist(String usercode) {
        SQLiteDatabase db = instance.getReadableDatabase();

        String sql = "select count(*) from " + CREATE_DEVICEDATA + " where " + USERCODE + " = ? ";
        Log.i(TAG, "[sql]" + sql);

        String[] selectargs = {usercode};
        Cursor cursor = db.rawQuery(sql, selectargs);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        return count > 0 ? true : false;
    }

    /**
     * 删除指定设备
     *
     * @param
     * @param usercode
     */
    public static void delDevice(String usercode) {
        String sql = "delete from " + CREATE_DEVICEDATA + " where " + usercode + " = ? ";
        String[] conditions = new String[]{usercode};
        instance.getWritableDatabase().execSQL(sql, conditions);
    }


}