package com.company.consultant.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

	public static String getCurrentDate(){
		
		String timeStamp = new SimpleDateFormat("mm/dd/yyyy").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
}
