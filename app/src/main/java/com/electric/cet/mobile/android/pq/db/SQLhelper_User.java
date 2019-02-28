//package com.electric.cet.mobile.android.pq.db;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import com.sundata.android.ebaghome.pojo.MCloudPushVO;
//import com.sundata.android.ebaghome.pojo.PushVO;
//import com.sundata.android.ebaghome.pojo.UserVO;
//
//public class SQLhelper_User extends SQLiteOpenHelper implements SQLConfig {
//
//    private static final String TAG = "SQLhelper_User";
//
//    public SQLhelper_User(Context context, String name, CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        Log.i(TAG, "[CREATEUSERMANAGER]:" + CREATEUSERMANAGER);
//        db.execSQL(CREATEUSERMANAGER);
//        Log.i(TAG, "[CREATERESHOLDER]:" + CREATERESHOLDER);
//        db.execSQL(CREATERESHOLDER);
//        Log.i(TAG, "[CREATERESPUSH]:" + CREATERESPUSH);
//        db.execSQL(CREATERESPUSH);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
//        db.execSQL("DROP TABLE IF EXISTS " + TB_USERMANAGER);
//        onCreate(db);
//    }
//
//    private static SQLhelper_User instance;
//
//    public static SQLhelper_User Instance(Context context) {
//        if (instance == null)
//            instance = new SQLhelper_User(context, SQLNAME, null, 3);
//        return instance;
//    }
//
//    /**
//     * 添加用户
//     *
//     * @param help
//     * @param userInfo
//     */
//    public static void insertUserInfo(UserVO userInfo) {
//        SQLiteDatabase db = instance.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(USERCODE, userInfo.getUuid());
//        values.put(USERPWD, userInfo.getPassword());
//        values.put(USERNAME, userInfo.getUname());
//        values.put(REALNAME, userInfo.getRealname());
//        values.put(CLASSCODE, userInfo.getClassId());
//        values.put(CLASSNAME, userInfo.getClassName());
//        values.put(GRADECODE, userInfo.getUserGradeId());
//        values.put(GRADENAME, userInfo.getGradeName());
//        values.put(SCHOOLCODE, userInfo.getSchoolId());
//        values.put(SCHOOLNAME, userInfo.getSchoolName());
//        values.put(CITYCODE, userInfo.getCity());
//        values.put(CITYNAME, userInfo.getCityName());
//        // values.put(IMAGEURL, userInfo.getImageURL());
//        // values.put(LOCALIMAGEURL, userInfo.getLocalImageURL());
//        values.put(USERTYPE, userInfo.getUserAttribute());
//        values.put(SESSIONID, userInfo.getSessionId());
//        db.insert(TB_USERMANAGER, null, values);
//    }
//
//    /**
//     * 修改用户
//     *
//     * @param help
//     * @param userCode
//     * @param userInfo
//     */
//    public static void updateUserInfo(String userCode, UserVO userInfo) {
//        SQLiteDatabase db = instance.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(USERCODE, userInfo.getUuid());
//        values.put(USERPWD, userInfo.getPassword());
//        values.put(USERNAME, userInfo.getUname());
//        values.put(REALNAME, userInfo.getRealname());
//        values.put(CLASSCODE, userInfo.getClassId());
//        values.put(CLASSNAME, userInfo.getClassName());
//        values.put(GRADECODE, userInfo.getUserGradeId());
//        values.put(GRADENAME, userInfo.getGradeName());
//        values.put(SCHOOLCODE, userInfo.getSchoolId());
//        values.put(SCHOOLNAME, userInfo.getSchoolName());
//        values.put(CITYCODE, userInfo.getCity());
//        values.put(CITYNAME, userInfo.getCityName());
//        // values.put(IMAGEURL, userInfo.getImageURL());
//        // values.put(LOCALIMAGEURL, userInfo.getLocalImageURL());
//        values.put(USERTYPE, userInfo.getUserAttribute());
//        values.put(SESSIONID, userInfo.getSessionId());
//        db.update(TB_USERMANAGER, values, "userCode=?", new String[] { userCode });
//    }
//
//    /**
//     * 查询用户
//     *
//     * @param help
//     * @param userCode
//     * @return
//     */
//    public static UserVO queryUserInfo() {
//        SQLiteDatabase db = instance.getReadableDatabase();
//        UserVO user = null;
//        Cursor cursor = db.query(TB_USERMANAGER, null, null, null, null, null, null);
//        if (cursor.moveToNext()) {
//            user = new UserVO();
//            String ucode = cursor.getString(cursor.getColumnIndex(USERCODE));
//            String upwd = cursor.getString(cursor.getColumnIndex(USERPWD));
//            String username = cursor.getString(cursor.getColumnIndex(USERNAME));
//            String realname = cursor.getString(cursor.getColumnIndex(REALNAME));
//            String ccode = cursor.getString(cursor.getColumnIndex(CLASSCODE));
//            String cname = cursor.getString(cursor.getColumnIndex(CLASSNAME));
//            String gcode = cursor.getString(cursor.getColumnIndex(GRADECODE));
//            String gname = cursor.getString(cursor.getColumnIndex(GRADENAME));
//            String scode = cursor.getString(cursor.getColumnIndex(SCHOOLCODE));
//            String sname = cursor.getString(cursor.getColumnIndex(SCHOOLNAME));
//            String ctcode = cursor.getString(cursor.getColumnIndex(CITYCODE));
//            String ctname = cursor.getString(cursor.getColumnIndex(CITYNAME));
//            // String imageURL =
//            // cursor.getString(cursor.getColumnIndex(IMAGEURL));
//            // String localImageURL =
//            // cursor.getString(cursor.getColumnIndex(LOCALIMAGEURL));
//            String usertype = cursor.getString(cursor.getColumnIndex(USERTYPE));
//            String sessionId = cursor.getString(cursor.getColumnIndex(SESSIONID));
//
//            user.setUuid(ucode);
//            user.setPassword(upwd);
//            user.setUname(username);
//            user.setClassId(ccode);
//            user.setClassName(cname);
//            user.setUserGradeId(gcode);
//            user.setGradeName(gname);
//            user.setSchoolId(scode);
//            user.setSchoolName(sname);
//            user.setCity(ctcode);
//            user.setCityName(ctname);
//            user.setUserAttribute(usertype);
//            user.setSessionId(sessionId);
//            user.setRealname(realname);
//        }
//        return user;
//
//    }
//
//    /**
//     * 用户是否存在
//     *
//     * @param help
//     * @param usercode
//     * @return
//     */
//    public static boolean isUserExist(String usercode) {
//        SQLiteDatabase db = instance.getReadableDatabase();
//
//        String sql = "select count(*) from " + TB_USERMANAGER + " where " + USERCODE + " = ? ";
//        Log.i(TAG, "[sql]" + sql);
//
//        String[] selectargs = { usercode };
//        Cursor cursor = db.rawQuery(sql, selectargs);
//        cursor.moveToFirst();
//        int count = cursor.getInt(0);
//        return count > 0 ? true : false;
//    }
//
//    /**
//     * 删除指定用户
//     *
//     * @param help
//     * @param usercode
//     */
//    public static void delSingUser(String usercode) {
//        String sql = "delete from " + TB_USERMANAGER + " where " + USERCODE + " = ?";
//        String[] conditions = new String[] { usercode };
//        instance.getWritableDatabase().execSQL(sql, conditions);
//    }
//
//    /**
//     * 添加持有人信息
//     *
//     * @param help
//     * @param holdercode
//     * @param holdername
//     * @param classCode
//     * @param fileid
//     * @param filename
//     * @param filetype
//     * @param restype
//     * @param date
//     */
//    public static void insertResHolder(String holdercode, String holdername, String classCode, String fileid,
//            String filename, String filetype, String restype, String date) {
//        if (isExistResHolder(holdercode, fileid))
//            return;
//
//        SQLiteDatabase db = instance.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(HOLDERCODE, holdercode);
//        values.put(HOLDERNAME, holdername);
//        values.put(CLASSCODE, classCode);
//        values.put(FILEID, fileid);
//        values.put(FILENAME, filename);
//        values.put(FILETYPE, filetype);
//        values.put(RESTYPE, restype);
//        values.put(DATE, date);
//
//        db.insert(TB_RESHOLDER, null, values);
//    }
//
//    /**
//     * 查询此条记录是否存在 holder表
//     *
//     * @param holdercode
//     * @param fileid
//     */
//    public static boolean isExistResHolder(String holdercode, String fileid) {
//        SQLiteDatabase db = instance.getReadableDatabase();
//
//        String sql = "select count(*) from  " + TB_RESHOLDER + " where " + HOLDERCODE + " = ? " + " and " + FILEID
//                + " = ?";
//        Log.i(TAG, "[sql]:" + sql);
//
//        String[] selectargs = { holdercode, fileid };
//        Cursor cursor = db.rawQuery(sql, selectargs);
//        if (cursor != null) {
//            cursor.moveToFirst();
//            int count = cursor.getInt(0);
//            return count > 0 ? true : false;
//        }
//        return false;
//    }
//
//    /**
//     * 修改持有人单列信息
//     *
//     * @param help
//     */
//    public static void updateResHolder(String holdercode, String holdername, String classCode, String fileid,
//            String filename, String filetype, String restype, String date) {
//        SQLiteDatabase db = instance.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(HOLDERCODE, holdercode);
//        values.put(HOLDERNAME, holdername);
//        values.put(CLASSCODE, classCode);
//        values.put(FILEID, fileid);
//        values.put(FILENAME, filename);
//        values.put(FILETYPE, filetype);
//        values.put(RESTYPE, restype);
//        values.put(DATE, date);
//
//        db.update(TB_RESHOLDER, values, HOLDERCODE + " = ? " + " and " + FILEID + " = ? ", new String[] { holdercode,
//                fileid });
//    }
//
//    /**
//     * 删除 指定文件id的 持有人列表的 单列信息
//     *
//     * @param help
//     * @param usercode
//     */
//    public static void delSingResHolder(String holdercode, String fileid) {
//        String sql = "delete from " + TB_RESHOLDER + " where " + HOLDERCODE + " = ? " + " and " + FILEID + " = ?";
//        String[] conditions = new String[] { holdercode, fileid };
//        instance.getWritableDatabase().execSQL(sql, conditions);
//    }
//
//    /**
//     * 添加 推送资料信息
//     *
//     * @param mcloudpushvo
//     * @param localcoverurl
//     * @param localfile
//     */
//    public static void insertResPush(MCloudPushVO mcloudpushvo, String localcoverurl, String localfile) {
//        if (insertResPush(mcloudpushvo.getId()))
//            return;
//
//        SQLiteDatabase db = instance.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(FILEID, mcloudpushvo.getFileid());
//        values.put(FILENAME, mcloudpushvo.getFileName());
//        values.put(COVERURL, mcloudpushvo.getShowUrl());
//        values.put(SUBJECTCODE, mcloudpushvo.getSubjectCode());
//        values.put(SUBJECTNAME, mcloudpushvo.getSubjectName());
//        values.put(RESURL, mcloudpushvo.getUrl());
//        values.put(RELEASERCODE, mcloudpushvo.getUid());
//        values.put(RELEASERNAME, mcloudpushvo.getUserName());
//        values.put(RELEASEDATE, mcloudpushvo.getUploadTime());
//        values.put(FILETYPE, mcloudpushvo.getExt());
//        values.put(FILESIZE, mcloudpushvo.getFileSize());
//        values.put(CLASSCODE, mcloudpushvo.getClassId());
//        values.put(DOWNLOADSTATE, mcloudpushvo.getDownload());
//        values.put(RESTYPE, mcloudpushvo.getTypeId());
//        values.put(LOCALCOVERURL, localcoverurl);
//        values.put(LOCALRESURL, localfile);
//
//        db.insert(TB_RESPUSH, null, values);
//    }
//
//    /**
//     * 查询此条记录是否存在 respush表
//     *
//     * @param fileid
//     */
//    public static boolean insertResPush(String fileid) {
//        SQLiteDatabase db = instance.getReadableDatabase();
//
//        String sql = "select count(*) from  " + TB_RESPUSH + " where " + FILEID + " = ?";
//        Log.i(TAG, "[sql]:" + sql);
//
//        String[] selectargs = { fileid };
//        Cursor cursor = db.rawQuery(sql, selectargs);
//        if (cursor != null) {
//            cursor.moveToFirst();
//            int count = cursor.getInt(0);
//            return count > 0 ? true : false;
//        }
//        return false;
//    }
//
//    /**
//     * 修改推送资料信息
//     *
//     * @param help
//     */
//    public static void updateResPush(MCloudPushVO mcloudpushvo, String fileid, String localcoverurl, String localresurl) {
//        SQLiteDatabase db = instance.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(FILEID, mcloudpushvo.getFileid());
//        values.put(FILENAME, mcloudpushvo.getFileName());
//        values.put(COVERURL, mcloudpushvo.getShowUrl());
//        values.put(SUBJECTCODE, mcloudpushvo.getSubjectCode());
//        values.put(SUBJECTNAME, mcloudpushvo.getSubjectName());
//        values.put(RESURL, mcloudpushvo.getUrl());
//        values.put(RELEASERCODE, mcloudpushvo.getUid());
//        values.put(RELEASERNAME, mcloudpushvo.getUserName());
//        values.put(RELEASEDATE, mcloudpushvo.getUploadTime());
//        values.put(FILETYPE, mcloudpushvo.getExt());
//        values.put(FILESIZE, mcloudpushvo.getFileSize());
//        values.put(CLASSCODE, mcloudpushvo.getClassId());
//        values.put(DOWNLOADSTATE, mcloudpushvo.getDownload());
//        values.put(RESTYPE, mcloudpushvo.getTypeId());
//        values.put(LOCALCOVERURL, localcoverurl);
//        values.put(LOCALRESURL, localresurl);
//
//        db.update(TB_RESPUSH, values, FILEID + " = ?", new String[] { fileid });
//    }
//
//    /**
//     * 删除 指定文件id的 推送资料信息
//     *
//     * @param help
//     * @param usercode
//     */
//    public static void delSingResPush(String fileid) {
//        String sql = "delete from " + TB_RESPUSH + " where " + FILEID + " = ? ";
//        String[] conditions = new String[] { fileid };
//        instance.getWritableDatabase().execSQL(sql, conditions);
//    }
//
//    /**
//     * 查询holder表中本人的资源id
//     *
//     * @return
//     */
//    public static List<String> selectFileID(String holdercode) {
//        SQLiteDatabase db = instance.getReadableDatabase();
//
//        List<String> fileids = new ArrayList<String>();
//
//        String sql = "select " + FILEID + " from " + TB_RESHOLDER + " where " + HOLDERCODE + " = ? ";
//        Log.i(TAG, "sql:" + sql);
//
//        String[] selectargs = { holdercode };
//        Cursor cursor = db.rawQuery(sql, selectargs);
//        Log.i(TAG, "cursor:" + (cursor == null));
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                String fileid = cursor.getString(cursor.getColumnIndex(FILEID));
//                fileids.add(fileid);
//            }
//        }
//
//        return fileids;
//    }
//
//    /**
//     * 查询本人的推送资料资源
//     *
//     * @return
//     */
//    public static List<PushVO> selectMyPushRes(String holdercode, String subjectCode) {
//
//        SQLiteDatabase db = instance.getReadableDatabase();
//
//        List<PushVO> list = new ArrayList<PushVO>();
//
//        String sql = "select * from " + TB_RESHOLDER + "," + TB_RESPUSH + " where " + TB_RESHOLDER + "." + FILEID
//                + " = " + TB_RESPUSH + "." + FILEID + " and " + HOLDERCODE + " = ?" + " and " + SUBJECTCODE + " = ?";
//
//        String allsql = "select * from " + TB_RESHOLDER + "," + TB_RESPUSH + " where " + TB_RESHOLDER + "." + FILEID
//                + " = " + TB_RESPUSH + "." + FILEID + " and " + HOLDERCODE + " = ?";
//
//        Log.i(TAG, "sql:" + sql);
//
//        String[] selectargs = { holdercode, subjectCode };
//        String[] allselectargs = { holdercode };
//
//        if (subjectCode == null || "".equals(subjectCode)) {
//            sql = allsql;
//            selectargs = allselectargs;
//        }
//
//        Cursor cursor = db.rawQuery(sql, selectargs);
//
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                String fileid = cursor.getString(cursor.getColumnIndex(FILEID));
//                String filename = cursor.getString(cursor.getColumnIndex(FILENAME));
//                String coverurl = cursor.getString(cursor.getColumnIndex(COVERURL));
//                String subjectcode = cursor.getString(cursor.getColumnIndex(SUBJECTCODE));
//                String subjectname = cursor.getString(cursor.getColumnIndex(SUBJECTNAME));
//                String resurl = cursor.getString(cursor.getColumnIndex(RESURL));
//                String releasercode = cursor.getString(cursor.getColumnIndex(RELEASERCODE));
//                String releasername = cursor.getString(cursor.getColumnIndex(RELEASERNAME));
//                String releaserdate = cursor.getString(cursor.getColumnIndex(RELEASEDATE));
//                String filetype = cursor.getString(cursor.getColumnIndex(FILETYPE));
//                String filesize = cursor.getString(cursor.getColumnIndex(FILESIZE));
//                String classCode = cursor.getString(cursor.getColumnIndex(CLASSCODE));
//                String downloadstate = cursor.getString(cursor.getColumnIndex(DOWNLOADSTATE));
//                String restype = cursor.getString(cursor.getColumnIndex(RESTYPE));
//                String localcoverurl = cursor.getString(cursor.getColumnIndex(LOCALCOVERURL));
//                String localresurl = cursor.getString(cursor.getColumnIndex(LOCALRESURL));
//
//                list.add(new PushVO(fileid, filename, coverurl, subjectcode, subjectname, resurl, releasercode,
//                        releasername, releaserdate, filetype, filesize, classCode, downloadstate, restype,
//                        localcoverurl, localresurl));
//            }
//        }
//
//        return list;
//    }
//
//}
