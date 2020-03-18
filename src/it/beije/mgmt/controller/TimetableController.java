package it.beije.mgmt.controller;

import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.service.TimetableService;



@Controller
@SessionScope
public class TimetableController {
	
	@Autowired
	private TimetableService timetableService;
	

	private Timesheet table = null;
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

	
	@RequestMapping(value= "/listtimetable", method= RequestMethod.GET)
	public String selezioneN(Locale locale, Model model) {
		
		System.out.println("Selezione n timesheet da inserire");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		return "ntimesheet";
	}
	
	
	static int totTimesheet;
	static int n;
	
	@RequestMapping(value="/firsttimesheet", method=RequestMethod.POST)
	public String primotimesheet (Model model, @RequestParam("nrighe") int tot) {
		totTimesheet=tot;
		n=1;
		model.addAttribute("n",n);
		return "addtimesheet";
	}
	
	@RequestMapping(value="/addtimesheet", method=RequestMethod.POST)
	public String addtimesheet(Model model, @Validated Timesheet timesheet) {
		
		if (TimetableService.findRecordsFromId(timesheet.getIdUser())==null)  {
			System.out.println("Utente non trovato");
			
			return "utentenontrovato";
		}
		
		
		double tot =timetableService.oreTrascorse(timesheet.getStart1(), timesheet.getEnd1(), timesheet.getStart2(),timesheet.getEnd2());
		timesheet.setTot(tot);
		
		listaTimesheet.add(timesheet);
		n++;
		if(totTimesheet>=n) {
		model.addAttribute("n",n);
		return "addtimesheet";
		}
		else {
		timetableService.insert(listaTimesheet);
		return "conferma";
		}
		
//		return timesheet(model,n,timesheet);

	}
		
	static List <Timesheet> listaTimesheet = new ArrayList<Timesheet>();
	public String timesheet(Model model,  @PathVariable("n") int n, @Validated Timesheet timesheet) {
		
	
		
		double tot =timetableService.oreTrascorse(timesheet.getStart1(), timesheet.getEnd1(), timesheet.getStart2(),timesheet.getEnd2());
		timesheet.setTot(tot);
		
		listaTimesheet.add(timesheet);
	
		if(totTimesheet>=n) {
		model.addAttribute("n",n);
		return "addtimesheet";
		}
		else {
		timetableService.insert(listaTimesheet);
		return "conferma";
		}
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
	@RequestMapping(value = "/cancella", method = RequestMethod.POST)
	public String ricercacanc (Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		
		
		
		return "cancellatimesheet";
	}


//	@PostMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
		public String user(@Validated Timesheet timetable,  Model model) {
		
		System.out.println("entro");
		
		if (TimetableService.findRecordsFromId(timetable.getIdUser())==null)  {
			System.out.println("Utente non trovato");
			return "utentenontrovato";
		}		

	double tot =timetableService.oreTrascorse(timetable.getStart1(), timetable.getEnd1(), timetable.getStart2(),timetable.getEnd2());
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
	
	@RequestMapping(value = "/confermacanc", method = RequestMethod.POST)
	public String cancellasheet() {
		System.out.println("Sto elaborando i tuoi dati");
		 timetableService.cancellaTimetable(tableU);
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
		Time s1 = null;
		Time e1 = null;
		Time s2 = null;
		Time e2 = null;
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
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String idUserDate(Model model, @RequestParam("date") java.sql.Date data, @RequestParam("id") int idUser) throws Exception {
		
		model.addAttribute("data",data);
		model.addAttribute("idUtente",idUser);
		
		//ricavo l'id degli utenti e ricerco per id_user per recuperare nome e cognome di ogni utente e fare la successiva stampa
		
		User utente = TimetableService.findRecordsFromId(idUser);
				
		model.addAttribute("utente", utente);

		List<Timesheet> tUtente = new ArrayList<Timesheet>();
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
		
		List<Timesheet> timetable = new ArrayList<Timesheet>();
		timetable = timetableService.takeRecordsFromDateId(data, idUser);
		
		Time s1=timetable.get(0).getStart1();
		Time e1=timetable.get(0).getEnd1();
		Time s2=timetable.get(0).getStart2();
		Time e2=timetable.get(0).getEnd2();
		timetable.get(0).setStart1(s1);
		timetable.get(0).setEnd1(e1);
		timetable.get(0).setStart2(s2);
		timetable.get(0).setEnd2(e2);
		
		Timesheet tableU = null;
		
		if(timetable != null)
			tableU = timetable.get(0);
		
		model.addAttribute("timetable",tableU);
		
		return "modificarecordtimetable";
	}
	static Timesheet  tableU = null;
	@RequestMapping(value = "/cancellatimesheet", method = RequestMethod.GET)
	public String cancellaTimeheet(Model model,  @RequestParam("date") java.sql.Date data, @RequestParam("id") int idUser) throws Exception {
		
				
		//ricavo l'id degli utenti e ricerco per id_user per recuperare nome e cognome di ogni utente e fare la successiva stampa
		if (TimetableService.findRecordsFromId(idUser)==null)  {
			System.out.println("Utente non trovato");
			return "utentenontrovato";
		}
		
		List<Timesheet> timetable = new ArrayList<Timesheet>();
		timetable = timetableService.takeRecordsFromDateId(data, idUser);
		
		Time s1=timetable.get(0).getStart1();
		Time e1=timetable.get(0).getEnd1();
		Time s2=timetable.get(0).getStart2();
		Time e2=timetable.get(0).getEnd2();
		timetable.get(0).setStart1(s1);
		timetable.get(0).setEnd1(e1);
		timetable.get(0).setStart2(s2);
		timetable.get(0).setEnd2(e2);
		
		
		
		if(timetable != null)
			tableU = timetable.get(0);
		
		model.addAttribute("timetable",tableU);
		
		return "confermacancellazione";
	}
	
	
	@RequestMapping(value = "/salvamodifiche", method = RequestMethod.POST)
	public String salvaModifiche (@Validated Timesheet timetable,@RequestParam("date") java.sql.Date data, @RequestParam("idUser") int idUser) {
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
