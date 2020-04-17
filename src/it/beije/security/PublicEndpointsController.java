package it.beije.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.exception.MasterException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.List;

@RestController
@RequestMapping("api")
public class PublicEndpointsController {
    @Autowired
    private UserRegistrationService registrationService;
    @Autowired
    private UserAuthenticationService authenticationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        try {
            return registrationService
                    .register(username, password);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
   

    @PostMapping("/login")
    public Object login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        try {
            return authenticationService
                    .login(username, password);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(UNAUTHORIZED).body(e.getMessage());
        }
    }
}