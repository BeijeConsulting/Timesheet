package it.beije.erp.timesheet.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.beije.erp.timesheet.entity.User;

import it.beije.erp.timesheet.service.UserService;

//import it.beije.jpa.GestioneUtenti;

@Controller
@SessionAttributes("user")
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

	@RequestMapping(value = "/conferma_dati", method = RequestMethod.POST)
	public String confermaDati(@Validated User user, Model model) {
		System.out.println("pagina conferma_dati: " + user.getFirstName());

		//Passa i parametri alla view ritornato
		//		model.addAttribute("userName", user.getFirstName());
		model.addAttribute("user", user);
		return "conferma_dati";
	}

	@RequestMapping(value = "/inserisci_utente", method = RequestMethod.POST)
	public String index(@Validated User user, Model model) {
		System.out.println("Pagine inseriti: " + user.getLastName());
		model.addAttribute("userName", user.getFirstName());

		System.out.println("sono in inserisci_utente");

		return "inserisci_utente";
	}

	@RequestMapping(value = "/conferma", method = RequestMethod.POST)
	public String conferma(@Validated User user, Model model) {
		new UserService().create(user);
		
		System.out.println("sono in confernama " + user.getFirstName());
		return "conferma";
	}

	@RequestMapping(value = "/modifica_utente", method = RequestMethod.POST)
	public String modificaUtente(@Validated User user, Model model) {

		return "modifica_utente";
	}


	
	@RequestMapping(value = "/cerca_utente", method = RequestMethod.POST)
	public String cercaUtente(@Validated User user, Model model) {
		
		return "cerca_utente";
	}
	
	@RequestMapping(value = "/utenti_trovati", method = RequestMethod.GET)
	public String utentiTrovati(@Validated User user, Model model) {
		String trovati = new UserService().trovaUtente(user.getFirstName(),user.getLastName());
		user.setPersonalEmail(trovati);
		return "utenti_trovati";
	}

	
	@RequestMapping(value = "/modifica_dati", method = RequestMethod.POST)
	public String modificaDati(@Validated User user, Model model) {
		
		try {
		
		user = new UserService().trovaID(user.getId());
		user.getFirstName();
		model.addAttribute("user", user);
			return "modifica_dati";
		}
		catch (NullPointerException e) {
			return "id_non_trovato";
		}
	
	}
	
	@RequestMapping(value = "/id_non_trovato", method = RequestMethod.POST)
	public String idNonTrovato(@Validated User user, Model model) {

		
		return "id_non_trovato";
	}
	
	@RequestMapping(value = "/conferma_modifica_dati", method = RequestMethod.POST)
	public String confermaModificaDati(@Validated User user, Model model) {

		new UserService().modificaUtente(user);
		return "conferma_modifica_dati";
	}
	
	@RequestMapping(value = "/cancella_utente", method = RequestMethod.POST)
	public String cancellaUtente(@Validated User user, Model model) {

		return "cancella_utente";
	}

	@RequestMapping(value = "/conferma_cancellazione", method = RequestMethod.POST)
	public String confermaCancellazione(@Validated User user, Model model) {

		new UserService().archiviaUtente(user);
		return "conferma_cancellazione";
	}
	
}
