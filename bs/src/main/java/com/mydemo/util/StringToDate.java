package com.mydemo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StringToDate {
	
	/**
	 * 字符串转时间，格式为"yyyy-MM-dd"<br>
	 * "2017-04-02 14:24:43" -> Sun Apr 02 00:00:00 GMT+08:00 2017
	 * @param str
	 * @return
	 */
	public static Date turnDate(String str,String dateFormat) {
		
		DateFormat fmt =new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = fmt.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 时间转字符串，格式为"yyyy-MM-dd"<br>
	 * @param str
	 * @return
	 */
	public static String dateToString(Date date,String dateFormat) {
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);  
		String str=sdf.format(date);
		return str;
	}
	
	/**
     * 日期转星期
     * 
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime,String dateFormat) {
        SimpleDateFormat f = new SimpleDateFormat(dateFormat);
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    /**
     * 获取两个时间的时间差，以分钟为单位
     * @param endDate
     * @param nowDate
     * @return
     */
    public static Long getDateDiff(Date endDate, Date nowDate) {
    	// 获得两个时间的分钟时间差异
    	long nm = 60000;
    	// 获得两个时间的毫秒时间差异
    	long diff = endDate.getTime() - nowDate.getTime();
    	// 计算差多少分钟
    	long min = diff / nm;
    	return min;
    }
    public static void main(String[] args) {
    	//System.out.println(getDateDiff(turnDate("2017-04-02 15:28:17","yyyy-MM-dd HH:mm:ss"),turnDate("2017-04-02 14:24:43.0","yyyy-MM-dd HH:mm:ss")));
    	System.out.println(dateToString(turnDate("2017-04-02 14:24:43.0","yyyy-MM-dd HH:mm:ss"),"HH"));
    	//System.out.println(turnDate("2017-04-02 14:24:43.0","yyyy-MM-dd HH:mm:ss"));
    	//System.out.println(new Date("2017-04-02"));
    	//System.out.println(dateToString(new Date()));
    	System.out.println(25%24);
    	String line = "7214545,2017-04-02 14:24:43.0,2017-04-02 15:28:17.0";
    	String[] arrays = line.split(",");
		List<String> days=new ArrayList<>();
		List<String> hours=new ArrayList<>();
		for (int i = 1; i < arrays.length; i++) {
			 String[] split = arrays[i].split(" ");
			 days.add(split[0]);
			 hours.add(split[1].substring(0, 2));
		}
		
//		2017-04-02
//		14:24:43.0
//		2017-04-02
//		15:28:17.0
		for (String string : hours) {
			System.out.println(string);
		}
    	System.out.println(dateToWeek("2019-01-13", "yyyy-MM-dd"));
    	
    	System.out.println(String.format("%02d",1));
    	
    	
    	String string="23*1";
    	String[] split = string.split("\\*");
    	System.out.println(split[0]+","+split[1]);
    	
	}

}
