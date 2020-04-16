package it.beije.mgmt.restcontroller;


import java.io.IOException;
import java.util.List;

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

import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.service.ContractService;

@RestController
@RequestMapping("api")

public class ContractApiController {
	@Autowired
	private ContractService contractService;

	
	@Transactional
	@RequestMapping(value = "/contracts/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Contract> getContractForUser(@PathVariable Long id) {
		
		try {
			return contractService.getContractByUser(id);
		}catch(MasterException e) {
			throw e;
		}
	}

	@RequestMapping(value = "/contract/user/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Contract createContract(@PathVariable Long id,
			@RequestBody Contract contract, HttpServletResponse response) throws Exception {

		try {
			return contractService.create(id, contract);
		}catch(RuntimeException e) {
			throw e;
		}		
	}

	@RequestMapping(value = { "/contract/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Contract getContract(@PathVariable Long id, Model model,
			HttpServletResponse response) throws IOException {
		
		
		try {
			return contractService.find(id);
		}catch(MasterException e) {
			throw e;
		}
	}

	@RequestMapping(value = "/contract/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Contract updateContract(@PathVariable Long id, @RequestBody Contract contract,
			Model model, HttpServletResponse response) throws IOException {
		
		System.out.println("update contract by id: " + id);
		System.out.println("update contract: " + contract);

		try {
			return contractService.update(id, contract);
		}catch(MasterException e) {
			throw e;
		}
	}
}


