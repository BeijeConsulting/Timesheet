package it.beije.mgmt.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.IllegalDateException;
import it.beije.mgmt.exception.IllegalHourException;
import it.beije.mgmt.exception.NoContentException;


@Service
public class TimetableService {
	
//	private static boolean hasOverlap(Time s1, Time e1, Time s2, Time e2) {
//		
//		 if(((s1==null && e1==null) && (s2!=null && e2!=null)) || ((s1!=null && e1!=null) && (s2==null && e2==null)))
//			return false;
//		 
//		 if(!e1.before(s2) && !s1.after(e2)) {
//			 if(e1.equals(s2) || s1.equals(e2)) {
//				 return false;
//			 }
//			 else
//				 return true;
//		 }
//		 else 
//			 return false;
//	}
//----------------------------------------------------------------------------------------------------------------------------------------------------	
//	public List<Timesheet> caricaTutto() {
//
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//
//		Query q = entitymanager.createQuery("SELECT t FROM Timesheet t");
//
//		List<Timesheet> timetables = q.getResultList();
//
//		entitymanager.close();
//		
//		System.out.println("caricaTutto : " + timetables.size());
//		
//		return timetables;
//	}
//----------------------------------------------------------------------------------------------------------------------------------------------------	
	public List<Timesheet> insert(List<Timesheet> timetables) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		for(Timesheet t : timetables) {
			
			if((t.getStart1()==null && t.getEnd1()!=null) || (t.getStart2()==null && t.getEnd2()!=null) || (t.getStart1()!=null && t.getEnd1()==null) || (t.getStart2()!=null && t.getEnd2()==null)
					 || (t.getStart1()==null && t.getEnd1()==null && t.getStart2()==null && t.getEnd2()==null))
				
				 //SE GLI ORARI DI OGNI TIMESHEET HANNO UN INIZIO MA NON UNA FINE O VICEVERSA  OPPURE HA TUTTI ORARI NULL C'�  UN PROBLEMA
				 throw new IllegalHourException("ATTENZIONE: � presente una timesheet con orari non completi");
			if(t.getTot()>8)
				
				//SE LE ORE DI UNA TIMESHEET SONO MAGGIORI DI 8 C'� UN PROBLEMA
				throw new IllegalHourException("ATTENZIONE: le ore complessive della giornata sono maggiori di 8!");
			
			
			
			entitymanager.persist(t);			
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
			System.out.println("non � stato trovato nulla");
		}
		System.out.println(user.getPassword());
		if (user.getPassword().equals(password))
			check = true;
		return check;
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
	 * @throws Exception 
	 * 
	 *****************************************************************************************************************/
	
	 public static boolean isHourInInterval(String target, String start, String end) {
	        return ((target.compareTo(start) >= 0)
	                && (target.compareTo(end) <= 0));
	    }
	 
//----------------------------------------------------------------------------------------------------------------------------------------------------	 
//	public static boolean submitUtente(int userId, Date datefrom) {
//	List<Timesheet> listaT = multiTimesheetPerDay(userId, datefrom);
//	LocalDateTime today = LocalDateTime.now();
//	EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//	EntityManager entitymanager = emfactory.createEntityManager();
//	EntityTransaction entr = entitymanager.getTransaction();
//	entr.begin();
//	String s = "";
//	double tot=0;
//	for(Timesheet t: listaT) {
//	
//		 tot += t.getTot();
//		
//		 if(!s.contains(t.getType()))
//			 s += t.getType();
//		 else
//			 //SE CI SONO PI� TIMESHEET CON STESSA TIPOLOGIA NELLA STESSA DATA C'� UN PROBLEMA
//			 throw new IllegalHourException("ATTENZIONE: ci sono pi� timesheet con la stessa tipologia");
//		 
//		 if((t.getStart1()==null && t.getEnd1()!=null) || (t.getStart2()==null && t.getEnd2()!=null) || (t.getStart1()!=null && t.getEnd1()==null) || (t.getStart2()!=null && t.getEnd2()==null)
//				 || (t.getStart1()==null && t.getEnd1()==null && t.getStart2()==null && t.getEnd2()==null))
//			
//			 //SE GLI ORARI DI OGNI TIMESHEET HANNO UN INIZIO MA NON UNA FINE O VICEVERSA  OPPURE HA TUTTI ORARI NULL C'�  UN PROBLEMA
//			 throw new IllegalHourException("ATTENZIONE: � presente una timesheet con orari non completi");
//		 
//	}
//	if((s.contains("f") || s.contains("F")) && s.length()>1)
//		//SE C'� UNA TIMESHEET FERIE ASSIEME AD ALTRE TIMESHEET CON STESSA DATA C'� UN PROBLEMA IN QUANTO SE SEI IN FERIE NON PUOI AVERE TIMESHEET PERMESSO O LAVORO
//	throw new IllegalHourException("ATTENZIONE: � presente una timesheet di tipo ferie assieme ad altre tipologie: o sei in ferie o sei al lavoro!");
//	
//	if(listaT.size() == 1 && tot <= 8) {
//		// SE IN QUEL GIORNO ABBIAMO UNA SOLA TIMESHEET E LE ORE SONO MENO O UGIALI A 8 (MINORE PER POSSIBILI PARTTIME) PUOI FARE SUBMIT.
//		String q= "UPDATE Timesheet t SET t.submit = '"+today+"' WHERE id_user ='"+userId+"'AND t.date='"+datefrom+"'";
//		Query query = entitymanager.createQuery(q);
//		int result=query.executeUpdate();
//		entr.commit();
//		entitymanager.close();
//		return true;
//	}
//	else
//	{
//		if(tot > 8) {
//			//SE IL TOTALE DELLE ORE DI UNA SINGOLA TIMESHEET SUPERA 8 C'� UN PROBLEMA.
//			System.out.println(tot);
//			throw new IllegalHourException("ATTENZIONE: le ore complessive della giornata sono maggiori di 8!");
//		}
//		else
//			//SE ARRIVIAMO QUA SIGNIFICA CHE ABBIAMO MINIMO 2 TIMESHEET E MASSIMO 3 TIMESHEET DI UNO STESSO GIORNO E DOBBIAMO VEDERE SE ABBIAMO ORARI CHE SI SOVRAPPONGONO.
//		{
//			Time primaStart1=listaT.get(0).getStart1();
//			Time primaEnd1=listaT.get(0).getEnd1();
//			Time primaStart2=listaT.get(0).getStart2();
//			Time primaEnd2=listaT.get(0).getEnd2();
//			Time secondaStart1=listaT.get(1).getStart1();
//			Time secondaEnd1= listaT.get(1).getEnd1();
//			Time secondaStart2=listaT.get(1).getStart2();
//			Time secondaEnd2=listaT.get(1).getEnd2();
//			
//			for(int i=1;i<listaT.size();i++) {
//				
//				if(i==1) {
//					
//					if(hasOverlap(primaStart1,primaEnd1,secondaStart1,secondaEnd1)|| hasOverlap(primaStart2, primaEnd2, secondaStart2, secondaEnd2)){
//						
//						throw new IllegalHourException(" ATTENZIONE: sono presenti degli accavallamenti con gli orari");
//					}
//					else if(i==2) {
//						
//						Time ultimoStart1=listaT.get(i).getStart1();
//						Time ultimoEnd1= listaT.get(i).getEnd1();
//						Time ultimoStart2=listaT.get(i).getStart2();
//						Time ultimoEnd2=listaT.get(i).getEnd2();
//						
//						if(hasOverlap(primaStart1, primaEnd1, ultimoStart1, ultimoEnd1)|| hasOverlap(primaStart2, primaEnd2, ultimoStart2, ultimoEnd2)) 
//							throw new IllegalHourException(" ATTENZIONE: sono presenti degli accavallamenti con gli orari");
//					
//						if(hasOverlap(secondaStart1, secondaEnd1, ultimoStart1, ultimoEnd1)|| hasOverlap(secondaStart2, secondaEnd2, ultimoStart2, ultimoEnd2))
//							throw new IllegalHourException(" ATTENZIONE: sono presenti degli accavallamenti con gli orari");
//					}
//				}	
//			}
//			String q= "UPDATE Timesheet t SET t.submit = '"+today+"' WHERE id_user ='"+userId+"'AND t.date='"+datefrom+"'";	
//			Query query = entitymanager.createQuery(q);
//			int result=query.executeUpdate();
//			entr.commit();
//			entitymanager.close();
//			return true;
//			}
//		}
//	}
//----------------------------------------------------------------------------------------------------------------------------------------------------	
//	public static boolean submitUtente(int userId, Date datefrom, Date dateto) {
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		EntityTransaction entr = entitymanager.getTransaction();
//		entr.begin();
//		if(dateto==null) {
//			//CASO IN CUI SI SELEZIONA UN UNICO GIORNO SENZA QUINDI IL DATETO
//			 return submitUtente(userId,datefrom);
//		}
//
//		else {
//			if(dateto.before(datefrom))
//				throw new IllegalDateException("ATTENZIONE: la data di fine non pu� essere precedente a quella di inizio");
//			List<Timesheet> listaT = TimetableService.retrieveTimatablesInDateRangeByUserId(userId, datefrom, dateto);
//			for(Timesheet t: listaT) {
//				Date occorrenza = t.getDate();
//				if(t.getSubmit()==null)
//				 submitUtente(userId, occorrenza);
//				
//			}
//			return true;
//			
//		}	
//}
//----------------------------------------------------------------------------------------------------------------------------------------------------
	public static boolean svuotaserver() {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction entr = entitymanager.getTransaction();
		entr.begin();
		String q= "DELETE from Timesheet where id IS NOT NULL";
		Query query = entitymanager.createQuery(q);
		int result=query.executeUpdate();
		entr.commit();
		if(result!=0)
			return true;
		else
			return false;
			
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------					
	public boolean validator(int userId, Date dateFrom, Date dateTo) {
		LocalDateTime today = LocalDateTime.now();
		
		if(dateTo==null) {
			List<Timesheet> lista = controlloValidazione(retrieveTimatablesInDateRangeByUserId(userId, dateFrom, dateFrom));
			EntityManagerFactory emfactory = JpaEntityManager.getInstance();
			EntityManager entitymanager = emfactory.createEntityManager();
			EntityTransaction entr = entitymanager.getTransaction();
			entr.begin();
			System.out.println(lista);
			System.out.println("secondo ciclo");
			for(Timesheet t : lista) {
				if(t.getSubmit()!=null) {
					String q= "UPDATE Timesheet t SET t.validated = '"+today+"' WHERE id ='"+ t.getId()+"'";
					Query query = entitymanager.createQuery(q);
					int result=query.executeUpdate();
				}
			}	
			entr.commit();
			entitymanager.close();
			
			return true;
		}
		else {
			if(dateTo.before(dateFrom))
				throw new IllegalDateException("ATTENZIONE: la data di fine non pu� essere precedente a quella di inizio");
			List<Timesheet> lista = controlloValidazione(retrieveTimatablesInDateRangeByUserId(userId,  dateFrom,dateTo));
			EntityManagerFactory emfactory = JpaEntityManager.getInstance();
			EntityManager entitymanager = emfactory.createEntityManager();
			EntityTransaction entr = entitymanager.getTransaction();
			entr.begin();
			System.out.println(lista);
			
			for(Timesheet t : lista) {
				if(t.getSubmit()!=null) {
					String q= "UPDATE Timesheet t SET t.validated = '"+today+"' WHERE id ='"+ t.getId()+"'";
					Query query = entitymanager.createQuery(q);
					int result=query.executeUpdate();
			}
		}	
		entr.commit();
		entitymanager.close();
		return true;	
		}
	}
	 
	 
	public List<Timesheet> controlloValidazione(List<Timesheet> lista){
		//SE UNA TIMESHEET � GI� VALIDATA LA SALTO
		List<Timesheet> nuova = new ArrayList<Timesheet>();
		for(Timesheet t : lista) {
			if(t.getValidated()==null) {
				nuova.add(t);
			}			
		}
		return nuova;
	}
	
	public void updateRecord(long id, Date date,Timesheet newTable) {
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

	public List<Timesheet> takeRecordsFromDateToDate(Date startDate, Date endDate) {
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
	
	//metodo vecchio da vedere se utile o no
	
//	public List<Timesheet> smartSearch(Date date1, Date date2, char type) {
//		List<Timesheet> records = new ArrayList<Timesheet>();
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//
//		if (date1 == null && date2 == null)
//			records = searchFromType(type);
//		else if (date2 == null && type == 0)
//			records = takeRecordsFromDate(date1);
//		else if (date1 != null && date2 != null && type == 0) {
//			records = takeRecordsFromDateToDate(date1, date2);
//		} else {
//			TypedQuery<Timesheet> q = entitymanager.createQuery("SELECT t FROM Timesheet t WHERE t.type='" + type
//					+ "' AND t.date >= '" + date1 + "' AND t.date<= '" + date2 + "'", Timesheet.class);
//			records = q.getResultList();
//		}
//		if (records.isEmpty()) {
//			System.out.println("nessun risultato trovato");
//		}
//
//		return records;
//	}

	public double oreTrascorse(Time time, Time time2, Time time3, Time time4) { //Calcolo ore in orario lavorativo normale
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
		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non � stata trovata alcuna timesheet con i parametri inseriti");
		else
			return timetables;
	}
	
	
	public static Timesheet singolatimesheet(int userId,Date dateFrom){
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q = entitymanager.createQuery("FROM Timesheet t WHERE t.idUser = "+userId+" and t.date = '"+dateFrom+"'");
		Timesheet timetables = (Timesheet) q.getSingleResult();
		entitymanager.close();
		if(timetables==null)
			throw new NoContentException("ATTENZIONE: non � stata trovata alcuna timesheet con i parametri inseriti");
		else
			return timetables;
	}
	
	
	public static List<Timesheet> retrieveTimatablesInDateRangeByUserId(int userId, Date dateFrom, Date dateTo) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q = entitymanager.createQuery("FROM Timesheet t WHERE t.idUser = "+userId+" and t.date >= '"+dateFrom+"' and t.date <= '"+dateTo+"'");

		List<Timesheet> timetables = q.getResultList();

		entitymanager.close();
		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non � stata trovata alcuna timesheet con i parametri inseriti");
		else
			return timetables;
	}
//-------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean deleteRestController(int userid,Date date) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction entr = entitymanager.getTransaction();
		entr.begin();
		TimetableService.singolatimesheet(userid, date);
		Query query = entitymanager.createQuery("delete from Timesheet where id_user= :ID AND date= :DATE");
		query.setParameter("ID", userid);
		query.setParameter("DATE", date);
		int result = query.executeUpdate();
		entr.commit();
		return true;

	}
//-------------------------------------------------------------------------------------------------------------------------------------------		
	public static User findRecordsFromId(long id) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User user = entitymanager.find(User.class, id);
		return user;
	}
//-------------------------------------------------------------------------------------------------------------------------------------------		
	public void cancellaTimetable(Timesheet table) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();  
		EntityTransaction entr = entitymanager.getTransaction();
		entr.begin();
		Query query = entitymanager.createQuery("delete from Timesheet where id_user= :ID AND date= :DATE");
		query.setParameter("ID", table.getIdUser());
		query.setParameter("DATE", table.getDate());
		int result = query.executeUpdate();
		entr.commit();
		entitymanager.close();
		emfactory.close();
	}
//-------------------------------------------------------------------------------------------------------------------------------------------		
	public void creaRecordTimetable(Timesheet table) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager(); //L -->lavorativo F-->Ferie M-->Malattia P-->Permesso 
		entitymanager.getTransaction().begin();
		entitymanager.persist(table);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
//-------------------------------------------------------------------------------------------------------------------------------------------		
	public static List<Timesheet> takeRecordsFromIdTimetableVersionWithPeriod(int id_user, Date start , Date end ) {
		// int p = id_user;
		List<Timesheet> records = new ArrayList<Timesheet>();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.createQuery(criteriaQuery);
		TypedQuery<Timesheet> q = entitymanager
				.createQuery("SELECT t FROM Timesheet as t WHERE id_user = '" + id_user + "' AND date BETWEEN '"+ start + "' AND '"
				+end +"'" , Timesheet.class);
		records = q.getResultList();
		return records;
	}
//-------------------------------------------------------------------------------------------------------------------------------------------		
	public static List<Timesheet> multiTimesheetPerDay(int userId,Date date){
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q = entitymanager.createQuery("FROM Timesheet t WHERE t.idUser = "+userId+" and t.date = '"+date+"'");
		List<Timesheet> timetables = q.getResultList();
		entitymanager.close();
		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non � stata trovata alcuna timesheet con i parametri inseriti");
		else
			return timetables;
	}

}
