package it.beije.erp.timesheet.service;

import java.time.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.sql.Date;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import it.beije.erp.timesheet.entity.Timetable;
import it.beije.erp.timesheet.entity.User;
import it.beije.jpa.JpaEntityManager;

@Service
public class TimetableService {

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

	// RECUPERA UTENTI PER DATA
	public List takeRecordsFromDate(Date startDate) {
		List<Timetable> records = new ArrayList<Timetable>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

//		entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timetable> q = entitymanager.createQuery(
				"SELECT t FROM Timetable t WHERE t.date >= '" + startDate + "'" + "ORDER BY t.id_user",
				Timetable.class);

		records = q.getResultList();

		return records;
	}

	// RECUPERA UTENTE PER ID - DATA
	public List<Timetable> takeRecordsFromDateId(Date startDate, int idUtente) {

		List<Timetable> records = new ArrayList<Timetable>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<Timetable> q = entitymanager.createQuery("SELECT t FROM Timetable t WHERE t.date = '" + startDate
				+ "'" + " AND t.idUser = '" + idUtente + "'" + " ORDER BY t.date", Timetable.class);

		records = q.getResultList();

		return records;
	}

//	public void updateRecord(int id, Date date, Timetable newTable) {
//
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//
//		EntityManager entitymanager = emfactory.createEntityManager();
//		
//		
//		newTable.setDate(date);
//		newTable.setId(id);
//		
//		entitymanager.getTransaction().begin();
//
//		entitymanager.persist(newTable);
//		//entitymanager.flush();
//		
//		entitymanager.getTransaction().commit();
//
//		entitymanager.close();
//		emfactory.close();
//		
//
//	}

//	public void updateRecord(int id, Date date,Timetable newTable) {
//
//		
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		TimetableService service = new TimetableService ();
//		double totOre =  service.oreTrascorse(newTable.getStart1(), newTable.getEnd1(), newTable.getStart2(), newTable.getEnd2());
//		
//		System.out.println("-------------------------------------------------------------------------");
//		System.out.println(newTable.getStart1());
//		System.out.println(newTable.getEnd1());
//		System.out.println(newTable.getStart2());
//		System.out.println(newTable.getEnd2());
//		System.out.println("type" + newTable.getType());
//		System.out.println(newTable.getDate());
//		System.out.println(totOre);
//
//		System.out.println("-------------------------------------------------------------------------");
//		
//		Query q = entitymanager.createNativeQuery("UPDATE timetable SET start1= '"+newTable.getStart1()+"'"+",end1='"+newTable.getEnd1()+"'"+",start2='"+newTable.getStart2()+"'"+",end2='"+newTable.getEnd2()+"'"+",date='"+newTable.getDate()+"'"+",tot='"+totOre+"'"+" WHERE date = '"+date+"'"+" AND id_user = '" + id +"'");
//		
//		//Query q = entitymanager.createQuery("UPDATE Timetable t SET t.start1= '"+newTable.getStart1()+"'"+",t.end1='"+newTable.getEnd1()+"'"+",t.start2='"+newTable.getStart2()+"'"+",t.end2='"+newTable.getEnd2()+"'"+",t.date='"+newTable.getDate()+"'"+",t.tot='"+totOre+"'"+" WHERE t.date = '"+date+"'"+" AND t.idUser = '" + id +"'");
//		
//		q.executeUpdate();
//		entitymanager.getTransaction().commit();
//
////		INSERT INTO table_name (column1, column2, column3, ...)
////		VALUES (value1, value2, value3, ...);
//		
//		
//		//entitymanager.createQuery("INSERT INTO Timetable (id,idUser,date,type,start1,end1,start2,end2,tot)
//	
//	
//	}

	
	
	
	
	
	public void updateRecord(int id, Date date,Timetable newTable) {

	EntityManagerFactory emfactory = JpaEntityManager.getInstance();
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	String modifica="UPDATE Timetable a SET";

	modifica += " a.idUser= '" +id;
	modifica += "' , a.type= '" + newTable.getType();
	modifica += "' , a.start1= '" +newTable.getStart1();
	modifica += "' , a.end1= '" + newTable.getEnd1();
	modifica += "' , a.start2= '" +newTable.getStart2();
	modifica += "' , a.end2= '" + newTable.getEnd2();
	modifica += "' , a.date= '" +date ;
	modifica += "' , a.tot= '" + newTable.getTot() +"'";
	
	modifica += " WHERE a.id= "+id;
	modifica += " AND a.date = " + date;
	
//	modifica += " WHERE a.id=  10";
	Query q = entitymanager.createQuery(modifica);
	int rowsUpdated = q.executeUpdate();
	
	System.out.println("---------------- date:" + date);
	//System.out.println(rowsUpdated);

	entitymanager.getTransaction().commit();
	
	}
	
	// RECUPERA UTENTE PER ID - DATA
	public List<Timetable> takeRecordFromDateId(Date date, int idUtente) {

		List<Timetable> records = new ArrayList<Timetable>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
//			entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timetable> q = entitymanager.createQuery(
				"SELECT t FROM Timetable t WHERE t.date = '" + date + "'" + " AND t.id_user = '" + idUtente + "'",
				Timetable.class);

//			SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
//			FROM Orders
//			INNER JOIN Customers
//			ON Orders.CustomerID=Customers.CustomerID;

		records = q.getResultList();

		return records;
	}

	// RECUPERA UTENTE PER ID - da DATA a DATA

	public List takeRecordsFromDateToDate(Date startDate, Date endDate) {
		List<Timetable> records = new ArrayList<Timetable>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TypedQuery<Timetable> q = entitymanager.createQuery(
				"SELECT t FROM Timetable t WHERE t.date >= '" + startDate + "' AND t.date<= '" + endDate + "'",
				Timetable.class);

		System.out.println(q.getFirstResult());
		records = q.getResultList();

		return records;
	}

	public List takeRecordsFromIdTimetableVersion(int id_user) {
		// int p = id_user;
		List<Timetable> records = new ArrayList<Timetable>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timetable> q = entitymanager
				.createQuery("SELECT t FROM Timetable t WHERE t.id_user = '" + id_user + "'", Timetable.class);
		records = q.getResultList();
		return records;
	}

	public static List takeRecordsTimetablVersion() {
		List<Timetable> records = new ArrayList<Timetable>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TypedQuery<Timetable> q = entitymanager.createQuery("SELECT t FROM Timetable t", Timetable.class);
		records = q.getResultList();
		return records;
	}

	public static User findRecordsFromId(int id) {
//		Timetable table=null;
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User user = entitymanager.find(User.class, id);

		return user;
	}

	public List searchFromType(char type) {
		List<Timetable> records = new ArrayList<Timetable>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		TypedQuery<Timetable> q = entitymanager.createQuery("SELECT t FROM Timetable t WHERE t.type='" + type + "'",
				Timetable.class);
		records = q.getResultList();

		return records;
	}

	public List smartSearch(Date date1, Date date2, char type) {
		List<Timetable> records = new ArrayList<Timetable>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		if (date1 == null && date2 == null)
			records = searchFromType(type);
		else if (date2 == null && type == 0)
			records = takeRecordsFromDate(date1);
		else if (date1 != null && date2 != null && type == 0) {
			records = takeRecordsFromDateToDate(date1, date2);
		} else {
			TypedQuery<Timetable> q = entitymanager.createQuery("SELECT t FROM Timetable t WHERE t.type='" + type
					+ "' AND t.date >= '" + date1 + "' AND t.date<= '" + date2 + "'", Timetable.class);
			records = q.getResultList();
		}
		if (records.isEmpty()) {
			System.out.println("nessun risultato trovato");
		}

		return records;
	}

	public void creaoRecordTimetable(Timetable table) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(table);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

	public double oreTrascorse(String start1, String end1, String start2, String end2) {
		System.out.println(start1);
		System.out.println(end1);
		System.out.println(start2);
		System.out.println(end2);
		start1 = approssimaOrario(start1);
		end1 = approssimaOrario(end1);
		start2 = approssimaOrario(start2);
		end2 = approssimaOrario(end2);

		LocalTime s1 = LocalTime.parse(start1, DateTimeFormatter.ofPattern("H:mm"));
		LocalTime e1 = LocalTime.parse(end1, DateTimeFormatter.ofPattern("H:mm"));
		LocalTime s2 = LocalTime.parse(start2, DateTimeFormatter.ofPattern("H:mm"));
		LocalTime e2 = LocalTime.parse(end2, DateTimeFormatter.ofPattern("H:mm"));

		double tempo = MINUTES.between(s1, e1) + MINUTES.between(s2, e2);
		double tempoTrascorso = tempo / 60;
		System.out.println(tempoTrascorso);
		int croppato = (int) tempo / 60;
		double minutaggioDecimale = tempoTrascorso - Math.floor(tempoTrascorso);

		double minutaggioGiusto = (minutaggioDecimale / 100) * 60;
		tempoTrascorso = croppato + minutaggioGiusto;
		DecimalFormat df = new DecimalFormat("#.##");
		tempoTrascorso = (double) Double.valueOf(df.format(tempoTrascorso));
		System.out.println(tempoTrascorso);
		return tempoTrascorso;

	}

	public String approssimaOrario(String orario) {
		String nuovoOrario = null;
		orario = orario.substring(0, orario.length());
		orario = orario.trim();
		int ora = Integer.parseInt(orario.substring(0, 2)); // prendo ora in un int
		int minuti = Integer.parseInt(orario.substring(3)); // prendo minuti in un int
//		System.out.println(minuti);
		if (minuti < 8) {

			nuovoOrario = "" + ora + ":" + "00";
		} else if (minuti > 8 && minuti <= 15) {
			nuovoOrario = "" + ora + ":" + "15";
		} else if (minuti > 15 && minuti <= 24) {
			nuovoOrario = "" + ora + ":" + "15";
		} else if (minuti > 24 && minuti <= 30) {
			nuovoOrario = "" + ora + ":" + "30"; // approssimo al quarto d'ora
		} else if (minuti > 30 && minuti <= 38) {
			nuovoOrario = "" + ora + ":" + "30";
		} else if (minuti > 38 && minuti <= 45) {
			nuovoOrario = "" + ora + ":" + "45";
		} else if (minuti > 45 && minuti <= 54) {
			nuovoOrario = "" + ora + ":" + "45";
		} else {
			if (ora == 23)
				nuovoOrario = "00:00";
			ora = ora + 1;
			nuovoOrario = "" + ora + ":" + "00";
		}

		return nuovoOrario;
	}

}
