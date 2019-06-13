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

	
	
	@RequestMapping(value = "/hometimetable", method = RequestMethod.POST)
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
	public String user(@Validated Timetable timetable, @RequestParam("idUser") int id, Model model) {
		
		if (TimetableService.findRecordsFromId(timetable.getIdUser())==null)  {
			System.out.println("Utente non trovato");
			return "utentenontrovato";
		}
		
//		System.out.println(pass);
//		
//		if (!(timetableService.checkPassword(id, pass)))  {
//			System.out.println("id e password non corrispondono");
//			return "timetable";
//		}
		
		//CONTROLLO DEL METODO :
//		tab =TimeSheetMethods.takeRecordsFromDate(timetable.getDate());
//		if (tab!=null)  {
//			System.out.println("Le occorrenze da quella data sono ");
//			for (Timetable r: tab)  {
//				System.out.println(r.getId_user());
//				System.out.println(r.getDate());
//				System.out.println(r.getType());
//				System.out.println(r.getStart1());
//				System.out.println(r.getEnd1());
//				System.out.println(r.getStart2());
//				System.out.println(r.getEnd2());
//			}
//		}
		
		String s1 = timetable.getStart1();
		String e1 = timetable.getEnd1();
		String s2 = timetable.getStart2();
		String e2 = timetable.getEnd2();

		timetable.setTot(timetableService.oreTrascorse(s1, e1, s2, e2));
		table=timetable;
//		System.out.println(table.getDate());
//		System.out.println(table.getId_user());
//		System.out.println(table.getStart1());
//		System.out.println(table.getEnd1());
//		System.out.println(table.getStart2());
//		System.out.println(table.getEnd2());
		model.addAttribute("timetable", timetable);
		System.out.println("Controllo"+timetable.getType());
//		model.addAttribute("id_user", timetable.getId_user());
//	
//		model.addAttribute("data", timetable.getDate());
//		model.addAttribute("type", timetable.getType());
//		model.addAttribute("orariodiinizio", timetable.getStart1());
//		model.addAttribute("orariodifine", timetable.getStart2());
//		model.addAttribute("secondorariodiinizio", timetable.getEnd1());
//		model.addAttribute("secondoorariodifine", timetable.getEnd2());
//		model.addAttribute("totale", TimeSheetMethods.calculateTotalHour(timetable.getStart1(), timetable.getEnd1(), timetable.getStart2(), timetable.getEnd2()));
		return "user";
	}
	
	@RequestMapping(value = "/confermadatitimetable", method = RequestMethod.POST)
	public void elaboraDati () {
		System.out.println("Sto elaborando i tuoi dati...");
//		
//		
//		System.out.println(table.getId_user());
//		System.out.println(table.getType());
//		System.out.println(table.getStart1());
//		System.out.println(table.getEnd1());
//		System.out.println(table.getStart2());
//		System.out.println(table.getEnd2());
//		System.out.println(table.getTot());
//		
		
		 timetableService.creaoRecordTimetable(table);
	}

	@RequestMapping(value = "/modifica", method = RequestMethod.POST)
	public String modificaDati(Model model, Locale locale) {

		Date date = new Date ();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		System.out.println("Modifica dei dati...");
		
		model.addAttribute(table);
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
			return "NonTiAbbiamoTrovato";
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
		 
		 return "confermadatitimetable";

	}
	

}
