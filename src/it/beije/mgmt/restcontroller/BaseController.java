package it.beije.mgmt.restcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.NoContentException;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class BaseController {
	
	protected void verifyLoggedUser(Authentication auth, Long id) {
		User user = (User) auth.getPrincipal();
		if(!user.getAuthority().contains("ADMIN") && user.getId()!=id)
			throw new NoContentException("Non si possiedono i permessi necessari");
	}
}
