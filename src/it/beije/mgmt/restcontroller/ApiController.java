package it.beije.mgmt.restcontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

		JSONObject objectJson = new JSONObject();
		objectJson.put("server_date",dateFormat.format(date));
		
		JSONObject objectJson1 = new JSONObject();
		JSONObject objectJson2 = new JSONObject();
		JSONObject objectJson3 = new JSONObject();
		JSONObject objectJson4 = new JSONObject();
		JSONObject objectJson5 = new JSONObject();
		
		JSONArray arrayJson = new JSONArray();
		objectJson1.put("code", "W");
		objectJson1.put("label", "Lavorativo");
		objectJson2.put("code", "H");
		objectJson2.put("label", "ferie");
		objectJson3.put("code", "P");
		objectJson3.put("label", "Permesso");
		objectJson4.put("code", "S");
		objectJson4.put("label", "Malattia");
		objectJson5.put("code", "C");
		objectJson5.put("label", "Cassa Integrazione");
		
		arrayJson.add(objectJson1);
		arrayJson.add(objectJson2);
		arrayJson.add(objectJson3);
		arrayJson.add(objectJson4);
		arrayJson.add(objectJson5);
		JSONObject mainJson = new JSONObject();
		mainJson.putAll(objectJson);
		mainJson.put("timesheet_types", arrayJson);

		return mainJson;
	}

}
