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
import it.beije.mgmt.entity.User;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.restcontroller.exception.InvalidJSONException;
import it.beije.mgmt.restcontroller.exception.NoContentException;
import it.beije.mgmt.service.AddressService;
import it.beije.mgmt.service.UserService;


@RestController
@RequestMapping("api")
public class AddressApiController {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserService userService;

	/****************** ADDRESS  *****************/
	@Transactional
	@RequestMapping(value = "/addresses/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Address> getAddressForUser(@PathVariable Long id) {
		User us = userService.find(id);
		if(us.isEmpty()) 
			throw new NoContentException("Non è stato trovato un utente con l'id selezionato");
		return addressService.getAddressByUser(id);
	}

	// write new bank credentials by idUser
	@RequestMapping(value = "/address/user/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Address createAddress(@PathVariable Long id,
			@RequestBody Address address, HttpServletResponse response) throws Exception {
		
		System.out.println("insert Address: " + address);

		User us = userService.find(id);
		if(us.isEmpty()) 
			throw new NoContentException("Non è stato trovato un utente con l'id selezionato");
		Address ad = new Address();
		try {
			ad = addressService.create(id, address);
		}catch(RuntimeException e) {
			throw new InvalidJSONException("Non è stato possibile aggiungere l'indirizzo desiderato");
		}
		return ad;
	}

	// get bank credentials by idBankCredentials
	@RequestMapping(value = { "/address/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Address getAddress(@PathVariable Long id, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("get address by idAddress: " + id);
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		Address address = entitymanager.find(Address.class, id);
		if(address.getId()==null) 
			throw new NoContentException("Non è stato trovato un indirizzo con l'id selezionato");
		return address;
	}

	// update existing bank credentials
	@RequestMapping(value = "/address/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Address updateContract(@PathVariable Long id, @RequestBody Address address,
			Model model, HttpServletResponse response) throws IOException {
		System.out.println("update address by id: " + id);
		System.out.println("update address: " + address);
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		Address ad = entitymanager.find(Address.class, id);
		if(ad.getId()==null) 
			throw new NoContentException("Non è stato trovato un indirizzo con l'id selezionato");
		try {
			ad = addressService.update(id, address);
		}catch(RuntimeException e) {
			throw new InvalidJSONException("Non è stato possibile modificare i dati dell'indirizzo desiderato");
		}
		return ad;
	}

}


