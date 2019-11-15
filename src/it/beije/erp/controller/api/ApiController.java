package it.beije.erp.controller.api;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import it.beije.erp.entity.Address;
import it.beije.erp.entity.BankCredentials;
import it.beije.erp.entity.Contract;
import it.beije.erp.entity.User;
import it.beije.erp.service.JPAService;
import it.beije.erp.timesheet.entity.Timetable;
import it.beije.jpa.JpaEntityManager;


@RestController
@RequestMapping("api")
public class ApiController {

	//	@Autowired
	//	private UserService userService;

	//	@Autowired
	//	private TimetableService timetableService;

	///////// TEST //////////////////////
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	private @ResponseBody User test(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);

		return new User();
	}

	@RequestMapping(value = "/testTT", method = RequestMethod.GET)
	public @ResponseBody Timetable testTT(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);

		return new Timetable();
	}
	//////////////////////////////////////


	/****************** ADDRESS *****************/

	/* 
	 * INDIRIZZI : 
	 * -CONTROLLER : 
	 * 		-Modifica
	 * 		-Tutte le view
	 *	-API : 
	 *		-Modifica
	 *
	 *
	 *	CREDENZIALI BANCARIE : 
	 *	-CONTROLLER : 
	 *		-Tutto
	 *	-API
	 *		-Modifica
	 *
	 *
	 *	CONTRATTI : 
	 *	-CONTROLLER : 
	 *		-Tutto
	 	-API : 
	 		-Tutto
	 * 
	 */

	//Read
	@RequestMapping(value = "/addresses/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Address> getAddresses(@PathVariable Long id) {

		User user = JPAService.getBean(User.class, id);
		List<Address> addresses = user.getAddresses();

		return addresses;
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
	public @ResponseBody Address getAddress(@PathVariable Long id) {

		Address address = JPAService.getBean(Address.class, id);

		return address;
	}

	//Write
	@RequestMapping(value = "/address", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Address addressPost(@RequestBody Address address) {
		JPAService.save(address);
		return address;
	}

	//	//Update
	//	@RequestMapping(value = "/address/{id}", method = RequestMethod.PUT,
	//			consumes = MediaType.APPLICATION_JSON_VALUE)
	//	public @ResponseBody Address addressPut(@RequestBody Address address, @PathVariable int id) {
	//		System.out.println("\n\n\naddressput...\n\n\n");
	//		address.setId(id);
	//		System.out.println("\n\n\nId...\n\n\n" + id);
	//		JPAService.save(address);
	//		return address;
	//	}
	//	

	@RequestMapping(value = "/address/{id}", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Address addressPut(@RequestBody Address addressData, @PathVariable Long id) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Address address = entitymanager.find(Address.class, id);

		if(addressData.getStreet() != null)address.setStreet(addressData.getStreet());
		if(addressData.getCity() != null)address.setCity(addressData.getCity());
		if(addressData.getProvince() != null)address.setProvince(addressData.getProvince());
		if(addressData.getCap() != null)address.setCap(addressData.getCap());
		if(addressData.getCountry() != null)address.setCountry(addressData.getCountry());
		if(addressData.getStartDate() != null)address.setStartDate(addressData.getStartDate());
		if(addressData.getEndDate() != null)address.setEndDate(addressData.getEndDate());

		entitymanager.persist(address);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		
		return address;

	}


	/****************** BANK CREDENTIALS *****************/
	//Read
	@RequestMapping(value = "/credentials/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<BankCredentials> getCredentialsForUser(@PathVariable Long id) {

		User user = JPAService.getBean(User.class, id);
		List<BankCredentials> credentials = user.getBankCredentials();

		return credentials;
	}

	@RequestMapping(value = "/credentials/{id}", method = RequestMethod.GET)
	public @ResponseBody BankCredentials getCredentials(@PathVariable Long id) {

		BankCredentials credentials = JPAService.getBean(BankCredentials.class, id);

		return credentials;
	}

	//Write
	@RequestMapping(value = "/credentials", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BankCredentials credentialsPost(@RequestBody BankCredentials credentials) {
		JPAService.save(credentials);
		return credentials;
	}



	/****************** CONTRACT *****************/
	//Read
	@RequestMapping(value = "/contract/user/{id}", method = RequestMethod.GET)

	public @ResponseBody List<Contract> getContracts(@PathVariable Long id) {		
		User user = JPAService.getBean(User.class, id);
		List<Contract> contracts = user.getContracts();
		return contracts;
	}

	@RequestMapping(value = "/contract/{id}", method = RequestMethod.GET)
	public @ResponseBody Contract getContract(@PathVariable Long id) {
		Contract contract = JPAService.getBean(Contract.class, id);
		return contract;
	}

	//Write
	@RequestMapping(value = "/contract", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Contract addressPost(@RequestBody Contract contract) {
		JPAService.save(contract);
		return contract;
	}


	///////// START USER //////////////////////
	//	@RequestMapping(value = "/users", method = RequestMethod.GET)
	//	public @ResponseBody List<User> getUsers(Model model, HttpServletResponse response) throws IOException {
	//		return userService.caricaTutti();
	//	}
	//
	//	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	//	public @ResponseBody User getUser(@PathVariable int id, Model model,
	//			HttpServletResponse response) throws IOException {
	//		System.out.println("get user by id: " + id);
	//
	//		return userService.find(id);
	//	}
	//
	//	@RequestMapping(value = "/user", method = RequestMethod.POST,
	//			consumes = MediaType.APPLICATION_JSON_VALUE)
	//	public @ResponseBody User insertUser(@RequestBody User user, Model model,
	//			HttpServletResponse response) throws IOException {
	//		System.out.println("insert user: " + user);
	//
	//		return userService.create(user);
	//	}
	//
	//	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT,
	//			consumes = MediaType.APPLICATION_JSON_VALUE)
	//	public @ResponseBody User updateUser(@PathVariable int id, @RequestBody User user, Model model,
	//			HttpServletResponse response) throws IOException {
	//		System.out.println("update user by id: " + id);
	//		System.out.println("update user: " + user);
	//
	//		return userService.update(id, user);
	//	}
	///////// END USER //////////////////////

	///////// START TIMESHEET //////////////////////
	//	@RequestMapping(value = "/timesheets", method = RequestMethod.GET)
	//	public @ResponseBody List<Timetable> getTimesheets(Model model, HttpServletResponse response) throws IOException {
	//		return timetableService.caricaTutto();
	//	}
	//
	//	@RequestMapping(value = "/timesheets", method = RequestMethod.POST,
	//			consumes = MediaType.APPLICATION_JSON_VALUE)
	//	public @ResponseBody List<Timetable> insertTimesheets(@RequestBody List<Timetable> timesheets, Model model,
	//			HttpServletResponse response) throws IOException {
	//		System.out.println("insert timesheets: " + timesheets);
	//
	//		return timetableService.insert(timesheets);
	//	}
	//
	//	@RequestMapping(value = "/timesheets/user/{id}", method = RequestMethod.GET)
	//	public @ResponseBody Map<String, Object> retrieveTimeSheetTables(@PathVariable int id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) {
	//		Map<String, Object> result = new HashMap<String, Object>();
	//		dateto = dateto == null? new Date(System.currentTimeMillis()):dateto;
	//		List<Timetable> timetablelist = timetableService.retrieveTimatablesInDateRangeByUserId(id,datefrom,dateto);
	//		List<Object> timesheets = new ArrayList<Object>();
	//		Map<String, List> byDate = new HashMap<String, List>();		
	//		
	//		timetablelist.forEach(timetable-> {
	//			Map<String, Object> ts = new HashMap<String, Object>();
	//			String currentDate = timetable.getDate();
	//			ts.put("id", timetable.getId());
	//			ts.put("type", timetable.getType());
	//			ts.put("tot", timetable.getTot());
	//			ts.put("start1", timetable.getStart1());
	//			ts.put("end1", timetable.getEnd1());
	//			if(timetable.getStart2() != null && timetable.getEnd2() != null) {
	//				ts.put("start2", timetable.getStart2());
	//				ts.put("end2", timetable.getEnd2());				
	//			}
	//			if(!byDate.containsKey(currentDate))
	//				byDate.put(currentDate, new ArrayList<Map>());
	//			byDate.get(currentDate).add(ts);			
	//		});	
	//		
	//		byDate.forEach((date,list) -> {
	//			Map<String, Object> timesheet = new HashMap<String, Object>();
	//			timesheet.put("date", date);
	//			timesheet.put("ts", list);
	//			timesheets.add(timesheet);
	//		});
	//		result.put("user", id);
	//		result.put("timesheets", timesheets);
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/timesheets/user/byId", method = RequestMethod.GET)
	//	public @ResponseBody Map<String, Object> mockedTimeSheetList() {
	//
	//		Map<String, Object> result = mockedTimeTables();
	//
	//		return result;
	//	}
	///////// END TIMESHEET //////////////////////

	//	private Map<String, Object> mockedTimeTables() {
	//		Map<String, Object> el = new HashMap<String, Object>();		
	//		el.put("id",1);
	//		el.put("type","work");
	//		el.put("start1","09:00");
	//		el.put("end1","13:00");
	//		el.put("start2","14:00");
	//		el.put("end2","18:00");
	//		el.put("tot",8);
	//
	//		List<Map<String, Object>> ts = new ArrayList<Map<String, Object>>();		
	//		ts.add(el);
	//
	//		Map<String, Object> timesheet = new HashMap<String, Object>();
	//		timesheet.put("date", new Date(2019-1900, 9, 29).toString());
	//		timesheet.put("ts", ts);
	//
	//		List<Map<String, Object>> timesheets = new ArrayList<Map<String, Object>>();
	//		timesheets.add(timesheet);
	//
	//		Map<String, Object> result = new HashMap<String, Object>();
	//		result.put("user", new Integer(1));
	//		result.put("timesheets", timesheets);
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/testJsonTT", method = RequestMethod.POST,
	//			consumes = MediaType.APPLICATION_JSON_VALUE)
	//	public void handleJsonPostRequest(@RequestBody Timetable timetable, Model model,
	//			HttpServletResponse response) throws IOException {
	//		System.out.println("timetable : "+timetable);
	//
	//		ObjectMapper objectMapper = new ObjectMapper();
	//		response.setStatus(200);//STATO RISPOSTA
	//		response.setContentType("application/json");//TIPO RISPOSTA
	//		response.getWriter().append(objectMapper.writeValueAsString(timetable));//CORPO RISPOSTA
	//	}

}
