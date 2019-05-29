package it.beije.timesheet.entities.methods;

import java.sql.Date;
import java.sql.Time;

import it.beije.timesheet.entities.Timetable;

public class Run {

	public static void main(String[] args) {
		Timetable ciao = new Timetable();
		
		
		ciao.setId_user(1);
		ciao.setId(1);
		ciao.setType('w');

//		ciao.setEnd1(LocalTime.of(12, 00));
//		ciao.setStart2(LocalTime.of(13, 00));
//		ciao.setEnd2(LocalTime.of(17, 00));
//		ciao.setTot(8.00);
		
		TimeSheetMethods.setOrUpdateTimeSheet(ciao);
		
		
		
	}

}
