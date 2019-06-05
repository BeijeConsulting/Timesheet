package it.beije.timesheet.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

import it.beije.timesheet.entities.Timetable;
import it.beije.timesheet.entities.methods.TimeSheetMethods;


@Controller
@SessionScope
public class HomeController {

	private Timetable table = null;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String preHome(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "preHome";
	}

	
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
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


	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated Timetable timetable, Model model) {
		List <Timetable> tab = new ArrayList <Timetable> ();
		if (TimeSheetMethods.findRecordsFromId(timetable.getId_user())==null)  {
			System.out.println("Utente non trovato");
			return "NonTiAbbiamoTrovato";
		}
		
		//CONTROLLO DEL METODO :
		
		tab =TimeSheetMethods.takeRecordsFromDate(timetable.getDate());
		if (tab!=null)  {
			System.out.println("Le occorrenze da quella data sono ");
			for (Timetable r: tab)  {
				System.out.println(r.getId_user());
				System.out.println(r.getDate());
				System.out.println(r.getType());
				System.out.println(r.getStart1());
				System.out.println(r.getEnd1());
				System.out.println(r.getStart2());
				System.out.println(r.getEnd2());
			}
		}
		
		String s1 = timetable.getStart1();
		String e1 = timetable.getEnd1();
		String s2 = timetable.getStart2();
		String e2 = timetable.getEnd2();

		timetable.setTot(TimeSheetMethods.oreTrascorse(s1, e1, s2, e2));
		table=timetable;
//		System.out.println(table.getDate());
//		System.out.println(table.getId_user());
//		System.out.println(table.getStart1());
//		System.out.println(table.getEnd1());
//		System.out.println(table.getStart2());
//		System.out.println(table.getEnd2());
		model.addAttribute("timetable", timetable);
		
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
	
	@RequestMapping(value = "/pagineDopoConferma", method = RequestMethod.POST)
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
		
		TimeSheetMethods.creaoModificaRecord(table);
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
	
	@RequestMapping(value = "/NonTiAbbiamoTrovato", method = RequestMethod.GET)
	public String nonTrovato () {
		System.out.println("Ti stiamo reinderizzando alla home");
		return "home";
	}

}
