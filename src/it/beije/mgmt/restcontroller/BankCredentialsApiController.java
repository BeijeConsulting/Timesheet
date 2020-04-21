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

import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.service.BankCredentialsService;

@RestController
@RequestMapping("api")
public class BankCredentialsApiController {

	@Autowired
	private BankCredentialsService bankCredentialsService;

	/****************** BANK CREDENTIALS *****************/
	// storico bank credentials user
	@Transactional
	@RequestMapping(value = "/bankCredentials/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<BankCredentials> getCredentialsForUser(@PathVariable Long id) {

		try {
			return bankCredentialsService.getBankCredentialsByUser(id);
		}catch(MasterException e) {
			throw e;
		}
	}

	// write new bank credentials by idUser
	@RequestMapping(value = "/bankCredentials/user/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BankCredentials createBankCredentials(@PathVariable Long id,
			@RequestBody BankCredentials bankCredentials, HttpServletResponse response) throws Exception {

		try {
			return bankCredentialsService.create(id, bankCredentials);
		} catch(RuntimeException e) {
			throw e;
		}
	}

	// get bank credentials by idBankCredentials
	@RequestMapping(value = { "/bankCredentials/{id}" }, method = RequestMethod.GET)
	public @ResponseBody BankCredentials getBankcredentials(@PathVariable Long id, Model model,	HttpServletResponse response) throws IOException {
		
		try {
			return bankCredentialsService.find(id);
		}catch(MasterException e) {
			throw e;
		}
	}

	// update existing bank credentials
	@RequestMapping(value = "/bankCredentials/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BankCredentials updateBankCredential(@PathVariable Long id, @RequestBody BankCredentials bankCredentials,
			Model model, HttpServletResponse response) throws IOException {
		
		try {
			return bankCredentialsService.update(id, bankCredentials);
		}catch(MasterException e) {
			
			throw e;
		}
	}
}