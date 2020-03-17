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

		Query q = entitymanager.createQuery("SELECT t FROM Timetable t");

		List<Timesheet> timetables = q.getResultList();

		entitymanager.close();
		
		System.out.println("caricaTutto : " + timetables.size());
		
		return timetables;
	}

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
				"SELECT t FROM Timetable t WHERE t.date >= '" + startDate + "'" + "ORDER BY t.id_user",
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

		TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timetable t WHERE t.date = '" + startDate
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
			Query query = entitymanager.createQuery("UPDATE Timetable t SET t.date=?1, t.type=?2, t.start1=?3, t.end1=?4, t.start2=?5, t.end2=?6, t.tot=?7 WHERE t.idUser = '"+id+"'"+" AND t.date = '" + date +"'");
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
				"SELECT t FROM Timetable t WHERE t.date = '" + date + "'" + " AND t.id_user = '" + idUtente + "'",
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
				"SELECT t FROM Timetable t WHERE t.date >= '" + startDate + "' AND t.date<= '" + endDate + "'",
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
				.createQuery("SELECT t FROM Timetable t WHERE t.id_user = '" + id_user + "'", Timesheet.class);
		records = q.getResultList();
		return records;
	}

	public static List takeRecordsTimetablVersion() {
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timetable t", Timesheet.class);
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
		TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timetable t WHERE t.type='" + type + "'",
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
			TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timetable t WHERE t.type='" + type
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
//
//		String type=table.getType()+"";
//		if (type.equals("h")) {
//			table.setStart1(LocalTime.of(00, 00));
//			table.setEnd1(LocalTime.of(00, 00));
//			table.setStart2(LocalTime.of(00, 00));
//			table.setEnd2(LocalTime.of(00, 00));
//
		if (table.getType().equals("L")) {
			table.setStart1(LocalTime.of(9,0));
			table.setEnd1(LocalTime.of(13,0));
			table.setStart2(LocalTime.of(14,0));
			table.setEnd2(LocalTime.of(18,0));
		}
//		if (type.equals("s")) {
//			table.setStart1(LocalTime.of(00, 00));
//			table.setEnd1(LocalTime.of(00, 00));
//			table.setStart2(LocalTime.of(00, 00));
//			table.setEnd2(LocalTime.of(00, 00));
		if (table.getType().equals("F")) {
			table.setStart1(LocalTime.of(9,0));
			table.setEnd1(LocalTime.of(13,0));
			table.setStart2(LocalTime.of(14,0));
			table.setEnd2(LocalTime.of(18,0));
		}
		
//		if (type.equals("v")) {
//			table.setStart1(LocalTime.of(00, 00));
//			table.setEnd1(LocalTime.of(00, 00));
//			table.setStart2(LocalTime.of(00, 00));
//			table.setEnd2(LocalTime.of(00, 00));
//	}
		if (table.getType().equals("M")) {
			table.setStart1(LocalTime.of(9,0));
			table.setEnd1(LocalTime.of(13,0));
			table.setStart2(LocalTime.of(14,0));
			table.setEnd2(LocalTime.of(18,0));
		}
//		if (type.equals("p")) {
//			table.setStart2(LocalTime.of(00, 00));
//			table.setEnd2(LocalTime.of(00, 00));

		if (table.getType().equals("P")) {
			table.setStart1(LocalTime.of(9,0));
			table.setEnd1(LocalTime.of(13,0));
			table.setStart2(LocalTime.of(14,0));
			table.setEnd2(LocalTime.of(18,0));
		}
		entitymanager.persist(table);
		entitymanager.getTransaction().commit();

//		entitymanager.close();
//		emfactory.close();
	}
//	public void creaRecordTimetable(Timesheet table) {
//
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//
//		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.getTransaction().begin();
//		String type=table.getType()+"";
//		if (type.equals("h")) {
//			table.setStart1("00:00");
//			table.setEnd1("00:00");
//			table.setStart2("00:00");
//			table.setEnd2("00:00");
//		}
//		if (type.equals("s")) {
//			table.setStart1("00:00");
//			table.setEnd1("00:00");
//			table.setStart2("00:00");
//			table.setEnd2("00:00");
//		}
//		
//		if (type.equals("v")) {
//			table.setStart1("00:00");
//			table.setEnd1("00:00");
//			table.setStart2("00:00");
//			table.setEnd2("00:00");
//		}
//		if (type.equals("p")) {
//			table.setStart2("00:00");
//			table.setEnd2("00:00");
//		}
//		entitymanager.persist(table);
//		entitymanager.getTransaction().commit();
//
////		entitymanager.close();
////		emfactory.close();
//	}

//	public double oreTrascorse(LocalTime localTime, LocalTime localTime2, LocalTime localTime3, LocalTime localTime4) {
//		System.out.println(localTime);
//		System.out.println(localTime2);
//		System.out.println(localTime3);
//		System.out.println(localTime4);
//		
//		localTime = approssimaOrario(localTime);
//		localTime2 = approssimaOrario(localTime2);
//		localTime3 = approssimaOrario(localTime3);
//		localTime4 = approssimaOrario(localTime4);
//
//		LocalTime s1 = LocalTime.parse(localTime, DateTimeFormatter.ofPattern("H:mm"));
//		LocalTime e1 = LocalTime.parse(localTime2, DateTimeFormatter.ofPattern("H:mm"));
//		LocalTime s2 = LocalTime.parse(localTime3, DateTimeFormatter.ofPattern("H:mm"));
//		LocalTime e2 = LocalTime.parse(localTime4, DateTimeFormatter.ofPattern("H:mm"));
//
//		double tempo = MINUTES.between(s1, e1) + MINUTES.between(s2, e2);
//		double tempoTrascorso = tempo / 60;
//		System.out.println(tempoTrascorso);
//		int croppato = (int) tempo / 60;
//		double minutaggioDecimale = tempoTrascorso - Math.floor(tempoTrascorso);
//
//		double minutaggioGiusto = (minutaggioDecimale / 100) * 60;
//		tempoTrascorso = croppato + minutaggioGiusto;
//		DecimalFormat df = new DecimalFormat("#.##");
//		tempoTrascorso = (double) Double.valueOf(df.format(tempoTrascorso));
//		System.out.println(tempoTrascorso);
//		return tempoTrascorso;
//
//	}
	public double oreTrascorse(LocalTime localTime, LocalTime localTime2, LocalTime localTime3, LocalTime localTime4) { //Calcolo ore in orario lavorativo normale
		System.out.println(localTime);
		System.out.println(localTime2);
		System.out.println(localTime3);
		System.out.println(localTime4);
		double tempoTrascorso = 0;
		
		tempoTrascorso =(localTime2.getHour()-localTime.getHour())+(localTime4.getHour()-localTime3.getHour());
		return tempoTrascorso;

	}
	public double oreTrascorse(LocalTime start1, LocalTime end1) {
		System.out.println(start1);
		System.out.println(end1);
		double tempoTrascorso = 0;
		tempoTrascorso =end1.getHour()-start1.getHour();
		return tempoTrascorso;

	}


//	public double oreTrascorse(String start1, String end1) {
//		System.out.println(start1);
//		System.out.println(end1);
//		
//		start1 = approssimaOrario(start1);
//		end1 = approssimaOrario(end1);
//		
//
//		LocalTime s1 = LocalTime.parse(start1, DateTimeFormatter.ofPattern("H:mm"));
//		LocalTime e1 = LocalTime.parse(end1, DateTimeFormatter.ofPattern("H:mm"));
//	
//
//		double tempo = MINUTES.between(s1, e1);
//		double tempoTrascorso = tempo / 60;
//		System.out.println(tempoTrascorso);
//		int croppato = (int) tempo / 60;
//		double minutaggioDecimale = tempoTrascorso - Math.floor(tempoTrascorso);
//
//		double minutaggioGiusto = (minutaggioDecimale / 100) * 60;
//		tempoTrascorso = croppato + minutaggioGiusto;
//		DecimalFormat df = new DecimalFormat("#.##");
//		tempoTrascorso = (double) Double.valueOf(df.format(tempoTrascorso));
//		System.out.println(tempoTrascorso);
//		return tempoTrascorso;
//
//	}

	
	
//	public String approssimaOrario(LocalTime localTime) {
//		String nuovoOrario = null;
//		localTime = localTime.substring(0, localTime.length());
//		localTime = localTime.trim();
//		int ora = Integer.parseInt(localTime.substring(0, 2)); // prendo ora in un int
//		int minuti = Integer.parseInt(localTime.substring(3)); // prendo minuti in un int
////		System.out.println(minuti);
//		if (minuti < 8) {
//
//			nuovoOrario = "" + ora + ":" + "00";
//		} else if (minuti > 8 && minuti <= 15) {
//			nuovoOrario = "" + ora + ":" + "15";
//		} else if (minuti > 15 && minuti <= 24) {
//			nuovoOrario = "" + ora + ":" + "15";
//		} else if (minuti > 24 && minuti <= 30) {
//			nuovoOrario = "" + ora + ":" + "30"; // approssimo al quarto d'ora
//		} else if (minuti > 30 && minuti <= 38) {
//			nuovoOrario = "" + ora + ":" + "30";
//		} else if (minuti > 38 && minuti <= 45) {
//			nuovoOrario = "" + ora + ":" + "45";
//		} else if (minuti > 45 && minuti <= 54) {
//			nuovoOrario = "" + ora + ":" + "45";
//		} else {
//			if (ora == 23)
//				nuovoOrario = "00:00";
//			ora = ora + 1;
//			nuovoOrario = "" + ora + ":" + "00";
//		}
//
//		return nuovoOrario;
//	}

	public List<Timesheet> retrieveListById(int id) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		Query q = entitymanager.createQuery("FROM Timetable t WHERE t.idUser = "+id);

		List<Timesheet> timetables = q.getResultList();

		entitymanager.close();
		
		return timetables;
	}

	public List<Timesheet> retrieveTimatablesInDateRangeByUserId(int userId, Date dateFrom, Date dateTo) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		Query q = entitymanager.createQuery("FROM Timetable t WHERE t.idUser = "+userId+" and t.date >= '"+dateFrom+"' and t.date <= '"+dateTo+"'");

		List<Timesheet> timetables = q.getResultList();

		entitymanager.close();
		
		return timetables;
	}

}
