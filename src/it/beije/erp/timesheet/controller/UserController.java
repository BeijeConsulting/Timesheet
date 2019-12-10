package it.beije.erp.timesheet.controller;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.beije.erp.dto.UserDto;
import it.beije.erp.entity.User;
import it.beije.erp.timesheet.service.UserService;

//import it.beije.jpa.GestioneUtenti;

@Controller
@SessionAttributes("user")
//@RequestMapping("/user")
public class UserController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String dataFormattato = dateFormat.format(date);

		model.addAttribute("serverTime", dataFormattato);

		return "home";
	}
	

	@RequestMapping(value = "/confirmdata", method = RequestMethod.POST)
	public String confermaDati(@Validated User user, String lastName, Model model) {
		model.addAttribute("user", user);
		return "confermadati";
	}

	@RequestMapping(value = "/insertuser", method = RequestMethod.GET)
	public String index(@Validated User user, Model model) {
		System.out.println("Pagine inseriti: " + user.getLastName());
		model.addAttribute("userName", user);

		System.out.println("sono in inserisciutente");

		return "inserisciutente";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/conferma", method = RequestMethod.POST)
	public String conferma(@Validated User user, Model model) {
		if (user.getDocument().length()==0)
			user.setDocument(null);
		if (user.getBirthDate().toLocalDate().isEqual(LocalDate.parse("1900-01-01")))
			user.setBirthDate(null);
		new UserService().create(user);
		
		System.out.println("sono in conferma " + user.getFirstName());
		return "conferma";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/modificautente", method = RequestMethod.GET)
	public String modificaUtente(@Validated User user, Model model) {

		return "modificautente";
	}


	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/cercautente", method = RequestMethod.GET)
	public String cercaUtente(@Validated User user, Model model) {
		
		return "cercautente";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/utentitrovati", method = RequestMethod.GET)
	public String utentiTrovati(@Validated User user, Model model,HttpServletRequest request) {
		List<User> trovati = new UserService().trovaUtenti(user.getFirstName(),user.getLastName(),user.getEmail(),user.getFiscalCode());
		request.getSession().setAttribute("users", trovati);
		return "utentitrovati";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/visualizzautente", method = RequestMethod.POST)
	public String visualizzaUtente(@RequestParam("userid") Long userid, Model model){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("timesheet");
		EntityManager em=factory.createEntityManager();
		
		User u=em.find(User.class, userid);
		
		model.addAttribute("utente",u);
		
		return "data";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/modificadati", method = RequestMethod.POST)
	public String modificaDati(@Validated User user, Model model) {
		
		try {
		
		user = new UserService().find(user.getId());
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

		new UserService().modificaUtente(user);
		return "conferma";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/cancellautente", method = RequestMethod.POST)
	public String cancellaUtente(@Validated User user, Model model) {

		return "cancellautente";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/confermacancellazione", method = RequestMethod.POST)
	public String confermaCancellazione(@Validated User user, Model model) {

		new UserService().archiviaUtente(user);
		return "confermacancellazione";
	}
	
	
	///////////////NEW METHOD
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
	
	@RequestMapping(value = {"/importdocuments"}, method = RequestMethod.GET)
	public String importDocuments(@Validated User user, Model model) {

		return "importdocuments";
	}
}
