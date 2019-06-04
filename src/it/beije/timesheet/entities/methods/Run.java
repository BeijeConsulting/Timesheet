package it.beije.timesheet.entities.methods;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

import it.beije.timesheet.entities.Timetable;
import it.beije.timesheet.entities.User;

public class Run {
	 
	
	
	public static void main(String[] args) {
//		User verifica = TimeSheetMethods.findRecordsFromId(1);
		List <Timetable> record = new ArrayList <Timetable> ();
		
//		if (verifica==null)  {
//			System.out.println("nessun utente");
//		}
//		else  {
//			System.out.println(verifica.getFirst_name());
//		}
//		record = TimeSheetMethods.takeRecordsTimetablVersion();
//		
//		if (record!=null)  {
//			for (Timetable r: record)  {
//				System.out.println(r.getId_user());
//				System.out.println(r.getDate());
//				System.out.println(r.getType());
//				System.out.println(r.getStart1());
//				System.out.println(r.getEnd1());
//				System.out.println(r.getStart2());
//				System.out.println(r.getEnd2());
//			}
//		}
//	
		
//		record=TimeSheetMethods.takeRecordsFromIdTimetableVersion(3);
		
//		if (record!=null)  {
//			for (Timetable r: record)  {
//				System.out.println(r.getId_user());
//				System.out.println(r.getDate());
//				System.out.println(r.getType());
//				System.out.println(r.getStart1());
//				System.out.println(r.getEnd1());
//				System.out.println(r.getStart2());
//				System.out.println(r.getEnd2());
//			}
//		}
	
		LocalDate d2 = LocalDate.of(2015, 02, 01);
		Date d = Date.valueOf(d2);	
	
		
		record = TimeSheetMethods.takeRecordsFromDate(d);
		if (record!=null)  {
			for (Timetable r: record)  {
				System.out.println(r.getId_user());
				System.out.println(r.getDate());
				System.out.println(r.getType());
				System.out.println(r.getStart1());
				System.out.println(r.getEnd1());
				System.out.println(r.getStart2());
				System.out.println(r.getEnd2());
			}
		}
		else {
			System.out.println("Dati non presenti");
		}
			
	
	
	}
	
	

}
