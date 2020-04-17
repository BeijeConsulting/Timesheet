package it.beije.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import it.beije.mgmt.CustomUserDetail;
import it.beije.mgmt.entity.Token;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.repository.TokenRepository;
import it.beije.mgmt.repository.UserRepository;

public class TokenAuthenticationProvider  extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
    private UserAuthenticationService userAuthenticationService;
	
	@Autowired
    private TokenRepository tokenRepository;
	
	@Autowired
    private UserRepository userRepository;

    @Override
    protected CustomUserDetail retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
       
    	Object token = authentication.getCredentials();
    	
    	Token prova = tokenRepository.findByToken(token);

    	Long idutente= prova.getIdUser();
    	
    	Optional<User> utente = userRepository.findById(idutente);
    	
    	if(!utente.isPresent()) {
    		
    		throw new BadCredentialsException("Invalid authentication token=" + token);
    	}
    	
    	User user= new User();
    	
    	user.setPassword(utente.get().getPassword());
    	
    	CustomUserDetail userDetails= new 	CustomUserDetail(user);
    	return userDetails;
        		               
    }

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}
}
