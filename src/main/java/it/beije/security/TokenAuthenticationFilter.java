package it.beije.security;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	//Tutte le richieste da autorizzare dovranno essere processate da un apposito authentication filter:
	
	 protected TokenAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}
	 

	private static final String AUTHORIZATION = "Authorization";
	    private static final String BEARER = "Bearer";

	    @Override
	    public Authentication attemptAuthentication(
	            HttpServletRequest request,
	            HttpServletResponse response) {
	        String token = Optional.ofNullable(request.getHeader(AUTHORIZATION))
	                .map(v -> v.replace(BEARER, "").trim())
	                .orElseThrow(() -> new BadCredentialsException("Missing authentication token."));

	        Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
	        return getAuthenticationManager().authenticate(auth);
	    }

}
