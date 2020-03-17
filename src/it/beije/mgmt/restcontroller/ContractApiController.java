package it.beije.mgmt.restcontroller;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.dto.UserDto;
import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.service.ContractService;
import it.beije.mgmt.service.JPAService;

@RestController
@RequestMapping("api")

public class ContractApiController {
	@Autowired
	private ContractService contractService;

	/****************** BANK CREDENTIALS????????????????????? *****************/
	// storico banck credentials user
	@Transactional
	@RequestMapping(value = "/contracts/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Contract> getContractForUser(@PathVariable Long id) {
		return contractService.getContractByUser(id);
	}

	// write new bank credentials by idUser
	@RequestMapping(value = "/contract/user/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Contract createContract(@PathVariable Long id,
			@RequestBody Contract contract, HttpServletResponse response) throws Exception {

		System.out.println("insert Contract: " + contract);

		return contractService.create(id, contract);
	}

	// get bank credentials by idBankCredentials
	@RequestMapping(value = { "/contract/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Contract getContract(@PathVariable Long id, Model model,
			HttpServletResponse response) throws IOException {
		
		System.out.println("get contract by idContract: " + id);
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		
		return entitymanager.find(Contract.class, id);
	}

	// update existing bank credentials
	@RequestMapping(value = "/contract/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Contract updateContract(@PathVariable Long id, @RequestBody Contract contract,
			Model model, HttpServletResponse response) throws IOException {
		
		System.out.println("update contract by id: " + id);
		System.out.println("update contract: " + contract);

		return contractService.update(id, contract);
	}

}


