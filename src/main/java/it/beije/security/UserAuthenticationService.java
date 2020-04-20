package it.beije.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import it.beije.mgmt.entity.User;


public interface UserAuthenticationService {
	
	String login(String username, String password) throws BadCredentialsException;

    User authenticateByToken(String token) throws AuthenticationException;

    void logout(String username);

}
