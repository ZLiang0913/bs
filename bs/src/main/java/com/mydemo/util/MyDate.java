package com.mydemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
 

public class MyDate {
 
    /**
     * 求出该日期是一年中的第几周
     * @param today
     * @return
     */
    public static int getWeek(String today) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
    	Date date = null;  
    	try {  
    	    date = format.parse(today);  
    	} catch (ParseException e) {  
    	    // TODO Auto-generated catch block  
    	    e.printStackTrace();  
    	}  
    	  
    	Calendar calendar = Calendar.getInstance();  
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);  
    	calendar.setTime(date);  
    	  
    	return calendar.get(Calendar.WEEK_OF_YEAR);
    }
 
 
}
 
