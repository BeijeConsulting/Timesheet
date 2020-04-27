package it.beije.mgmt.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.dto.TimesheetSearchRequest;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.IllegalDateException;
import it.beije.mgmt.exception.IllegalHourException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.UpdateException;
import it.beije.mgmt.repository.SearchCriteria;
import it.beije.mgmt.repository.SearchOperation;
import it.beije.mgmt.repository.TimesheetRepository;
import it.beije.mgmt.repository.TimesheetSpecification;
import it.beije.mgmt.repository.UserRepository;
import it.beije.mgmt.repository.UserSpecification;
import it.beije.mgmt.tool.Utils;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;


@Service
public class TimesheetService {
	
	@Autowired
	private TimesheetRepository timesheetRepository;
	
	@Autowired
	private UserRepository userRepository;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	private boolean hasOverlap(Time s1, Time e1, Time s2, Time e2) {
		
		 if(((s1==null && e1==null) && (s2!=null && e2!=null)) || ((s1!=null && e1!=null) && (s2==null && e2==null)))
			return false;
		 if(!e1.before(s2) && !s1.after(e2)) {
			 if(e1.equals(s2) || s1.equals(e2)) {
				 return false;
			 }else
				 return true;
		 }else 
			 return false;
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public List<Timesheet> findAll() {
		
		List<Timesheet> completeTimes = timesheetRepository.findAll();
		if(completeTimes.size()==0)
			throw new NoContentException("La lista è vuota");
		return completeTimes;
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
				//SE LE ORE DI UNA TIMESHEET SONO MAGGIORI DI 8h C'è UN PROBLEMA
				throw new IllegalHourException("ATTENZIONE: le ore complessive della giornata sono maggiori di 8!");
		}
		try {
			return timesheetRepository.saveAll(timetables);
		}catch(EntityExistsException e) {
			throw new ServiceException("Timesheet già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		}
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------
	public Timesheet insertDefault(Long idUser,Timesheet timesheet) {
		
		List<Timesheet> t= new ArrayList<Timesheet>();

		try {
			if (!userRepository.findById(idUser).isPresent())
				throw new NoContentException("ATTENZIONE: non è stato trovato alcun utente con questo id");
		
			timesheet.setIdUser(idUser);
			timesheet.setType("D");
			t.add(timesheet);
			try {
				getDefaultTimesheet(idUser);
			}catch(NoContentException e) {
					insert(t);
					return timesheet;
			}
		}catch (RuntimeException e) {
			throw e;
		}
		throw new ServiceException("Non è stato possibile inserire il timesheet di default");
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	public Timesheet getDefaultTimesheet(Long idUser) {
		
		try {
			TimesheetSpecification spFindDef = new TimesheetSpecification();
			spFindDef.add(new SearchCriteria("idUser", idUser, SearchOperation.EQUAL));
			spFindDef.add(new SearchCriteria("type", "D", SearchOperation.EQUAL));
			return timesheetRepository.findOne(spFindDef).get();
		}catch (NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un utente con l'id selezionato o i dati potrebbero essere corrotti");
		}catch (IncorrectResultSizeDataAccessException e) {
			throw new ServiceException("Dati duplicati");
		}
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public List<Timesheet> getRecordsFromDateId(Date startDate, Long idUser) {
		
		TimesheetSpecification spFindDef = new TimesheetSpecification();
		spFindDef.add(new SearchCriteria("idUser", idUser, SearchOperation.EQUAL));
		spFindDef.add(new SearchCriteria("startDate", startDate, SearchOperation.EQUAL));
		List<Timesheet> timetables = timesheetRepository.findAll(spFindDef);
		
		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non è stata trovata alcuna timesheet con i parametri inseriti");
		return timetables;
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	@Transactional
	public boolean submitUser(Long userId, Date datefrom) {
		List<Timesheet> listaT = getRecordsFromDateId(datefrom, userId);
		Date sqltoday= Date.valueOf((LocalDate.now()));
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
		if(tot > 8)
			//SE IL TOTALE DELLE ORE SUPERA 8 C'è UN PROBLEMA.
			throw new IllegalHourException("ATTENZIONE: le ore complessive della giornata sono maggiori di 8!");
		if((s.toLowerCase().contains("f")) && s.length()>1)
			//SE C'è UNA TIMESHEET FERIE ASSIEME AD ALTRE TIMESHEET CON STESSA DATA C'è UN PROBLEMA IN QUANTO SE SEI IN FERIE NON PUOI AVERE TIMESHEET PERMESSO O LAVORO
			throw new IllegalHourException("ATTENZIONE: è presente una timesheet di tipo ferie assieme ad altre tipologie: o sei in ferie o sei al lavoro!");
		if(listaT.size() == 1) {
			// SE IN QUEL GIORNO ABBIAMO UNA SOLA TIMESHEET E LE ORE SONO MENO O UGUALI A 8 (MINORE PER POSSIBILI PARTTIME) PUOI FARE SUBMIT.
			listaT.get(0).setSubmit(sqltoday);
			timesheetRepository.save(listaT.get(0));
			return true;
		}
		//SE ARRIVIAMO QUA SIGNIFICA CHE ABBIAMO MINIMO 2 TIMESHEET E MASSIMO 3 TIMESHEET DI UNO STESSO GIORNO E DOBBIAMO VEDERE SE ABBIAMO ORARI CHE SI SOVRAPPONGONO.
		Time primaStart1=listaT.get(0).getStart1();
		Time primaEnd1=listaT.get(0).getEnd1();
		Time primaStart2=listaT.get(0).getStart2();
		Time primaEnd2=listaT.get(0).getEnd2();
		Time secondaStart1=listaT.get(1).getStart1();
		Time secondaEnd1= listaT.get(1).getEnd1();
		Time secondaStart2=listaT.get(1).getStart2();
		Time secondaEnd2=listaT.get(1).getEnd2();
			
		for(int i=1;i<listaT.size();i++) {
			if(i==1)
				if(hasOverlap(primaStart1,primaEnd1,secondaStart1,secondaEnd1)|| hasOverlap(primaStart2, primaEnd2, secondaStart2, secondaEnd2))
						throw new IllegalHourException(" ATTENZIONE: sono presenti degli accavallamenti con gli orari");
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
		try {
			for(Timesheet t: listaT) {
				t.setSubmit(sqltoday);
				timesheetRepository.saveAndFlush(t);
			}
			return true;
		}catch(EntityExistsException eee) {
			throw new ServiceException("Entity già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		}
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public boolean submitUser(Long userId, Date datefrom, Date dateto) {
		if(dateto==null) {
			//CASO IN CUI SI SELEZIONA UN UNICO GIORNO SENZA QUINDI IL DATETO
			 return submitUser(userId,datefrom);
		}
		if(dateto.before(datefrom))
			throw new IllegalDateException("ATTENZIONE: la data di fine non può essere precedente a quella di inizio");
		
		List<Timesheet> listaT = retrieveTimatablesInDateRangeByUserId(userId, datefrom, dateto);
		for(Timesheet t: listaT) {
			Date occorrenza = t.getDate();
			if(t.getSubmit()==null)
				submitUser(userId, occorrenza);	
		}
		return true;	
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean svuotaserver() {
		timesheetRepository.deleteAll();
		return true;		
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------
	public List<Timesheet> retrieveTimatablesInDateRangeByUserId(Long idUser, Date dateFrom, Date dateTo) {

		TimesheetSpecification spFindDef = new TimesheetSpecification();
		spFindDef.add(new SearchCriteria("idUser", idUser, SearchOperation.EQUAL));
		spFindDef.add(new SearchCriteria("date", dateFrom, SearchOperation.GREATER_THAN_EQUAL));
		spFindDef.add(new SearchCriteria("date", dateTo, SearchOperation.LESS_THAN_EQUAL));
		List<Timesheet> timetables = timesheetRepository.findAll(spFindDef);
		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non è stata trovata alcuna timesheet con i parametri inseriti");
		return timetables;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------------
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public boolean validator(Long userId, Date dateFrom, Date dateTo) {
		
		Date sqltoday= Date.valueOf(LocalDate.now());
		
		if(dateTo==null) {
			List<Timesheet> lista = checkValidations(retrieveTimatablesInDateRangeByUserId(userId, dateFrom, dateFrom));
			for(Timesheet t : lista) {
				if(t.getSubmit()!=null) {
					t.setValidated(sqltoday);
					timesheetRepository.save(t);
				} else 
					throw new UpdateException("ATTENZIONE: Non è possibile validare una timesheet che non è stata submittata");
			}	
			return true;
		}
		if(dateTo.before(dateFrom))
			throw new IllegalDateException("ATTENZIONE: la data di fine non può essere precedente a quella di inizio");
		
		List<Timesheet> lista = checkValidations(retrieveTimatablesInDateRangeByUserId(userId,  dateFrom,dateTo));	
		for(Timesheet t : lista) {	
			if(t.getSubmit()!=null) {
				t.setValidated(sqltoday);
				timesheetRepository.save(t);				
			}
		}	
		return true;	
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	private List<Timesheet> checkValidations(List<Timesheet> lista){
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
		
		try {
			Timesheet t= timesheetRepository.findById(id).get();
		
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
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un timesheet con l'id selezionato o i dati potrebbero essere corrotti");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------
	public List<Timesheet> takeRecordsFromDateToDate(Date dateFrom, Date dateTo) {
		TimesheetSpecification spFindDef = new TimesheetSpecification();
		spFindDef.add(new SearchCriteria("date", dateFrom, SearchOperation.GREATER_THAN_EQUAL));
		spFindDef.add(new SearchCriteria("date", dateTo, SearchOperation.LESS_THAN_EQUAL));
		List<Timesheet> timetables = timesheetRepository.findAll(spFindDef);

		if(timetables.isEmpty())
			throw new NoContentException("ATTENZIONE: non è stata trovata alcuna timesheet con i parametri inseriti");
		
		return timetables;
	}
	
	public void deleteOne(long id) {
		
		 timesheetRepository.deleteById(id);
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------	
	public List<Timesheet> searchTimesheets(Long idUser,Date dateFrom, Date dateTo, String type, boolean submit, boolean validated) {
		
		TimesheetSpecification spFindDef = new TimesheetSpecification();
		
		if(submit== false && validated==true)
			throw new NoContentException("ATTENZIONE: non è possibile cercare timesheet validate e non submittate allo stesso tempo!");

		if(dateFrom==null && dateTo!=null)
			throw new IllegalDateException("ATTENZIONE: non è possibile fare una ricerca inserendo solo la data di fine e non di inizio periodo. Se si desidera fare una ricerca per singola data inserire la data di inizio. ");
		
		if (idUser != null)
			spFindDef.add(new SearchCriteria("idUser", idUser, SearchOperation.MATCH));
		if (dateFrom != null)
			spFindDef.add(new SearchCriteria("date", dateFrom, SearchOperation.GREATER_THAN_EQUAL));
		if(dateTo!=null)
			spFindDef.add(new SearchCriteria("date", dateTo, SearchOperation.LESS_THAN_EQUAL));
		if (type != null)
			spFindDef.add(new SearchCriteria("type", type, SearchOperation.MATCH));	
		
		if(validated)
			spFindDef.add(new SearchCriteria("validated", null, SearchOperation.NOT_EQUAL));
		else
			spFindDef.add(new SearchCriteria("validated", null, SearchOperation.EQUAL));
		if(submit)
			spFindDef.add(new SearchCriteria("validated", null, SearchOperation.NOT_EQUAL));
		else
			spFindDef.add(new SearchCriteria("submit", null, SearchOperation.EQUAL));

		return timesheetRepository.findAll(spFindDef);
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------	
	public List<Timesheet> searchTimesheets(TimesheetSearchRequest req) {
		
		 return searchTimesheets(req.getIdUser(),req.getDateFrom(),req.getDateTo(),req.getType(), req.getSubmit(), req.getValidated());
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------	
//	public double oreTrascorse(Time time, Time time2, Time time3, Time time4) { //Calcolo ore in orario lavorativo normale
//		double tempoTrascorso = 0;
//		LocalTime.parse(time.toString()).getHour();
//	//	tempoTrascorso =((time2.getHours()-time.getHours())+(time4.getHours()-time3.getHours()));
//		tempoTrascorso =(LocalTime.parse(time2.toString()).getHour()-LocalTime.parse(time.toString()).getHour())+(LocalTime.parse(time4.toString()).getHour()-LocalTime.parse(time3.toString()).getHour());
//		return tempoTrascorso;

//	}
//	public double oreTrascorse(Time time,Time time2) {
//		double tempoTrascorso = 0;
//		tempoTrascorso =LocalTime.parse(time2.toString()).getHour()-LocalTime.parse(time.toString()).getHour();
//		return tempoTrascorso;
//
//	}
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
}