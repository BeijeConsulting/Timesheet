package it.beije.security;

import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.TokenVerificationException;
import it.beije.mgmt.service.UserService;

public class JWTAuthenticationService implements UserAuthenticationService {
	
	//INTERFACCIA BASATA SU JWT 
	
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

    @Override
    public String login(String username, String password) throws BadCredentialsException {
        return userService
                .getByUsername(username)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> jwtService.create(username))
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password."));
    }

    @Override
    public User authenticateByToken(String token) {
        try {
            Object username = jwtService.verify(token).get("username");
            return Optional.ofNullable(username)
                    .flatMap(name -> userService.getByUsername(String.valueOf(name)))
                    .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found."));
        } catch (TokenVerificationException e) {
            throw new BadCredentialsException("Invalid JWT token.", e);
        }
    }

    @Override
    public void logout(String username) {
        // ...
    }
}

