package it.beije.mgmt.restcontroller;

import static org.springframework.http.ResponseEntity.ok;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.dto.AuthenticationRequest;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.repository.UserRepository;
import it.beije.mgmt.security.JwtTokenFilter;
import it.beije.mgmt.security.JwtTokenProvider;


@RestController
@RequestMapping("api")
public class ApiController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

	///////// TEST //////////////////////
	
	@PostMapping("/signin")
    public ResponseEntity<Map<Object, Object>> signin(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getAuthority());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        } catch (MasterException e) {
            throw e;
        }
    }
	
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
		
		JSONArray arrayTimesheetTypes = new JSONArray();
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
		
		arrayTimesheetTypes.add(objectTT1);
		arrayTimesheetTypes.add(objectTT2);
		arrayTimesheetTypes.add(objectTT3);
		arrayTimesheetTypes.add(objectTT4);
		arrayTimesheetTypes.add(objectTT5);
		
		JSONObject mainJson = new JSONObject();
		mainJson.putAll(objectServerDate);
		mainJson.put("timesheet_types", arrayTimesheetTypes);
		mainJson.putAll(objectGmap);

		return mainJson;
	}
}
