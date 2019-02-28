package com.electric.cet.mobile.android.pq.db;

public interface SQLConfig {

	public static final String TB_USERMANAGER 	= "usermanager";
	public static final String TB_RESHOLDER	    = "resholder";
	public static final String TB_RESPUSH	    = "respush";
	public static final String SQLNAME			= "sundata_ebag_user.db";
	
	public static final String ID 			    = "_id";
	public static final String USERCODE         = "userCode";
	public static final String USERPWD          = "userPwd";
	public static final String USERNAME 		= "userName";
	public static final String REALNAME 		= "realName";
	public static final String CLASSCODE 		= "classCode";
	public static final String CLASSNAME		= "className";
	public static final String GRADECODE 		= "gradeCode";
	public static final String GRADENAME 		= "gradeName";
	public static final String SCHOOLCODE		= "schoolCode";
	public static final String SCHOOLNAME		= "schoolName";
	public static final String CITYCODE 		= "cityCode";
	public static final String CITYNAME 		= "cityName";
	public static final String IMAGEURL 		= "imageURL";
	public static final String LOCALIMAGEURL 	= "localImageURL";
	public static final String USERTYPE 		= "userType";
	public static final String SESSIONID 		= "sessionId";
	
	
	public static final String HOLDERCODE		= "holdercode";
	public static final String HOLDERNAME		= "holdername";
	public static final String FILEID			= "fileid";
	public static final String DATE				= "date";
	
	public static final String FILENAME			= "filename";
	public static final String SUBJECTCODE		= "subjectcode";
	public static final String SUBJECTNAME		= "subjectname";
	public static final String FILETYPE			= "filetype";
	public static final String FILESIZE			= "filesize";
	public static final String FILEPATH			= "filepath";
	public static final String FILEHOLDER		= "fileholder";
	public static final String COVERURL		    = "coverurl";
	public static final String RESTYPE		    = "restype";
	public static final String DOWNLOADSTATE	= "downloadstate";
	
	public static final String RELEASERCODE	    = "releasercode";
	public static final String RELEASERNAME	    = "releasername";
	public static final String RELEASEDATE	    = "releaserdate";
	public static final String RESURL	    	= "resurl";
	
	public static final String LOCALCOVERURL	= "localcoverurl";
	public static final String LOCALRESURL	    = "localresurl";
	
	
	
	

	public static final String CREATEUSERMANAGER = "CREATE TABLE IF NOT EXISTS "
			+ TB_USERMANAGER
			+ " ("
			+ ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ USERCODE
			+ " VARCHAR NULL,"
			+ USERPWD
			+ " VARCHAR NULL,"
			+ USERNAME
			+ " VARCHAR NULL,"
			+ CLASSCODE
			+ " VARCHAR NULL,"
			+ CLASSNAME
			+ " VARCHAR NULL,"
			+ REALNAME
			+ " VARCHAR NULL,"
			+ GRADECODE
			+ " VARCHAR NULL,"
			+ GRADENAME
			+ " VARCHAR NULL,"
			+ SCHOOLCODE
			+ " VARCHAR NULL,"
			+ SCHOOLNAME
			+ " VARCHAR NULL,"
			+ CITYCODE
			+ " VARCHAR NULL,"
			+ CITYNAME
			+ " VARCHAR NULL,"
			+ IMAGEURL
			+ " VARCHAR NULL,"
			+ LOCALIMAGEURL
			+ " VARCHAR NULL,"
			+ SESSIONID
			+ " VARCHAR NULL," + USERTYPE + " INT DEFAULT 0 " + ")";
	
	//holder表
	public static final String CREATERESHOLDER = "CREATE TABLE IF NOT EXISTS "
			+ TB_RESHOLDER
			+ " ("
			+ ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ HOLDERCODE
			+ " VARCHAR NULL,"
			+ HOLDERNAME
			+ " VARCHAR NULL,"
			+ CLASSCODE
			+ " VARCHAR NULL,"
			+ FILEID
			+ " VARCHAR NULL,"
			+ FILENAME
			+ " VARCHAR NULL,"
			+ FILETYPE
			+ " VARCHAR NULL,"
			+ RESTYPE
			+ " VARCHAR NULL,"
			+ DATE
			+ " VARCHAR NULL"
			+ ")"
			;
	
	//推送资源表
	public static final String CREATERESPUSH = "CREATE TABLE IF NOT EXISTS "
			+ TB_RESPUSH
			+ " ("
			+ ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ FILEID
			+ " VARCHAR NULL,"
			+ FILENAME
			+ " VARCHAR NULL,"  
			+ COVERURL
			+ " VARCHAR NULL," 
			+ SUBJECTCODE
			+ " VARCHAR NULL,"
			+ SUBJECTNAME
			+ " VARCHAR NULL,"
			+ RESURL
			+ " VARCHAR NULL,"
			+ RELEASERCODE
			+ " VARCHAR NULL,"
			+ RELEASERNAME
			+ " VARCHAR NULL,"
			+ RELEASEDATE
			+ " VARCHAR NULL,"
			+ FILETYPE
			+ " VARCHAR NULL,"
			+ FILESIZE
			+ " VARCHAR NULL,"
			+ CLASSCODE
			+ " VARCHAR NULL,"
			+ DOWNLOADSTATE
			+ " VARCHAR NULL,"
			+ RESTYPE
			+ " VARCHAR NULL,"
			+ LOCALCOVERURL
			+ " VARCHAR NULL,"
			+ LOCALRESURL
			+ " VARCHAR NULL"
			+ ")"
			;
}
