package com.electric.cet.mobile.android.pq.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtils extends SQLiteOpenHelper {



    final String CREATE_TABLE_SQL = "Create table deviceInfo(_id interger primary " + "key autoincrement,DeviceId, DeviceName,CityId,CountyId, " +
            "IsInstalled,IsOnline, IsUsable, IsSIMCardOnline, IsAbnormal, IsPowerFailure, Longitude, Latitude, AdjustTime, IsVoltageRegulateNormal" +
            "IsReactiveCompensationNormal, Manufacture, Model, PhaseTypeId, Capacity, IsCircuitNormal, InstallAddress, DeviceTypeId, State, CircuitId" +
            "Courts, IsManufactureNormal, Location)";

    public DBUtils(Context context){
        super(context,"deviceInfo.db",null, 1);
    }
    /**
     * Called when the database is created for the first time.
     * 当数据库第一次创建的时候调用
     * 那么这个方法特别适合做表结构的初始化  创建表就是写sql语句
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    /**
     * Called when the database needs to be upgraded
     * 当数据库版本升级的时候调用
     *
     * 这个方法适合做   表结构的更新
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table deviceInfo add account varchar(29)");
    }
}
