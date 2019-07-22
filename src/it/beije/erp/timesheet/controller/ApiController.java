package it.beije.erp.timesheet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.beije.erp.timesheet.entity.Timetable;
import it.beije.erp.timesheet.entity.User;
import it.beije.erp.timesheet.service.UserService;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	private UserService userService;

	///////// TEST //////////////////////
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody User test(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		
		return new User();
	}
	
	@RequestMapping(value = "/testTT", method = RequestMethod.GET)
	public @ResponseBody Timetable testTT(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		
		return new Timetable();
	}
	//////////////////////////////////////
	
	///////// START USERS //////////////////////
	@RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers(Model model, HttpServletResponse response) throws IOException {
    	return userService.caricaTutti();
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable int id, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("get user by id: " + id);
    	
    	return userService.find(id);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User insertUser(@RequestBody User user, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("insert user: " + user);
    	
    	return userService.create(user);
	}
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User updateUser(@PathVariable int id, @RequestBody User user, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("update user by id: " + id);
    	System.out.println("update user: " + user);
    	
    	return userService.update(id, user);
	}
	///////// END USERS //////////////////////

	
	@RequestMapping(value = "/testJsonTT", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void handleJsonPostRequest(@RequestBody Timetable timetable, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("timetable : "+timetable);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	response.setStatus(200);//STATO RISPOSTA
    	response.setContentType("application/json");//TIPO RISPOSTA
    	response.getWriter().append(objectMapper.writeValueAsString(timetable));//CORPO RISPOSTA
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
		
		user = new UserService().find(user.getId());
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
	
	///////////////NEW METHOD
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage(@Validated User user, Model model) {

		new UserService().archiviaUtente(user);
		return "homepage";
	}

}
