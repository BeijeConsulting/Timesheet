package it.beije.timesheet.entities.methods;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.sql.Date;
import java.text.DecimalFormat;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import it.beije.timesheet.HDButils;
//import it.beije.timesheet.entities.JpaEntityManager;
import it.beije.timesheet.entities.Timetable;
import it.beije.timesheet.entities.User;

public class TimeSheetMethods {
	
	//Get Timesheet of a User By ID
	
	public static List<Timetable> getTimeSheetOfUserById(int idUser) throws Exception {
		
		SessionFactory factory = HDButils.getFactory(Timetable.class);
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
		
		SessionFactory factory = HDButils.getFactory(Timetable.class);
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

	//Set Or Update TimeSheet
	public static void setOrUpdateTimeSheet(Timetable table) {	
		
		SessionFactory factory = null;
		Session session = null;
//ntynr
		try {
			factory = HDButils.getFactory(Timetable.class);
			session = factory.openSession();
			
			Transaction transaction = session.beginTransaction();
		    
			session.saveOrUpdate(table);
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
		
	}
	
	public static boolean checkPassword (int id, String password)   {
		boolean check=false;
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User user = entitymanager.find(User.class, id);
		if (user.getPassword().equals(password) && user!=null)  {
			check=true;
		}
		
		
		return check;
	}
	
	public static List takeRecordsFromDate (Date startDate)  {
		List <Timetable> records = new ArrayList <Timetable> ();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timetable> q =entitymanager.createQuery("SELECT t FROM Timetable t WHERE t.date >= '"+startDate+"'" ,Timetable.class);
		
		System.out.println(q.getFirstResult());
		records = q.getResultList();
		
		
		return records;
	}
	
	public static List takeRecordsFromIdTimetableVersion (int id_user) {
	//	int p = id_user;
		List <Timetable> records = new ArrayList <Timetable> ();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timetable> q =entitymanager.createQuery("SELECT t FROM Timetable t WHERE t.id_user = '" +id_user +"'" ,Timetable.class);
		records = q.getResultList();
		return records;
	}
	
	public static List takeRecordsTimetablVersion ()  {
		List <Timetable> records = new ArrayList <Timetable> ();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TypedQuery<Timetable> q =entitymanager.createQuery("SELECT t FROM Timetable t", Timetable.class);
		records = q.getResultList();
		return records;
	}
	
	public static User findRecordsFromId (int id)  {
//		Timetable table=null;
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User user = entitymanager.find(User.class, id);
	
		
		return user;
	}
//	public static double calculateTotalHour(LocalTime t1, LocalTime t2, LocalTime t3, LocalTime t4) {
//		double tempo = MINUTES.between(t1, t2)+MINUTES.between(t3,t4);
//		return tempo/60;
//	}
	
	public static void creaoModificaRecord (Timetable table) {
		
	      EntityManagerFactory emfactory = JpaEntityManager.getInstance();
	      
	      EntityManager entitymanager = emfactory.createEntityManager();
	      entitymanager.getTransaction().begin();

	     
	      
	      
	      entitymanager.persist(table);
	      entitymanager.getTransaction().commit();

	      entitymanager.close( );
	      emfactory.close( );
	}
	
	public static double oreTrascorse(String start1, String end1, String start2, String end2)  {
		System.out.println(start1);
		System.out.println(end1);
		System.out.println(start2);
		System.out.println(end2);
		start1=approssimaOrario(start1);
		end1=approssimaOrario(end1);
		start2=approssimaOrario(start2);
		end2=approssimaOrario(end2);
			
		LocalTime s1 = LocalTime.parse(start1,DateTimeFormatter.ofPattern("H:mm"));
		LocalTime e1 = LocalTime.parse(end1,DateTimeFormatter.ofPattern("H:mm"));
		LocalTime s2 = LocalTime.parse(start2,DateTimeFormatter.ofPattern("H:mm"));
		LocalTime e2 = LocalTime.parse(end2,DateTimeFormatter.ofPattern("H:mm"));
	
		double tempo = MINUTES.between(s1, e1)+MINUTES.between(s2,e2);
		double tempoTrascorso=tempo/60;
		System.out.println(tempoTrascorso);
		int croppato=(int) tempo/60;
		double minutaggioDecimale = tempoTrascorso - Math.floor(tempoTrascorso);
		
		double minutaggioGiusto=(minutaggioDecimale/100)*60;
		tempoTrascorso = croppato+minutaggioGiusto;
		DecimalFormat df = new DecimalFormat("#.##");
		tempoTrascorso= (double)Double.valueOf(df.format(tempoTrascorso));
		System.out.println(tempoTrascorso);
		return tempoTrascorso;

	}
	
	public static String approssimaOrario(String orario)  {
		String nuovoOrario=null;
		orario=orario.substring(0, orario.length());
		orario=orario.trim();
		int ora=Integer.parseInt(orario.substring(0,2));			//prendo ora in un int
		int minuti=Integer.parseInt(orario.substring(3));  			//prendo minuti in un int
//		System.out.println(minuti);
		if (minuti<8)  {
			
			nuovoOrario=""+ora+":"+"00";
		 } else if (minuti >8 && minuti <=15)  {
			 nuovoOrario=""+ora+":"+"15";
		 } else if (minuti>15 && minuti <=24)  {
			 nuovoOrario=""+ora+":"+"15";
		 } else if (minuti>24 && minuti <=30)  {
			 nuovoOrario=""+ora+":"+"30";                      //approssimo al quarto d'ora
		 } else if (minuti>30 && minuti <=38)  {
			 nuovoOrario=""+ora+":"+"30";
		 } else if (minuti>38 && minuti <=45)  {
			 nuovoOrario=""+ora+":"+"45";
		 } else if (minuti>45 && minuti <=54)  {
			 nuovoOrario=""+ora+":"+"45";
		 } else {
			 if (ora == 23)
				 nuovoOrario="00:00";
			 ora=ora+1;
			 nuovoOrario=""+ora+":"+"00";
		 }

		return nuovoOrario;
	}
	//CONNESSIONE AL FACTORY
//	private static SessionFactory getFactory() throws Exception {
//		SessionFactory factory = new Configuration().configure()
//				.addAnnotatedClass(Timetable.class)
//				.buildSessionFactory();		
//		return factory;
//	}
	
} //END CLASS
