package com.vn.hcmute.team.cortana.mymoney.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

	public static long getMilisecondFromDate(String date) {
		Calendar calendar = Calendar.getInstance();
		Date date1;
		try {
			date1 = DATE_FORMAT.parse(date);
			calendar.setTime(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return calendar.getTimeInMillis();
	}

}
