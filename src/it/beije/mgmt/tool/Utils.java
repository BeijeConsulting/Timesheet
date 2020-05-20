package it.beije.mgmt.tool;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	public static String formatDate(Date date) {
		return date != null ? dateFormat.format(date) : null;
	}
	
	public static Date parseDate(String date) throws ParseException {
		if (date == null) return null;
		
		java.util.Date d = dateFormat.parse(date);
		return new Date(d.getTime());
	}

	public static String formatTime(Time time) {
		return time != null ? timeFormat.format(time) : null;
	}
	
	public static Time parseTime(String time) throws ParseException {
		if (time == null) return null;
		
		java.util.Date d = timeFormat.parse(time);
		return new Time(d.getTime());
	}

//	private String toHHmm(String time) {
//		return (time != null && time.length() > 5) ? time.substring(0, 5) : time;
//	}

}
