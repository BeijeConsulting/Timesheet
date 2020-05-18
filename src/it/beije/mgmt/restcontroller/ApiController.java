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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import it.beije.mgmt.exception.NoContentException;
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
	
	static void verifyLoggedUser(Authentication auth, Long id) {
		User user = (User) auth.getPrincipal();
		if(!user.getAuthority().contains("ADMIN") && user.getId()!=id)
			throw new NoContentException("Non si possiedono i permessi necessari");
	}
}
