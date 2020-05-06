package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
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
import it.beije.mgmt.repository.AddressRepository;
import it.beije.mgmt.service.AddressService;


@RestController
@RequestMapping("api")
public class AddressApiController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AddressRepository repository;
	
	@Autowired
	private AddressService addressService;
	

	/****************** ADDRESS  *****************/

	@RequestMapping(value = "/addresses/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Address> getAddressForUser(@PathVariable Long id) {
		log.debug("GET /addresses/user/{id}");
		List<Address> ordinad = repository.findAll(Sort.by(Sort.Direction.DESC, "Startdate"));
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
		log.debug("POST /addresses/user/{id}");
		
		try {
			return addressService.create(id, address);
		}catch(RuntimeException e) {
			throw e;
		}
	}

	// get bank credentials by idBankCredentials
	@RequestMapping(value = { "/address/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Address getAddress(@PathVariable Long id, Model model,
			HttpServletResponse response) throws IOException {
		log.debug("GET /addresses/{id}");
		
		try {
			return addressService.find(id);
		}catch(MasterException e) {
			throw e;
		}
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Address updateContract(@PathVariable Long id, @RequestBody Address address,
			Model model, HttpServletResponse response) throws IOException {
		log.debug("PUT /addresses/{id}");
		
		try {
			return addressService.update(id, address);
		}catch(MasterException e) {
			throw e;
		}
	}
	
	@RequestMapping(value = "/address/archive/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean archiveAddress(@PathVariable Long id, HttpServletResponse response) {
		log.debug("PUT /address/archive/{id}");		
		return addressService.archive(id);
	}
}


