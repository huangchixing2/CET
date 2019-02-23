package com.electric.cet.mobile.android.pq.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期格式化工具类
 * @author cz
 *
 */
public class DateUtil {

	/**
	 * long 格式化  年 月  日  时  分  秒
	 * @param date
	 * @return
	 */
	public static String formatDateDT(long date) {
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(currentdate);
	}
	
	/**
	 * long 格式化  年 月  日  时  分  
	 * @param date
	 * @return
	 */
	public static String formatDateDM(long date) {
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(currentdate);
	}

	/**
	 * long 格式化  年 月  日
	 * @param date
	 * @return
	 */
	public static String formatDateD(long date) {
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(currentdate);
	}
	
	/**
	 * long 格式化  月  日  时  分
	 * @param date
	 * @return
	 */
	public static String formatDateMD(long date) {
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日  HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(currentdate);
	}
	
	/**
	 * long 格式化  年 月  日
	 * @param date
	 * @return
	 */
	public static String formatDateDN(long date) {
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(currentdate);
	}
	
	/**
	 * long 格式化 月  日
	 * @param date
	 * @return
	 */
	public static String formatDateMday(long date) {
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(currentdate);
	}

	
	/**
	 * long 格式化时分
	 * @param date
	 * @return
	 */
	public static String formatDateMM(long date) {
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(currentdate);
	}
	
	/**
	 * 组织成字符串  2013年10月22日
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String formatPickerDate(int year, int month, int day) {

		month++;
		StringBuilder formatStr = new StringBuilder();
		formatStr.append(year);
		formatStr.append("年");
		if (month < 10) {
			formatStr.append(0);
		}
		formatStr.append(month);
		formatStr.append("月");
		if (day < 10) {
			formatStr.append(0);
		}
		formatStr.append(day);
		formatStr.append("日");

		return formatStr.toString();
	}
	
	/**
	 * 格林威治时间转现在时间
	 * @param date
	 * @return
	 */
	public static String fromGreenwichtoNow(long date)
	{
		return formatDateDM(date * 1000);
	}
	
	/**
	 * 获取年
	 * @param date
	 * @return
	 */
	public static String getYear(long date)
	{
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datetime = sdf.format(currentdate);
		String year = datetime.substring(0, datetime.indexOf("-"));
		return year;
	}
	
	/**
	 * 获取月
	 * @param date
	 * @return
	 */
	public static String getMonth(long date)
	{
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datetime = sdf.format(currentdate);
//		String  month = datetime.substring(datetime.indexOf("-")+1, datetime.lastIndexOf("-"));
		return datetime;
	}
	
	/**
	 * 获取日
	 * @param date
	 * @return
	 */
	public static String getDay(long date)
	{
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datetime = sdf.format(currentdate);
//		String day = datetime.substring(datetime.lastIndexOf("-")+1, datetime.length());
		return datetime;
	}
	
	/**
	 * 获取  几时
	 * @param date
	 * @return
	 */
	public static int getHours(long date)
	{
		Date currentdate = new Date(date * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm-ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datetime = sdf.format(currentdate);
		String hours = datetime.substring(datetime.indexOf(":")+1, datetime.lastIndexOf(":"));
		return Integer.parseInt(hours);
	}
	
	
	/**
	 * 比较日期是否一致
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isSameDay(long date1,long date2)
	{
		return formatDateDN(date1).equals(formatDateDN(date2)) ? true :false;
	}
}
