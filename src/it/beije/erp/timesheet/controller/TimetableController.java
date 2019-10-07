package it.beije.erp.timesheet.controller;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;


import it.beije.erp.timesheet.entity.Timetable;
import it.beije.erp.timesheet.entity.User;
import it.beije.erp.timesheet.service.TimetableService;



@Controller
@SessionScope
public class TimetableController {
	
	@Autowired
	private TimetableService timetableService;
	

	private Timetable table = null;
	String password;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/prehome", method = RequestMethod.GET)
	public String preHome(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "prehome";
	}

	
	
	@RequestMapping(value = "/timetable", method = RequestMethod.POST)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
//		model.addAttribute("password", password);
		return "timetable";
	}

	@RequestMapping(value = "/ricerca", method = RequestMethod.POST)
	public String ricerca (Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		
		
		
		return "ricerca";
	}


//	@PostMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated Timetable timetable, @RequestParam("idUser") int idUser,  Model model) {
		
		if (TimetableService.findRecordsFromId(timetable.getIdUser())==null)  {
			System.out.println("Utente non trovato");
			return "utentenontrovato";
		}		
		
		
			System.out.println("data passata:"+ timetable.getDate());

//		System.out.println("data insrita"+timetable.getIdUser());
//		System.out.println("data insrita"+timetable.getStart1());
//		System.out.println("data insrita"+timetable.getEnd1());
//		System.out.println("data insrita"+timetable.getStart2());
//		System.out.println("data insrita"+timetable.getEnd2());
//		table.setDate(timetable.getDate());
//		
//		table.setEnd1(timetable.getEnd1());
//		table.setEnd2(timetable.getEnd2());
//		table.setStart1(timetable.getStart1());
//		table.setStart2(timetable.getStart2());
	table=timetable;
	double tot =	timetableService.oreTrascorse(timetable.getStart1(), timetable.getEnd1(), timetable.getStart2(),timetable.getEnd2());
		timetable.setTot(tot);
	model.addAttribute("timetable", timetable);
		
		System.out.println("data saddsxta"+ table.getDate());
	
		
		return "user";
	}
	
	@RequestMapping(value = "/confermadatitimetable", method = RequestMethod.POST)
	public String elaboraDati () {
		System.out.println("Sto elaborando i tuoi dati...");
		 timetableService.creaRecordTimetable(table);
		 return "conferma";
	}

	@RequestMapping(value = "/modifica", method = RequestMethod.POST)
	public String modificaDati(Model model, Locale locale) {

		Date date = new Date ();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		System.out.println("Modifica dei dati...");
		
		model.addAttribute(table);
		System.out.println("fine modifica sul controller");
		String s1 = null;
		String e1 = null;
		String s2 = null;
		String e2 = null;
		try {
			s1 = table.getStart1();
		}
		catch (StringIndexOutOfBoundsException e) {
			s1=null;
		}
		try {
			e1 = table.getEnd1();
		}
		catch (StringIndexOutOfBoundsException e) {
			e1=null;
		}
		try {
			s2 = table.getStart2();
		}
		catch (StringIndexOutOfBoundsException e) {
		s2=null;
		}
		try {
			e2 = table.getEnd2();
		}
		catch (StringIndexOutOfBoundsException e) {
		e2=null;
		}
				
			
		
		if (s1==null||e1==null) {
			if (!(s2==null||e2==null)) {
			table.setTot(timetableService.oreTrascorse(s2, e2));
			 timetableService.creaRecordTimetable(table);
			return "conferma";
			}
			else return "erroremodificaorari";
		}
		else if (s2==null||e2==null) {
			System.out.println("prima if prima metodo");
			if (!(s1==null||e1==null)) {
				System.out.println("dopo if prima metodo");
			table.setTot(timetableService.oreTrascorse(s1, e1));
			System.out.println("dopo if dopo metodo");
			 timetableService.creaRecordTimetable(table);
			return "conferma";
			}
			else return "erroremodificaorari";
		}
		else {
		table.setTot(timetableService.oreTrascorse(s1, e1, s2, e2));
//		table=timetable;
		model.addAttribute("timetable", table);
		System.out.println("Controllo"+table.getType());
		 timetableService.creaRecordTimetable(table);
		return "conferma";}
	}
		
	
	@RequestMapping(value = "/erroremodificaorari", method = RequestMethod.GET)
	public String erroreOrari () {
		System.out.println("hai cannato gli orari, torna al modifica");
		return "modifica";
	}
	
	@RequestMapping(value = "/utentenontrovato", method = RequestMethod.GET)
	public String nonTrovato () {
		System.out.println("Ti stiamo reinderizzando alla home");
		return "prehome";
	}
	
//	@RequestMapping(value = "/data", method = RequestMethod.GET)
//	public String user(Model model, @RequestParam("i") java.sql.Date i) throws Exception {
//		
//		//stampa ed inserimento nel model del parametro date ricevuto dal form
//		System.out.println(i);
//		model.addAttribute("stampa",i);
//		
//		//recupero gli oggetti timetable relativi a quella data
//	//	List<Timetable> t = TimeSheetMethods.takeRecordsFromDate(i);
//		
////		//salvo la lista degli indici degli utenti e per ogni elemento recupero l'utente
////		List<Integer> indici_utenti = new ArrayList<Integer>();
////		
////		for (Timetable table : t) {
////			indici_utenti.add(table.getId_user());
////		}
////		
////		System.out.println(t.size());
////		System.out.println(t.get(0).getId_user());
////		
////		
////		//ricavo l'id degli utenti e ricerco per id_user per recuperare nome e cognome di ogni utente e fare la successiva stampa
////		List<User> utenti = new ArrayList<User>();
////		
////		
////		for (int j = 0; j < indici_utenti.size(); j++) {
////			User temp = TimeSheetMethods.findRecordsFromId(indici_utenti.get(j));
////			System.out.println(temp.getFirst_name()+ temp.getLast_name());
////			utenti.add(temp);
////		}
////				
////		model.addAttribute("listaUtenti", utenti);
////		
//		List<Timetable> tUtente = new ArrayList<Timetable>();
//		tUtente = TimeSheetMethods.takeRecordsFromDateId(i, 1);
//		
//		for (Timetable tu : tUtente) {
//			System.out.println(tu.getId());
//			System.out.println(tu.getId_user());
//			System.out.println(tu.getTot());
//		}
//		
//		return "data";
//	}
	
	
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String idUserDate(Model model, @RequestParam("date") java.sql.Date data, @RequestParam("id") int idUser) throws Exception {
		
		model.addAttribute("data",data);
		model.addAttribute("idUtente",idUser);
		
		//ricavo l'id degli utenti e ricerco per id_user per recuperare nome e cognome di ogni utente e fare la successiva stampa
		
		User utente = TimetableService.findRecordsFromId(idUser);
				
		model.addAttribute("utente", utente);

		List<Timetable> tUtente = new ArrayList<Timetable>();
		tUtente = timetableService.takeRecordsFromDateId(data, 1);
		
		return "data";
	}
	
	
	@RequestMapping(value = "/modificarecordtimetable", method = RequestMethod.GET)
	public String modificaUtente(Model model,  @RequestParam("date") java.sql.Date data, @RequestParam("id") int idUser) throws Exception {
		
				
		//ricavo l'id degli utenti e ricerco per id_user per recuperare nome e cognome di ogni utente e fare la successiva stampa
		if (TimetableService.findRecordsFromId(idUser)==null)  {
			System.out.println("Utente non trovato");
			return "utentenontrovato";
		}
		
		List<Timetable> timetable = new ArrayList<Timetable>();
		timetable = timetableService.takeRecordsFromDateId(data, idUser);
		
		String s1=timetable.get(0).getStart1().substring(0,5);
		String e1=timetable.get(0).getEnd1().substring(0,5);
		String s2=timetable.get(0).getStart2().substring(0,5);
		String e2=timetable.get(0).getEnd2().substring(0,5);
		timetable.get(0).setStart1(s1);
		timetable.get(0).setEnd1(e1);
		timetable.get(0).setStart2(s2);
		timetable.get(0).setEnd2(e2);
		
		Timetable tableU = null;
		
		if(timetable != null)
			tableU = timetable.get(0);
		
		model.addAttribute("timetable",tableU);
		
		return "modificarecordtimetable";
	}
	
	
	@RequestMapping(value = "/salvamodifiche", method = RequestMethod.POST)
	public String salvaModifiche (@Validated Timetable timetable,@RequestParam("date") java.sql.Date data, @RequestParam("idUser") int idUser) {
		System.out.println("Sto elaborando i tuoi dati...");
//		
//		
		System.out.println(timetable.getIdUser());
		System.out.println(timetable.getType());
		System.out.println(timetable.getStart1());
		System.out.println(timetable.getEnd1());
		System.out.println(timetable.getStart2());
		System.out.println("controlloCosimo" + timetable.getEnd2());
		
		
		 timetableService.updateRecord(idUser, data, timetable);
		 
		 return "conferma";

	}
	

}
