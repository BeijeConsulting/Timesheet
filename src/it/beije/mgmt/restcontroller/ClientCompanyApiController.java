package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.ClientCompany;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.service.ClientCompanyService;

@RestController
@RequestMapping("api")
public class ClientCompanyApiController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClientCompanyService clientService;
	
	@Transactional
	@RequestMapping(value = "/clientcompanies", method = RequestMethod.GET)
	public @ResponseBody List<ClientCompany> getClientCompanies(Model model, HttpServletResponse response){
		log.debug("GET /clientcompanies");
		
		try{
			return clientService.findAll();
		}catch(MasterException e) {
			throw e;
		}
	}
	
	@Transactional
	@RequestMapping(value = { "/clientcompany/{id}" }, method = RequestMethod.GET)
	public @ResponseBody ClientCompany getClientCompany(@PathVariable Long id, Model model,
			HttpServletResponse response) {
		log.debug("GET /clientcompanies/{id}");
		
		try {
			return clientService.find(id);
		}catch(MasterException e) {
			throw e;
		}
	}
	
	@Transactional
	@RequestMapping(value = "/clientcompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ClientCompany insertClientCompany(@RequestBody ClientCompany client, HttpServletResponse response) throws IOException {
		log.debug("POST /clientcompany");
		try {
			return clientService.create(client);
		}catch(MasterException e) {
			throw e;
		}
	}
	
	@Transactional
	@RequestMapping(value = "/clientcompany/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ClientCompany updateClientCompany(@PathVariable Long id, @RequestBody ClientCompany client,
			Model model, HttpServletResponse response) throws IOException {
		log.debug("PUT /clientcompany/{id}");

		try {
			return clientService.update(id, client);
		}catch(MasterException e) {
			throw e;
		}
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/clientcompanies/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ClientCompany> getClientsForUser(@PathVariable Long id, Authentication auth) {
		log.debug("GET /clientcompanies/user/{id}");
		
		try {
			verifyLoggedUser(auth, id);	
			return clientService.getClientsByUser(id);
		}catch(MasterException e) {
			throw e;
		}
	}
}
