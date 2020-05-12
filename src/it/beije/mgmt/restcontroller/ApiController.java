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
		
		JSONArray timesheetTypes = new JSONArray();
		JSONObject timesheetType = new JSONObject();
		timesheetType.put("code", "W");
		timesheetType.put("label", "Lavoro");
		timesheetTypes.add(timesheetType);
		timesheetType = new JSONObject();
		timesheetType.put("code", "E");
		timesheetType.put("label", "Straordinari");
		timesheetTypes.add(timesheetType);
		timesheetType = new JSONObject();
		timesheetType.put("code", "H");
		timesheetType.put("label", "Ferie");
		timesheetTypes.add(timesheetType);
		timesheetType = new JSONObject();
		timesheetType.put("code", "P");
		timesheetType.put("label", "Permesso");
		timesheetTypes.add(timesheetType);
		timesheetType = new JSONObject();
		timesheetType.put("code", "S");
		timesheetType.put("label", "Permesso Studio");
		timesheetTypes.add(timesheetType);
		timesheetType = new JSONObject();
		timesheetType.put("code", "I");
		timesheetType.put("label", "Malattia");
		timesheetTypes.add(timesheetType);
		timesheetType = new JSONObject();
		timesheetType.put("code", "C");
		timesheetType.put("label", "Cassa Integrazione");
		timesheetTypes.add(timesheetType);


		JSONArray addressTypes = new JSONArray();
		JSONObject addressType = new JSONObject();
		addressType.put("code","R");
		addressType.put("label","Residenza");
		addressTypes.add(addressType);
		addressType = new JSONObject();
		addressType.put("code","D");
		addressType.put("label","Domicilio");
		addressTypes.add(addressType);

		JSONObject mainJson = new JSONObject();
		
		mainJson.putAll(objectServerDate);
		mainJson.put("address_types", addressTypes);
		mainJson.put("timesheet_types", timesheetTypes);
		mainJson.putAll(objectGmap);

		return mainJson;
	}
}
