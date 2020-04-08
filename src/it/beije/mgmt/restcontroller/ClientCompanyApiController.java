package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.ClientCompany;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.service.ClientCompanyService;


@RestController
@RequestMapping("api")
public class ClientCompanyApiController {

	@Autowired
	private ClientCompanyService clientService;
	
	@Transactional
	@RequestMapping(value = "/clientcompanies", method = RequestMethod.GET)
	public @ResponseBody List<ClientCompany> getClientCompanies(Model model, HttpServletResponse response){
		
		try{
			return clientService.caricaTutti();
		}catch(MasterException e) {
			throw e;
		}
	}
	
	@Transactional
	@RequestMapping(value = { "/clientcompany/{id}" }, method = RequestMethod.GET)
	public @ResponseBody ClientCompany getClientCompany(@PathVariable Long id, Model model,
			HttpServletResponse response) {
		
		try {
			return clientService.find(id);
		}catch(MasterException e) {
			throw e;
		}
	}
	
	@Transactional
	@RequestMapping(value = "/clientcompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ClientCompany insertClientCompany(@RequestBody ClientCompany client, HttpServletResponse response) throws IOException {

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

		try {
			return clientService.update(id, client);
		}catch(MasterException e) {
			throw e;
		}
	}
	
	@Transactional
	@RequestMapping(value = "/clientcompanies/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ClientCompany> getClientsForUser(@PathVariable Long id) {
		
		try {
			return clientService.getClientsByUser(id);
		}catch(MasterException e) {
			throw e;
		}
	}
}
