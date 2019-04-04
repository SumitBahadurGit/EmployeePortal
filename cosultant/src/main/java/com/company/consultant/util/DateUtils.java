package com.company.consultant.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtils {

	public static String getCurrentDate(){
		
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
	public static String getDateString(){
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		return date.toString();
	}
	
	public static java.sql.Date getDate(String _date){
		if(StringUtils.isEmpty(_date)){
			return null;
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
		    date = (Date) dateFormat.parse(_date);
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return new java.sql.Date(date.getTime());
	}
}
