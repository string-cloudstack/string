package com.pft.string.service.api.commonutil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Converters {

	public Converters() {
		// TODO Auto-generated constructor stub
	}
	
	public static Date toDate(String startDateString) {
		DateFormat df = new SimpleDateFormat(("MM/dd/yyyy HH:mm:ss"));
		Date startDate = null;
		try {
			startDate = df.parse(startDateString);
			//String newDateString = df.format(startDate);
			//System.out.println(newDateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startDate;
	}

	public static Timestamp toSqlTimestamp(Date UtilDate) {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(UtilDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timestamp;
	}
	
}
