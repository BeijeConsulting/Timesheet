package it.beije.timesheet.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.timesheet.entities.*;
import it.beije.timesheet.entities.methods.TimeSheetMethods;

@Controller
public class HomeController {

	private Timetable table = null;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated Timetable timetable, Model model) {

		String s1 = timetable.getStart1();
		String e1 = timetable.getEnd1();
		String s2 = timetable.getStart2();
		String e2 = timetable.getEnd2();

		timetable.setTot(TimeSheetMethods.oreTrascorse(s1, e1, s2, e2));
		table = timetable;
		// model.addAttribute("timetable", timetable);
		return "user";
	}

	@RequestMapping(value = "/conferma", method = RequestMethod.POST)
	public void elaboraDati(@Validated User user, Model model) {
		System.out.println("Sto elaborando i tuoi dati...");

	}

	@RequestMapping(value = "/modifica", method = RequestMethod.POST)
	public String modificaDati(Model model) {
		System.out.println("Modifica dei dati...");
		model.addAttribute(table);
		return "modifica";
	}

}
