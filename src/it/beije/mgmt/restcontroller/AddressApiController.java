package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.service.AddressService;


@RestController
@RequestMapping("api")
public class AddressApiController {
	
	@Autowired
	private AddressService addressService;
	

	/****************** ADDRESS  *****************/

	@RequestMapping(value = "/addresses/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Address> getAddressForUser(@PathVariable Long id) {
		
		try {
			return addressService.getAddressByUser(id);
		}catch(MasterException e) {
			throw e;
		}
	}

	// write new bank credentials by idUser
	@RequestMapping(value = "/address/user/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Address createAddress(@PathVariable Long id,
			@RequestBody Address address, HttpServletResponse response) throws Exception {
		
		try {
			return addressService.create(id, address);
		}catch(RuntimeException e) {
			throw new InvalidJSONException("Non � stato possibile aggiungere l'indirizzo desiderato");
		}
	}

	// get bank credentials by idBankCredentials
	@RequestMapping(value = { "/address/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Address getAddress(@PathVariable Long id, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("get address by idAddress: " + id);
		
//		EntityManager entitymanager = null;
		try {
			return addressService.find(id);
		}catch(MasterException e) {
			throw e;
		}
	}
//			entitymanager = JpaEntityManager.getInstance().createEntityManager();
//			Address address = entitymanager.find(Address.class, id);
//			if(address.getId()==null) 
//				throw new NoContentException("Non � stato trovato un indirizzo con l'id selezionato");
//		return address;
//	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Address updateContract(@PathVariable Long id, @RequestBody Address address,
			Model model, HttpServletResponse response) throws IOException {
		
		try {
			return addressService.update(id, address);
		}catch(MasterException e) {
			throw e;
		}
	}

}


