package it.beije.timesheet.entities.methods;

import java.time.LocalDate;
import java.time.LocalTime;

import it.beije.timesheet.entities.Timetable;

public class Run {

	public static void main(String[] args) {
		Timetable ciao = new Timetable();
		
		
		ciao.setId_user(1);
		ciao.setId(1);
		ciao.setType('w');
		ciao.setDate(LocalDate.of(2015, 2, 1));
		ciao.setStart1(LocalTime.of(9, 00));
		ciao.setEnd1(LocalTime.of(12, 00));
		ciao.setStart2(LocalTime.of(13, 00));
		ciao.setEnd2(LocalTime.of(17, 00));
		ciao.setTot(8.00);
		
		TimeSheetMethods.setOrUpdateTimeSheet(ciao);
		
		
		
	}

}
