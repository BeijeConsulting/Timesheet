package it.beije.timesheet.entities.methods;

import java.time.*;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import it.beije.timesheet.entities.Timetable;

public class TimeSheetMethods {
	
	//Get Timesheet of a User By ID
	public static List<Timetable> getTimeSheetOfUserById(int idUser) throws Exception {
		
		SessionFactory factory = TimeSheetMethods.getFactory();
		Session session = factory.openSession();
		
		Criteria criteria = session.createCriteria(Timetable.class);
		
		if(idUser != 0) {
			criteria.add(Restrictions.eq("id_user", idUser));
		}
		
		List<Timetable> table = null;

	    table = criteria.list();
	    
	    if (session != null && session.isOpen()) {
	    	session.close();
	        factory.close();
	        }

	    return table;
	}
	
	//Get TimeSheet of a User By ID and Period
	public static List<Timetable> getTimeSheetOfUserByIdAndPeriod(int idUser, LocalDate from, LocalDate to) throws Exception {
	    
		if (to.isBefore(from)) {
	        throw new IllegalArgumentException("Invalid range");
	    }
		
		SessionFactory factory = TimeSheetMethods.getFactory();
		Session session = factory.openSession();
		
		Criteria criteria = session.createCriteria(Timetable.class);
		
		if(idUser != 0 && from != null && to != null) {
			criteria.add(Restrictions.eq("id_user", idUser));
			criteria.add(Restrictions.ge("date", from));
			criteria.add(Restrictions.lt("date", to));
		}
		
		List<Timetable> table = null;

	    table = criteria.list();
	    
	    if (session != null && session.isOpen()) {
	    	session.close();
	        factory.close();
	        }

	    return table;
	}

	//CONNESSIONE AL FACTORY
	private static SessionFactory getFactory() throws Exception {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Timetable.class)
				.buildSessionFactory();		
		return factory;
	}
	
} //END CLASS
