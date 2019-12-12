package it.beije;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String formatDate(Date date) {
		return date != null ? sdf.format(date) : null;
	}
	
	public static Date parseDate(String date) throws ParseException {
		if (date == null) return null;
		
		java.util.Date d = sdf.parse(date);
		return new Date(d.getTime());
	}

}
