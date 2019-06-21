package it.beije.erp.timesheet.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.beije.erp.timesheet.entity.User;

import it.beije.erp.timesheet.service.UserService;

//import it.beije.jpa.GestioneUtenti;

@Controller
@SessionAttributes("user")
//@RequestMapping("/user")
public class UserController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String dataFormattato = dateFormat.format(date);

		model.addAttribute("serverTime", dataFormattato);

		return "home";
	}

	@RequestMapping(value = "/confermadati", method = RequestMethod.POST)
	public String confermaDati(@Validated User user, String lastName, Model model) {
		System.out.println("pagina confermadati: " + user.getFirstName());
		System.out.println(user.getLastName());
		//Passa i parametri alla view ritornato
		//		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("user", user);
		return "confermadati";
	}

	@RequestMapping(value = "/inserisciutente", method = RequestMethod.POST)
	public String index(@Validated User user, Model model) {
		System.out.println("Pagine inseriti: " + user.getLastName());
		model.addAttribute("userName", user);

		System.out.println("sono in inserisciutente");

		return "inserisciutente";
	}

	@RequestMapping(value = "/conferma", method = RequestMethod.POST)
	public String conferma(@Validated User user, Model model) {
		new UserService().create(user);
		
		System.out.println("sono in confernama " + user.getFirstName());
		return "conferma";
	}

	@RequestMapping(value = "/modificautente", method = RequestMethod.POST)
	public String modificaUtente(@Validated User user, Model model) {

		return "modificautente";
	}


	
	@RequestMapping(value = "/cercautente", method = RequestMethod.POST)
	public String cercaUtente(@Validated User user, Model model) {
		
		return "cercautente";
	}
	
	@RequestMapping(value = "/utentitrovati", method = RequestMethod.GET)
	public String utentiTrovati(@Validated User user, Model model) {
		String trovati = new UserService().trovaUtente(user.getFirstName(),user.getLastName());
		user.setPersonalEmail(trovati);
		return "utentitrovati";
	}

	
	@RequestMapping(value = "/modificadati", method = RequestMethod.POST)
	public String modificaDati(@Validated User user, Model model) {
		
		try {
		
		user = new UserService().trovaID(user.getId());
		user.getFirstName();
		model.addAttribute("user", user);
			return "modificadati";
		}
		catch (NullPointerException e) {
			return "idnontrovato";
		}
	
	}
	
	@RequestMapping(value = "/idnontrovato", method = RequestMethod.POST)
	public String idNonTrovato(@Validated User user, Model model) {

		
		return "idnontrovato";
	}
	
	@RequestMapping(value = "/confermamodificadati", method = RequestMethod.POST)
	public String confermaModificaDati(@Validated User user, Model model) {

		new UserService().modificaUtente(user);
		return "conferma";
	}
	
	@RequestMapping(value = "/cancellautente", method = RequestMethod.POST)
	public String cancellaUtente(@Validated User user, Model model) {

		return "cancellautente";
	}

	@RequestMapping(value = "/confermacancellazione", method = RequestMethod.POST)
	public String confermaCancellazione(@Validated User user, Model model) {

		new UserService().archiviaUtente(user);
		return "confermacancellazione";
	}
	
	
	///////////////NEW METHOD
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage(@Validated User user, Model model) {

		return "homepage";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(@Validated User user, Model model) {

		return "login";
		
	
	}
	
	@RequestMapping(value = "/temporanea", method = RequestMethod.GET)
	public String temporanea(@Validated User user, Model model) {

		return "temporanea";
	}
}
