package it.beije.mgmt.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.jpa.TimesheetRequest;
import it.beije.mgmt.exception.IllegalDateException;
import it.beije.mgmt.exception.IllegalHourException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.UpdateException;
import it.beije.mgmt.repository.TimesheetRepository;
import it.beije.mgmt.repository.UserRepository;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;


@Service
public class TimesheetService {
	
	@Autowired
	private TimesheetRepository timesheetRepository;
	
	@Autowired
	private UserRepository userRepository;
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	private static boolean hasOverlap(Time s1, Time e1, Time s2, Time e2) {
		
		 if(((s1==null && e1==null) && (s2!=null && e2!=null)) || ((s1!=null && e1!=null) && (s2==null && e2==null)))
			return false;
		 
		 if(!e1.before(s2) && !s1.after(e2)) {
			 if(e1.equals(s2) || s1.equals(e2)) {
				 return false;
			 }
			 else
				 return true;
		 }
		 else 
			 return false;
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public List<Timesheet> caricaTutto() {
		return timesheetRepository.findAll();
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	@Transactional
	public List<Timesheet> insert(List<Timesheet> timetables) {

		for(Timesheet t : timetables) {
			
			if((t.getStart1()==null && t.getEnd1()!=null) || (t.getStart2()==null && t.getEnd2()!=null) || (t.getStart1()!=null && t.getEnd1()==null) || (t.getStart2()!=null && t.getEnd2()==null)
					 || (t.getStart1()==null && t.getEnd1()==null && t.getStart2()==null && t.getEnd2()==null))
				
				 //SE GLI ORARI DI OGNI TIMESHEET HANNO UN INIZIO MA NON UNA FINE O VICEVERSA  OPPURE HA TUTTI ORARI NULL C'è  UN PROBLEMA
				 throw new IllegalHourException("ATTENZIONE: é presente una timesheet con orari non completi");
			if(t.getTot()>8)
				
				//SE LE ORE DI UNA TIMESHEET SONO MAGGIORI DI 8 C'è UN PROBLEMA
				throw new IllegalHourException("ATTENZIONE: le ore complessive della giornata sono maggiori di 8!");
		}
		
		return timesheetRepository.saveAll(timetables);
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------
	public Timesheet insertDefault(Long idUser,Timesheet timesheet) {
		
		List<Timesheet> t= new ArrayList<Timesheet>();
		if (userRepository.findById(idUser).isPresent())
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun utente con questo id");
		else {
			
			timesheet.setIdUser(idUser);
			timesheet.setType("D");
			t.add(timesheet);
			if(timesheetRepository.findByIdUserAndType(idUser, "D")!=null)
				
				throw new ServiceException("ATTENZIONE: esiste già una timesheet default per questo utente.Se si desidera modificarla andare su modifica");
		
			insert(t);
			return timesheet;
		}
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	public Timesheet getDefaultTimesheet(Long idUser) {
	
		String type="D";
		return timesheetRepository.findByIdUserAndType(idUser, type);
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public List<Timesheet> takeRecordsFromDateId(Date startDate, Long idUtente) {
		
		List<Timesheet> timetables = timesheetRepository.findByIdUserAndDate(idUtente, startDate);
		
		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non è stata trovata alcuna timesheet con i parametri inseriti");
		else
			return timetables;
		
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	@Transactional
	public boolean submitUtente(Long userId, Date datefrom) {
		List<Timesheet> listaT = takeRecordsFromDateId(datefrom, userId);
		java.util.Date today= new java.util.Date();
		Date sqltoday= convertUtilToSql(today);
		String s = "";
		double tot=0;
		for(Timesheet t: listaT) {
		//PER TUTTE LE TIMESHEET DELLA RICERCA (CHE PUò ANCHE ESSERE 1) SOMMO LE ORE TOTALI
			 tot += t.getTot();
			
			 if(!s.contains(t.getType()))
				 s += t.getType();
			 else
				 //SE CI SONO PIù TIMESHEET CON STESSA TIPOLOGIA NELLA STESSA DATA C'è UN PROBLEMA
				 throw new IllegalHourException("ATTENZIONE: ci sono più timesheet con la stessa tipologia");
			 
			 if((t.getStart1()==null && t.getEnd1()!=null) || (t.getStart2()==null && t.getEnd2()!=null) || (t.getStart1()!=null && t.getEnd1()==null) || (t.getStart2()!=null && t.getEnd2()==null)
					 || (t.getStart1()==null && t.getEnd1()==null && t.getStart2()==null && t.getEnd2()==null))
				
				 //SE GLI ORARI DI OGNI TIMESHEET HANNO UN INIZIO MA NON UNA FINE O VICEVERSA  OPPURE HA TUTTI ORARI NULL C'è  UN PROBLEMA
				 throw new IllegalHourException("ATTENZIONE: é presente una timesheet con orari non completi");
			 
		}
		if((s.contains("f") || s.contains("F")) && s.length()>1)
			//SE C'è UNA TIMESHEET FERIE ASSIEME AD ALTRE TIMESHEET CON STESSA DATA C'è UN PROBLEMA IN QUANTO SE SEI IN FERIE NON PUOI AVERE TIMESHEET PERMESSO O LAVORO
		throw new IllegalHourException("ATTENZIONE: è presente una timesheet di tipo ferie assieme ad altre tipologie: o sei in ferie o sei al lavoro!");
		
		if(listaT.size() == 1 && tot <= 8) {
			// SE IN QUEL GIORNO ABBIAMO UNA SOLA TIMESHEET E LE ORE SONO MENO O UGUALI A 8 (MINORE PER POSSIBILI PARTTIME) PUOI FARE SUBMIT.
			listaT.get(0).setSubmit(sqltoday);
			timesheetRepository.save(listaT.get(0));
			return true;
		}
		else
			// SE SIAMO QUA O LE ORE SONO MAGGIORI DI 8 OPPURE ABBIAMO PIù TIMESHEET
		{
			if(tot > 8) {
				//SE IL TOTALE DELLE ORE SUPERA 8 C'è UN PROBLEMA.
				System.out.println(tot);
				throw new IllegalHourException("ATTENZIONE: le ore complessive della giornata sono maggiori di 8!");
			}
			else
				//SE ARRIVIAMO QUA SIGNIFICA CHE ABBIAMO MINIMO 2 TIMESHEET E MASSIMO 3 TIMESHEET DI UNO STESSO GIORNO E DOBBIAMO VEDERE SE ABBIAMO ORARI CHE SI SOVRAPPONGONO.
			{
				Time primaStart1=listaT.get(0).getStart1();
				Time primaEnd1=listaT.get(0).getEnd1();
				Time primaStart2=listaT.get(0).getStart2();
				Time primaEnd2=listaT.get(0).getEnd2();
				Time secondaStart1=listaT.get(1).getStart1();
				Time secondaEnd1= listaT.get(1).getEnd1();
				Time secondaStart2=listaT.get(1).getStart2();
				Time secondaEnd2=listaT.get(1).getEnd2();
				
				for(int i=1;i<listaT.size();i++) {
					
					if(i==1) {
						
						if(hasOverlap(primaStart1,primaEnd1,secondaStart1,secondaEnd1)|| hasOverlap(primaStart2, primaEnd2, secondaStart2, secondaEnd2)){
							
							throw new IllegalHourException(" ATTENZIONE: sono presenti degli accavallamenti con gli orari");
						}
						else if(i==2) {
							
							Time ultimoStart1=listaT.get(i).getStart1();
							Time ultimoEnd1= listaT.get(i).getEnd1();
							Time ultimoStart2=listaT.get(i).getStart2();
							Time ultimoEnd2=listaT.get(i).getEnd2();
							
							if(hasOverlap(primaStart1, primaEnd1, ultimoStart1, ultimoEnd1)|| hasOverlap(primaStart2, primaEnd2, ultimoStart2, ultimoEnd2)) 
								throw new IllegalHourException(" ATTENZIONE: sono presenti degli accavallamenti con gli orari");
						
							if(hasOverlap(secondaStart1, secondaEnd1, ultimoStart1, ultimoEnd1)|| hasOverlap(secondaStart2, secondaEnd2, ultimoStart2, ultimoEnd2))
								throw new IllegalHourException(" ATTENZIONE: sono presenti degli accavallamenti con gli orari");
						}
					}	
				}
				
				for(Timesheet t: listaT) {
					t.setSubmit(sqltoday);
					timesheetRepository.save(t);
				}
				return true;
				}
			}
		}
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public boolean submitUtente(Long userId, Date datefrom, Date dateto) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction entr = entitymanager.getTransaction();
		entr.begin();
		if(dateto==null) {
			//CASO IN CUI SI SELEZIONA UN UNICO GIORNO SENZA QUINDI IL DATETO
			 return submitUtente(userId,datefrom);
		}

		else {
			if(dateto.before(datefrom))
				throw new IllegalDateException("ATTENZIONE: la data di fine non può essere precedente a quella di inizio");
			List<Timesheet> listaT = retrieveTimatablesInDateRangeByUserId(userId, datefrom, dateto);
			for(Timesheet t: listaT) {
				Date occorrenza = t.getDate();
				if(t.getSubmit()==null)
				 submitUtente(userId, occorrenza);
				
			}
			return true;
			
		}		
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean svuotaserver() {
		timesheetRepository.deleteAll();
		return true;		
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------
	public List<Timesheet> retrieveTimatablesInDateRangeByUserId(Long userId, Date dateFrom, Date dateTo) {

		List<Timesheet> timetables = timesheetRepository.findByIdUserAndDateGreaterThanEqualAndDateLessThanEqual(userId, dateFrom, dateTo);
		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non è stata trovata alcuna timesheet con i parametri inseriti");
		else
			return timetables;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------------
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public boolean validator(Long userId, Date dateFrom, Date dateTo) {
		
		java.util.Date today= new java.util.Date();
		Date sqltoday= convertUtilToSql(today);
		
		if(dateTo==null) {
			List<Timesheet> lista = controlloValidazione(retrieveTimatablesInDateRangeByUserId(userId, dateFrom, dateFrom));
			for(Timesheet t : lista) {
				if(t.getSubmit()!=null) {
					
					t.setValidated(sqltoday);
					timesheetRepository.save(t);
				}
			else 
				throw new UpdateException("ATTENZIONE: Non è possibile validare una timesheet che non è stata submittata");
			
			}	
			return true;
		}
		else {
			
			if(dateTo.before(dateFrom))
				throw new IllegalDateException("ATTENZIONE: la data di fine non può essere precedente a quella di inizio");
			
			List<Timesheet> lista = controlloValidazione(retrieveTimatablesInDateRangeByUserId(userId,  dateFrom,dateTo));	
			for(Timesheet t : lista) {
				
				if(t.getSubmit()!=null) {
					t.setValidated(sqltoday);
					timesheetRepository.save(t);				
			}
		}	
		return true;	
		}
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	public List<Timesheet> controlloValidazione(List<Timesheet> lista){
		//SE UNA TIMESHEET è GIà VALIDATA LA SALTO
		List<Timesheet> nuova = new ArrayList<Timesheet>();
		for(Timesheet t : lista) {
			if(t.getValidated()==null) {
				nuova.add(t);
			}			
		}
		return nuova;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------	
	public void updateTimesheet(Long id,Timesheet newt) {
		
		Timesheet t= timesheetRepository.getOne(id);
		
		if(t.getSubmit()!=null)
			throw new UpdateException("ATTENZIONE: una timesheet già submittata non può essere modificata");
		
		if(newt.getTot()>8) 
			//SE LE ORE TOTALI DELLA TIMESHEET SONO MAGGIORI DI 8 C'è UN PROBLEMA
			throw new IllegalHourException("ATTENZIONE: le ore complessive della giornata sono maggiori di 8!");
		
		if((newt.getStart1()==null && newt.getEnd1()!=null) || (newt.getStart2()==null && newt.getEnd2()!=null) || (newt.getStart1()!=null && newt.getEnd1()==null) || (newt.getStart2()!=null && newt.getEnd2()==null)|| (newt.getStart1()==null && newt.getEnd1()==null && newt.getStart2()==null && newt.getEnd2()==null))
			
			 //SE GLI ORARI DI OGNI TIMESHEET HANNO UN INIZIO MA NON UNA FINE O VICEVERSA  OPPURE HA TUTTI ORARI NULL C'è  UN PROBLEMA
			 throw new IllegalHourException("ATTENZIONE: é presente una timesheet con orari non completi");
		
		if(!Objects.isNull(newt.getDate())) t.setDate(newt.getDate());	
		if(!Objects.isNull(newt.getStart1())) t.setStart1(newt.getStart1());
		if(!Objects.isNull(newt.getEnd1())) t.setEnd1(newt.getEnd1());
		if(!Objects.isNull(newt.getStart2())) t.setStart2(newt.getStart2());
		if(!Objects.isNull(newt.getEnd2())) t.setEnd2(newt.getEnd2());
		if(!Objects.isNull(newt.getTot())) t.setTot(newt.getTot());
		if(!Objects.isNull(newt.getIdUser())) t.setIdUser(newt.getIdUser());
		if(!Objects.isNull(newt.getType())) t.setType(newt.getType());
			
		timesheetRepository.save(t);
		
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------
	public List<Timesheet> takeRecordsFromDateToDate(Date dateFrom, Date dateTo) {
		List<Timesheet> timetables = timesheetRepository.findByDateGreaterThanEqualAndDateLessThanEqual(dateFrom, dateTo);

		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non è stata trovata alcuna timesheet con i parametri inseriti");
		else
			return timetables;
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------	
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
//--------------------------------------------------------------------------------------------------------------------------------------------------------	
//	public static Timesheet singolatimesheet(int userId,Date dateFrom){
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		Query q = entitymanager.createQuery("FROM Timesheet t WHERE t.idUser = "+userId+" and t.date = '"+dateFrom+"'");
//		Timesheet timetables = (Timesheet) q.getSingleResult();
//		entitymanager.close();
//		if(timetables==null)
//			throw new NoContentException("ATTENZIONE: non è stata trovata alcuna timesheet con i parametri inseriti");
//		else
//			return timetables;
//	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------
	public void deleteOne(long id) {
		
		 timesheetRepository.deleteById(id);
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------	
	public static User findRecordsFromId(long id) {
		
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User user = entitymanager.find(User.class, id);
		return user;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------	
	public List<Timesheet> trovaTimesheets(Long idUser,Date dateFrom, Date dateTo, String type, boolean submit, boolean validated) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		
		List<String> searchQuery=new ArrayList<>();
		String whereClause="";
		
		if(submit== false && validated==true) {
			throw new NoContentException("ATTENZIONE: non è possibile cercare timesheet validate e non submittate allo stesso tempo!");
		}
		
		if(dateFrom==null && dateTo!=null)
			throw new IllegalDateException("ATTENZIONE: non è possibile fare una ricerca inserendo solo la data di fine e non di inizio periodo. Se si desidera fare una ricerca per singola data inserire la data di inizio. ");
		
		if (idUser != null) {
			searchQuery.add("a.idUser LIKE '%"+idUser+"%'");
			whereClause+="WHERE ";
		}
		
		if(dateTo==null) {
			if (dateFrom != null) {
				searchQuery.add("a.date LIKE '%"+dateFrom+"%'");
				if (whereClause.length()==0)
					whereClause+="WHERE ";
			}
		}
		else {
			searchQuery.add(" a.date >= '" + dateFrom + "' AND a.date<= '" + dateTo + "'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";	
			}
		
		if (type != null) {
			searchQuery.add("a.type LIKE '%"+type+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		
		if(validated==true) {
			
	 			searchQuery.add("a.validated IS NOT NULL");
				if (whereClause.length()==0)
					whereClause+="WHERE ";
		}
		else {
			
			searchQuery.add("a.validated IS NULL");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
			
			if (submit == false ) {
				searchQuery.add("a.submit IS NULL");
				if (whereClause.length()==0)
					whereClause+="WHERE ";
			}
			else {
					searchQuery.add("a.submit IS NOT NULL");
					if (whereClause.length()==0)
						whereClause+="WHERE ";}
		}
		
		System.out.println("Sto cercando");
		
		for (int i=0;i<searchQuery.size();i++) {
			whereClause+=searchQuery.get(i);
			if (i!=searchQuery.size()-1)
				whereClause+=" AND ";
		}
		
		System.out.println(whereClause);
		TypedQuery<Timesheet> query=entitymanager.createQuery("SELECT a from Timesheet a "+whereClause,Timesheet.class);
		
		List<Timesheet> timesheetlist=query.getResultList();
		
		entitymanager.close();
		
		return timesheetlist;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------	
	public List<Timesheet> trovaTimesheets(TimesheetRequest req) {
		
		 return trovaTimesheets(req.getIdUser(),req.getDateFrom(),req.getDateTo(),req.getType(), req.getSubmit(), req.getValidated());
	}
}