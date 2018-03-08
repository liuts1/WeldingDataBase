package com.gy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	/**
	 * 对日期进行格式化
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 对日期进行格式化
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ServiceException
	 */
	public static Date parseDateStr(String dateStr, String pattern)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			throw new Exception("100003", e);
		}
	}
	/***
	 * 传入DATE时间计算出二个时间相差的时间天数 
	 * @param fDate  
	 * @param oDate
	 * @return  如果为-1代表有一个时间为空 
	 * @姓名:xiaoting
	 * @时间 2015-11-4 下午8:41:50
	 */
	public static int getIntervalDays(Date fDate, Date oDate) {

		if (null == fDate || null == oDate) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	}

	/**
	 * 根据Date时间计算相差天数，小时数，分钟数
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static String getDateInterval(Date sDate, Date eDate){
		long day = 1000 * 24 * 60 * 60;
		long hour = 1000 * 60 * 60;
		long minute = 1000 * 60;
		long intervalMilli = eDate.getTime() - sDate.getTime();//两时间相差毫秒数
		long _day = intervalMilli / day;//相差天数
		long _hour = intervalMilli / hour;//相差小时数
		long _minute = intervalMilli / minute;//相差分钟数
		if(_minute < 60){
			if(_minute==0){
				return String.valueOf(1) + "分钟";
			}else{
				return String.valueOf(_minute) + "分钟";
			}
		}else if(_hour < 24 && _hour > 0){
			return String.valueOf(_hour) + "小时";
		}else if(_day >= 1){
			return String.valueOf(_day) + "天";
		}
		return null;
	}

    public static String queryDateSpace(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算相差多少天
        long day = diff / nd;
        // 计算相差多少小时
        long hour = diff % nd / nh;
        // 计算相差多少分钟
        long min = diff % nd % nh / nm;
        // 计算相差多少秒//输出结果
        return day + "天" + hour + "小时" + min + "分钟";
    }

    public static String queryDateSpacedDay(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算相差多少天
        long day = diff / nd;
        // 计算相差多少小时
        long hour = diff % nd / nh;
        // 计算相差多少分钟
        long min = diff % nd % nh / nm;
        // 计算相差多少秒//输出结果
        return day + "天";
    }

}
