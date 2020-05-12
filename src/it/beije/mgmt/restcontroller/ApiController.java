package it.beije.mgmt.restcontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;


@RestController
@RequestMapping("api")
public class ApiController {


	///////// TEST //////////////////////
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	private @ResponseBody User test(Locale locale, Model model) {
		
		System.out.println("Home Page Requested, locale = " + locale);

		return new User();
	}

	@RequestMapping(value = "/testTT", method = RequestMethod.GET)
	public @ResponseBody Timesheet testTT(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);

		return new Timesheet();
	}
	
	@RequestMapping(value = "/constants", method = RequestMethod.GET)
	public @ResponseBody JSONObject costant(Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm Z");
		Date date = new Date();

		JSONObject objectServerDate = new JSONObject();
		objectServerDate.put("server_date",dateFormat.format(date));
		
		JSONObject objectGmap = new JSONObject();
		objectGmap.put("gmap_key","XYZ");
		
		JSONObject objectTT1 = new JSONObject();
		JSONObject objectTT2 = new JSONObject();
		JSONObject objectTT3 = new JSONObject();
		JSONObject objectTT4 = new JSONObject();
		JSONObject objectTT5 = new JSONObject();
		JSONObject objectTT6 = new JSONObject();
		JSONObject objectTT7 = new JSONObject();
		
		JSONArray arrayTimesheetTypes = new JSONArray();
		JSONArray arrayTimesheetTypes1 = new JSONArray();
		objectTT1.put("code", "W");
		objectTT1.put("label", "Lavorativo");
		objectTT2.put("code", "H");
		objectTT2.put("label", "ferie");
		objectTT3.put("code", "P");
		objectTT3.put("label", "Permesso");
		objectTT4.put("code", "S");
		objectTT4.put("label", "Malattia");
		objectTT5.put("code", "C");
		objectTT5.put("label", "Cassa Integrazione");
		objectTT6.put("code","R");
		objectTT6.put("label","Residenza");
		objectTT7.put("code","D");
		objectTT7.put("label","Domicilio");
		
		
		arrayTimesheetTypes.add(objectTT1);
		arrayTimesheetTypes.add(objectTT2);
		arrayTimesheetTypes.add(objectTT3);
		arrayTimesheetTypes.add(objectTT4);
		arrayTimesheetTypes.add(objectTT5);
		arrayTimesheetTypes1.add(objectTT6);
		arrayTimesheetTypes1.add(objectTT7);
		
		JSONObject mainJson = new JSONObject();
		
		mainJson.putAll(objectServerDate);
		mainJson.put("address_types", arrayTimesheetTypes1);
		mainJson.put("timesheet_types", arrayTimesheetTypes);
		mainJson.putAll(objectGmap);

		return mainJson;
	}
}
