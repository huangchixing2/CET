package com.electric.cet.mobile.android.pq.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.electric.cet.mobile.android.pq.Bean.DataBean;
import com.electric.cet.mobile.android.pq.Bean.DeviceBean;


public class SQLhelper_Device extends SQLiteOpenHelper implements SQLConfig {

    private static final String TAG = "SQLhelper_Device";

    public SQLhelper_Device(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "[CREATEUSERMANAGER]:" + CREATEDEVICEINFO);
        db.execSQL(CREATEDEVICEINFO);
//        Log.i(TAG, "[CREATERESHOLDER]:" + CREATERESHOLDER);
//        db.execSQL(CREATERESHOLDER);
//        Log.i(TAG, "[CREATERESPUSH]:" + CREATERESPUSH);
//        db.execSQL(CREATERESPUSH);
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS "+TB_DEVICEINFO);
        onCreate(db);
    }

    private static SQLhelper_Device instance;

    public static SQLhelper_Device Instance(Context context) {
        if (instance == null)
            instance = new SQLhelper_Device(context, SQLNAME, null, 3);
        return instance;
    }

    /**
     * 添加设备信息
     *
     *
     * @param deviceInfo
     */
    public static void insertUserInfo(DataBean deviceInfo) {
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
        values.put(CAPACITY, deviceInfo.getCapacity());
        values.put(ISCIRCUITNORMAL, deviceInfo.getCircuitNormal());
        values.put(INSTALLADDRESS, deviceInfo.getInstallAddress());
        values.put(DEVICETYPEID, deviceInfo.getDeviceTypeId());
        values.put(STATE, deviceInfo.getState());
        values.put(CIRCUITID, deviceInfo.getCircuitId());
        values.put(COURTS, deviceInfo.getCourts());
        values.put(ISMANUFACTURENORMAL, deviceInfo.getManufactureNormal());
        values.put(LOCATION, deviceInfo.getLocation());
        db.insert(TB_DEVICEINFO, null, values);
    }

    /**
     * 修改设备信息
     * @param help
     * @param userCode
     * @param userInfo
     */
    public static void updateUserInfo(String userCode,UserVO userInfo){
        SQLiteDatabase db = instance.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERCODE, userInfo.getUuid());
        values.put(USERPWD, userInfo.getPassword());
        values.put(USERNAME, userInfo.getUname());
        values.put(REALNAME, userInfo.getRealname());
        values.put(CLASSCODE, userInfo.getClassId());
        values.put(CLASSNAME, userInfo.getClassName());
        values.put(GRADECODE, userInfo.getUserGradeId());
        values.put(GRADENAME, userInfo.getGradeName());
        values.put(SCHOOLCODE, userInfo.getSchoolId());
        values.put(SCHOOLNAME, userInfo.getSchoolName());
        values.put(CITYCODE, userInfo.getCity());
        values.put(CITYNAME, userInfo.getCityName());
//		values.put(IMAGEURL, userInfo.getImageURL());
//		values.put(LOCALIMAGEURL, userInfo.getLocalImageURL());
        values.put(USERTYPE, userInfo.getUserAttribute());
        values.put(SESSIONID, userInfo.getSessionId());
        db.update(TB_USERMANAGER, values, "userCode=?", new String []{userCode});
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
        Cursor cursor = db.query(TB_DEVICEINFO, null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            dataBean = new DataBean();
//            String code = cursor.getString(cursor.getColumnIndex(CODE));  //需要确定和添加
            int devid = cursor.getInt(cursor.getColumnIndex(DEVICEID));
            String devicename = cursor.getString(cursor.getColumnIndex(DEVICENAME));
            int cityid = cursor.getInt(cursor.getColumnIndex(CITYID));
            int countyid = cursor.getInt(cursor.getColumnIndex(COUNTYID));
            int powersupplyid = cursor.getInt(cursor.getColumnIndex(POWERSUPPLYID));
            Boolean isinstalled = cursor(cursor.getColumnIndex(ISINSTALLED)); //为啥报错？
            String isonline = cursor.getString(cursor.getColumnIndex(ISONLINE));
            String isusable = cursor.getString(cursor.getColumnIndex(ISUSABLE));
            String issimcardonline = cursor.getString(cursor.getColumnIndex(ISSIMCARDONLINE));
            String isabnormal = cursor.getString(cursor.getColumnIndex(ISABNORMAL));
            String ispowerfailure = cursor.getString(cursor.getColumnIndex(ISPOWERFAILURE));
            String longitude = cursor.getString(cursor.getColumnIndex(LONGITUDE));
            String latitude = cursor.getString(cursor.getColumnIndex(LATITUDE));
            String adjusttime = cursor.getString(cursor.getColumnIndex(ADJUSTTIME));
            String isvoltageregulatenormal = cursor.getString(cursor.getColumnIndex(ISVOLTAGEREGULATENORMAL));
            String isreactivecompensationnormal = cursor.getString(cursor.getColumnIndex(ISREACTIVECOMPENSATIONNORMAL));
            String manufacture = cursor.getString(cursor.getColumnIndex(MANUFACTURE));
            String model = cursor.getString(cursor.getColumnIndex(MODEL));
            String capacity = cursor.getString(cursor.getColumnIndex(CAPACITY));
            String iscircuitnormal = cursor.getString(cursor.getColumnIndex(ISCIRCUITNORMAL));
            String installaddress = cursor.getString(cursor.getColumnIndex(INSTALLADDRESS));
            String devicetypeid = cursor.getString(cursor.getColumnIndex(DEVICETYPEID));
            String state = cursor.getString(cursor.getColumnIndex(STATE));
            String circuitid = cursor.getString(cursor.getColumnIndex(CIRCUITID));
            String courts = cursor.getString(cursor.getColumnIndex(COURTS));
            String ismanufacturenormal = cursor.getString(cursor.getColumnIndex(ISMANUFACTURENORMAL));
            String location = cursor.getString(cursor.getColumnIndex(LOCATION));

            dataBean.setDeviceId(devid);


        }
        return dataBean;

    }

    /**
     * 设备是否存在
     *
     * @param help
     * @param usercode
     * @return
     */
    public static boolean isUserExist(String usercode) {
        SQLiteDatabase db = instance.getReadableDatabase();

        String sql = "select count(*) from " + TB_USERMANAGER + " where " + USERCODE + " = ? ";
        Log.i(TAG, "[sql]" + sql);

        String[] selectargs = { usercode };
        Cursor cursor = db.rawQuery(sql, selectargs);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        return count > 0 ? true : false;
    }

    /**
     * 删除指定设备
     *
     * @param help
     * @param usercode
     */
    public static void delSingUser(String usercode) {
        String sql = "delete from " + TB_USERMANAGER + " where " + USERCODE + " = ?";
        String[] conditions = new String[] { usercode };
        instance.getWritableDatabase().execSQL(sql, conditions);
    }


}
