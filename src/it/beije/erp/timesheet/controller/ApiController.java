package it.beije.erp.timesheet.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.beije.erp.entity.User;
import it.beije.erp.timesheet.entity.Timetable;
import it.beije.erp.timesheet.service.TimetableService;
import it.beije.erp.timesheet.service.UserService;


@Controller
@RequestMapping("api")
public class ApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private TimetableService timetableService;

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

	///////// START USER //////////////////////
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers(Model model, HttpServletResponse response) throws IOException {
		return userService.caricaTutti();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable int id, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("get user by id: " + id);

		return userService.find(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User insertUser(@RequestBody User user, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("insert user: " + user);

		return userService.create(user);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUser(@PathVariable int id, @RequestBody User user, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("update user by id: " + id);
		System.out.println("update user: " + user);

		return userService.update(id, user);
	}
	///////// END USER //////////////////////

	///////// START TIMESHEET //////////////////////
	@RequestMapping(value = "/timesheets", method = RequestMethod.GET)
	public @ResponseBody List<Timetable> getTimesheets(Model model, HttpServletResponse response) throws IOException {
		return timetableService.caricaTutto();
	}

	@RequestMapping(value = "/timesheets", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Timetable> insertTimesheets(@RequestBody List<Timetable> timesheets, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("insert timesheets: " + timesheets);

		return timetableService.insert(timesheets);
	}

	@RequestMapping(value = "/timesheets/user/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> retrieveTimeSheetTables(@PathVariable int id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) {
		Map<String, Object> result = new HashMap<String, Object>();
		dateto = dateto == null? new Date(System.currentTimeMillis()):dateto;
		List<Timetable> timetablelist = timetableService.retrieveTimatablesInDateRangeByUserId(id,datefrom,dateto);
		List<Object> timesheets = new ArrayList<Object>();
		Map<String, List> byDate = new HashMap<String, List>();		
		
		timetablelist.forEach(timetable-> {
			Map<String, Object> ts = new HashMap<String, Object>();
			String currentDate = timetable.getDate();
			ts.put("id", timetable.getId());
			ts.put("type", timetable.getType());
			ts.put("tot", timetable.getTot());
			ts.put("start1", timetable.getStart1());
			ts.put("end1", timetable.getEnd1());
			if(timetable.getStart2() != null && timetable.getEnd2() != null) {
				ts.put("start2", timetable.getStart2());
				ts.put("end2", timetable.getEnd2());				
			}
			if(!byDate.containsKey(currentDate))
				byDate.put(currentDate, new ArrayList<Map>());
			byDate.get(currentDate).add(ts);			
		});	
		
		byDate.forEach((date,list) -> {
			Map<String, Object> timesheet = new HashMap<String, Object>();
			timesheet.put("date", date);
			timesheet.put("ts", list);
			timesheets.add(timesheet);
		});
		result.put("user", id);
		result.put("timesheets", timesheets);

		return result;
	}

	@RequestMapping(value = "/timesheets/user/byId", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> mockedTimeSheetList() {

		Map<String, Object> result = mockedTimeTables();

		return result;
	}
	///////// END TIMESHEET //////////////////////

	private Map<String, Object> mockedTimeTables() {
		Map<String, Object> el = new HashMap<String, Object>();		
		el.put("id",1);
		el.put("type","work");
		el.put("start1","09:00");
		el.put("end1","13:00");
		el.put("start2","14:00");
		el.put("end2","18:00");
		el.put("tot",8);

		List<Map<String, Object>> ts = new ArrayList<Map<String, Object>>();		
		ts.add(el);

		Map<String, Object> timesheet = new HashMap<String, Object>();
		timesheet.put("date", new Date(2019-1900, 9, 29).toString());
		timesheet.put("ts", ts);

		List<Map<String, Object>> timesheets = new ArrayList<Map<String, Object>>();
		timesheets.add(timesheet);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("user", new Integer(1));
		result.put("timesheets", timesheets);
		return result;
	}

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
		user.setSecondaryEmail(trovati);
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
