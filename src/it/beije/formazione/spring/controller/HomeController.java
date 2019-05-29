package it.beije.formazione.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

import it.beije.formazione.spring.model.User;
import it.beije.jpa.GestioneUtenti;

@Controller
@SessionScope
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String dataFormattato = dateFormat.format(date);

		//		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("serverTime", dataFormattato);

		return "home";
	}

	@RequestMapping(value = "/confermaDati", method = RequestMethod.POST)
	public String confermaDati(@Validated User user, Model model) {
		System.out.println("pagina confermaDati: " + user.getFirstName());

		//Passa i parametri alla view ritornato
		//		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("user", user);
		return "confermaDati";
	}

	@RequestMapping(value = "/inserisciUtente", method = RequestMethod.POST)
	public String index(@Validated User user, Model model) {
		System.out.println("Pagine inseriti: " + user.getLastName());
		model.addAttribute("userName", user.getFirstName());

		System.out.println("sono in inserisciUtente");

		return "inserisciUtente";
	}

	@RequestMapping(value = "/conferma", method = RequestMethod.POST)
	public String conferma(@Validated User user, Model model) {
		GestioneUtenti.creaUtente(user.getFirstName(), user.getLastName(), user.getPersonalEmail(), 
				user.getWorkEmail(), user.getPhone(), user.getFiscalCode(), 0, user.getPassword());
		System.out.println("sono in confernama " + user.getFirstName());
		return "conferma";
	}

	@RequestMapping(value = "/modificaUtente", method = RequestMethod.POST)
	public String modificaUtente(@Validated User user, Model model) {

		return "modificaUtente";
	}

	@RequestMapping(value = "/cancellaUtente", method = RequestMethod.POST)
	public String cancellaUtente(@Validated User user, Model model) {

		return "cancellaUtente";
	}

	@RequestMapping(value = "/cercaUtente", method = RequestMethod.POST)
	public String cercaUtente(@Validated User user, Model model) {

		return "cercaUtente";
	}

}
