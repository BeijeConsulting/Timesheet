package it.beije.mgmt.restcontroller;

import static org.springframework.http.ResponseEntity.ok;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;

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
@PreAuthorize("hasAuthority('ADMIN')")
public class BaseController {
	
	protected void verifyLoggedUser(Authentication auth, Long id) {
		User user = (User) auth.getPrincipal();
		if(!user.getAuthority().contains("ADMIN") && user.getId()!=id)
			throw new NoContentException("Non si possiedono i permessi necessari");
	}
}
