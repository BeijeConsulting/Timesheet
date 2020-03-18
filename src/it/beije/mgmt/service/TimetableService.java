package it.beije.mgmt.service;

import java.time.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.jpa.JpaEntityManager;

@Service
public class TimetableService {
	
	public List<Timesheet> caricaTutto() {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		Query q = entitymanager.createQuery("SELECT t FROM Timesheet t");

		List<Timesheet> timetables = q.getResultList();

		entitymanager.close();
		
		System.out.println("caricaTutto : " + timetables.size());
		
		return timetables;
	}

	
	
	//inserimento lista Timesheet
	public List<Timesheet> insert(List<Timesheet> timetables) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		for(Timesheet timetable : timetables) {
			entitymanager.persist(timetable);			
		}

		entitymanager.getTransaction().commit();
		entitymanager.close();
		
		return timetables;
	}
	
	/*****************************************************************************************************************
	 * 
	 * VERIFICA PASSWORD
	 * 
	 *****************************************************************************************************************/
	public boolean checkPassword(int id, String password) {
		boolean check = false;
		System.out.println("password in metodo: " + password);
		System.out.println(id);
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User user = entitymanager.find(User.class, id);

		if (user == null) {
			System.out.println("non è stato trovato nulla");
		}
		System.out.println(user.getPassword());
		if (user.getPassword().equals(password))
			check = true;

//		System.out.println(check);
		return check;
	}

	/*****************************************************************************************************************
	 * 
	 * RECUPERA UTENTE PER DATA
	 * 
	 *****************************************************************************************************************/
	public List takeRecordsFromDate(Date startDate) {
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

//		entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timesheet> q = entitymanager.createQuery(
				"SELECT t FROM Timesheet t WHERE t.date >= '" + startDate + "'" + "ORDER BY t.id_user",
				Timesheet.class);

		records = q.getResultList();

		return records;
	}

	/*****************************************************************************************************************
	 * 
	 * RECUPERA UTENTI PER ID E DATA
	 * 
	 *****************************************************************************************************************/
	public List<Timesheet> takeRecordsFromDateId(Date startDate, int idUtente) {
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timesheet t WHERE t.date = '" + startDate
				+ "'" + " AND t.idUser = '" + idUtente + "'" + " ORDER BY t.date", Timesheet.class);

		records = q.getResultList();

		return records;
	}


	/*****************************************************************************************************************
	 * 
	 * AGGIORNA TUPLA NEL DATABASE
	 * 
	 *****************************************************************************************************************/
	
	public void updateRecord(int id, Date date,Timesheet newTable) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TimetableService service = new TimetableService ();
		try{
			EntityTransaction entr = entitymanager.getTransaction();
			entr.begin();
			Query query = entitymanager.createQuery("UPDATE Timesheet t SET t.date=?1, t.type=?2, t.start1=?3, t.end1=?4, t.start2=?5, t.end2=?6, t.tot=?7 WHERE t.idUser = '"+id+"'"+" AND t.date = '" + date +"'");
			query.setParameter(1, newTable.getDate());
			query.setParameter(2, newTable.getType());
			query.setParameter(3, newTable.getStart1());
			query.setParameter(4, newTable.getEnd1());
			query.setParameter(5, newTable.getStart2());
			query.setParameter(6, newTable.getEnd2());
			query.setParameter(7, service.oreTrascorse(newTable.getStart1(), newTable.getEnd1(), newTable.getStart2(), newTable.getEnd2()) );
			int x = query.executeUpdate();
			System.out.println("NUM RECORD MODIFICATI = "+ x);
			entr.commit();
			}
			finally{
	//		entitymanager.close();
			}
			
		
//		double totOre =  service.oreTrascorse(newTable.getStart1(), newTable.getEnd1(), newTable.getStart2(), newTable.getEnd2());
//		int count = entitymanager.createQuery("UPDATE Timetable t "										+ "SET t.type =  '"+newTable.getType()+"'"+" ,t.start1= '"+newTable.getStart1()+",t.end1='"+newTable.getEnd1()+",t.start2='"+newTable.getStart2()+",t.end2='"+newTable.getEnd2()+",t.date='"+newTable.getDate()+"',t.tot='"+totOre+"'").executeUpdate();
//		int count2 = entitymanager.unwrap(Timetable).
	
	}
	
	/*****************************************************************************************************************
	 * 
	 * RECUPERA TUPLE BY ID E DATA
	 * 
	 *****************************************************************************************************************/
	public List<Timesheet> takeRecordFromDateId(Date date, int idUtente) {

		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
//			entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timesheet> q = entitymanager.createQuery(
				"SELECT t FROM Timesheet t WHERE t.date = '" + date + "'" + " AND t.id_user = '" + idUtente + "'",
				Timesheet.class);


			
			
			records = q.getResultList();
			
			
			return records;
		}
		




		

	

	// RECUPERA UTENTE PER ID - da DATA a DATA

	public List takeRecordsFromDateToDate(Date startDate, Date endDate) {
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TypedQuery<Timesheet> q = entitymanager.createQuery(
				"SELECT t FROM Timesheet t WHERE t.date >= '" + startDate + "' AND t.date<= '" + endDate + "'",
				Timesheet.class);

		System.out.println(q.getFirstResult());
		records = q.getResultList();

		return records;
	}

	public List takeRecordsFromIdTimetableVersion(int id_user) {
		// int p = id_user;
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timesheet> q = entitymanager
				.createQuery("SELECT t FROM Timesheet t WHERE t.id_user = '" + id_user + "'", Timesheet.class);
		records = q.getResultList();
		return records;
	}

	public static List takeRecordsTimetablVersion() {
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timesheet t", Timesheet.class);
		records = q.getResultList();
		return records;
	}

	public static User findRecordsFromId(long id) {
//		Timetable table=null;
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User user = entitymanager.find(User.class, id);

		return user;
	}

	public List searchFromType(char type) {
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timesheet t WHERE t.type='" + type + "'",
				Timesheet.class);
		records = q.getResultList();

		return records;
	}

	//metodo
	public List smartSearch(Date date1, Date date2, char type) {
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		if (date1 == null && date2 == null)
			records = searchFromType(type);
		else if (date2 == null && type == 0)
			records = takeRecordsFromDate(date1);
		else if (date1 != null && date2 != null && type == 0) {
			records = takeRecordsFromDateToDate(date1, date2);
		} else {
			TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timesheet t WHERE t.type='" + type
					+ "' AND t.date >= '" + date1 + "' AND t.date<= '" + date2 + "'", Timesheet.class);
			records = q.getResultList();
		}
		if (records.isEmpty()) {
			System.out.println("nessun risultato trovato");
		}

		return records;
	}




	/*****************************************************************************************************************
	 * 
	 * INSERISCI TUPLA NEL DATABASE
	 * 
	 *****************************************************************************************************************/
	public void creaRecordTimetable(Timesheet table) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager(); //L -->lavorativo F-->Ferie M-->Malattia P-->Permesso 
		entitymanager.getTransaction().begin();
		entitymanager.persist(table);
		entitymanager.getTransaction().commit();

//		entitymanager.close();
//		emfactory.close();
	}
	public void cancellaTimetable(Timesheet table) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();  
		System.out.println("entroooo");
		System.out.println(table);//mi da null...
			EntityTransaction entr = entitymanager.getTransaction();
			entr.begin();
			Query query = entitymanager.createQuery("delete from Timesheet where id_user= :ID AND date= :DATE");
			query.setParameter("ID", table.getIdUser());
			query.setParameter("DATE", table.getDate());
			int result = query.executeUpdate();
			entr.commit();

//		entitymanager.close();
//		emfactory.close();
	}


	public double oreTrascorse(Time time, Time time2, Time time3, Time time4) { //Calcolo ore in orario lavorativo normale
		System.out.println(time);
		System.out.println(time2);
		System.out.println(time3);
		System.out.println(time4);
		double tempoTrascorso = 0;
		LocalTime.parse(time.toString()).getHour();
	//	tempoTrascorso =((time2.getHours()-time.getHours())+(time4.getHours()-time3.getHours()));
		tempoTrascorso =(LocalTime.parse(time2.toString()).getHour()-LocalTime.parse(time.toString()).getHour())+(LocalTime.parse(time4.toString()).getHour()-LocalTime.parse(time3.toString()).getHour());
		return tempoTrascorso;

	}
	public double oreTrascorse(Time time,Time time2) {
		double tempoTrascorso = 0;
		tempoTrascorso =LocalTime.parse(time2.toString()).getHour()-LocalTime.parse(time.toString()).getHour();
		return tempoTrascorso;

	}

	public List<Timesheet> retrieveListById(int id) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		Query q = entitymanager.createQuery("FROM Timesheet t WHERE t.idUser = "+id);

		List<Timesheet> timetables = q.getResultList();

		entitymanager.close();
		
		return timetables;
	}

	public List<Timesheet> retrieveTimatablesInDateRangeByUserId(int userId, Date dateFrom, Date dateTo) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q = entitymanager.createQuery("FROM Timesheet t WHERE t.idUser = "+userId+" and t.date >= '"+dateFrom+"' and t.date <= '"+dateTo+"'");

		List<Timesheet> timetables = q.getResultList();

		entitymanager.close();
		
		return timetables;
	}

}
