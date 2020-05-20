package it.beije.mgmt.controller;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.beije.mgmt.entity.User;
import it.beije.mgmt.service.UserService;

//import it.beije.jpa.GestioneUtenti;

@Controller
@SessionAttributes("user")
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	//ENTRY POINT SEZIONE USER VIA USER NORMALE ----------------------------------------------------------!!!!!!!!!!!-------------------------
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Home Page Requested, locale = " + locale);
		//System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String dataFormattato = dateFormat.format(date);

		model.addAttribute("serverTime", dataFormattato);

		return "home";
	}
	
//	INSERT UTENTE____________________________________________________________________________
	
	@RequestMapping(value = "/insertuser", method = RequestMethod.GET)
	public String index(@Validated User user, Model model) {
		log.info("Pagine inseriti: " + user.getLastName());
		//System.out.println("Pagine inseriti: " + user.getLastName());
		model.addAttribute("userName", user);

		log.debug("sono in inserisci utente");
		//System.out.println("sono in inserisci utente");

		return "inserisciutente";
	}

	@RequestMapping(value = "/confirmdata", method = RequestMethod.POST)
	public String confermaDati(@Validated User user, String lastName, Model model) {
		model.addAttribute("user", user);
		return "confermadati";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/conferma", method = RequestMethod.POST)
	public String conferma(@Validated User user, Model model) {
		if (user.getDocument().length()==0)
			user.setDocument(null);
		if (user.getBirthDate().toLocalDate().isEqual(LocalDate.parse("1900-01-01")))
			user.setBirthDate(null);
		userService.create(user);
		
		log.debug("sono in conferma " + user.getFirstName());
		//System.out.println("sono in conferma " + user.getFirstName());
		return "conferma";
	}
	
	
	
//	MODIFICA UTENTE___________________________________________________________________________
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/modificautente", method = RequestMethod.GET)
	public String modificaUtente(@Validated User user, Model model) {

		return "modificautente";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/modificadati", method = RequestMethod.POST)
	public String modificaDati(@Validated User user, Model model) {
		
		try {
		
		user = userService.find(user.getId(), false);
		user.getFirstName();
		model.addAttribute("user", user);
			return "modificadati";
		}
		catch (NullPointerException e) {
			return "idnontrovato";
		}
	
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/idnontrovato", method = RequestMethod.POST)
	public String idNonTrovato(@Validated User user, Model model) {

		
		return "idnontrovato";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/confermamodificadati", method = RequestMethod.POST)
	public String confermaModificaDati(@Validated User user, Model model) {

		userService.update(user);
		return "conferma";
	}


//	CERCA E VISUALIZZA UTENTE___________________________________________________________________________	
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/cercautente", method = RequestMethod.GET)
	public String cercaUtente(@Validated User user, Model model) {
		
		return "cercautente";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/utentitrovati", method = RequestMethod.GET)
	public String utentiTrovati(@Validated User user, Model model,HttpServletRequest request) {
//		List<User> trovati = userService.trovaUtenti(user.getFirstName(),user.getLastName(),user.getEmail(),user.getFiscalCode());
//		request.getSession().setAttribute("users", trovati);
		return "utentitrovati";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/visualizzautente", method = RequestMethod.POST)
	public String visualizzaUtente(@RequestParam("userid") Long userid, Model model){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("timesheetDB");
		EntityManager em=factory.createEntityManager();
		
		User u=em.find(User.class, userid);
		
		model.addAttribute("utente",u);
		
		return "data";
	}

	
//	CANCELLA UTENTE___________________________________________________________________________		
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/cancellautente", method = RequestMethod.POST)
	public String cancellaUtente(@Validated User user, Model model) {
		
		return "cancellautente";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/confermacancellazione", method = RequestMethod.POST)
	public String confermaCancellazione(@Validated User user, Model model) {

		userService.dismissUser(user);
		return "confermacancellazione";
	}
	
//	ENTRY POINT SEZIONE IMPORT DOCUMENTS___________________________________________________________________________		
	
	@RequestMapping(value = {"/importdocuments"}, method = RequestMethod.GET)
	public String importDocuments(@Validated User user, Model model) {

		return "importdocuments";
	}
	
	
	///////////////NEW METHOD
	
	//ENTRY POINT SEZIONE USER VIA ADMIN -------------------------------------------------------------------------------------!!!!!!!!-------------
	@PreAuthorize("hasAnyRole('ADMIN')")	
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage(@Validated User user, Model model) {
		return "homepage";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@Validated User user, Model model) {

		return "login";
		
	
	}
	
	@RequestMapping(value = {"/temporanea","/"}, method = RequestMethod.GET)
	public String temporanea(@Validated User user, Model model) {

		return "temporanea";
	}
	

}
