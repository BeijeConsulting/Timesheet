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

import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.dto.UserDto;
import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.service.BankCredentialsService;
import it.beije.mgmt.service.JPAService;
import it.beije.mgmt.service.UserService;

@RestController
@RequestMapping("api")
public class BankCredentialsApiController {

	@Autowired
	private BankCredentialsService bankCredentialsService;
	@Autowired
	private UserService userService;

	/****************** BANK CREDENTIALS *****************/
	// storico bank credentials user
	@Transactional
	@RequestMapping(value = "/bankCredentials/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<BankCredentials> getCredentialsForUser(@PathVariable Long id) {

//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//
//		EntityManager entitymanager = emfactory.createEntityManager();
//
//		User user = entitymanager.find(User.class, id);
//		System.out.println("user.getBankCredentials()?"
//				+ (user.getBankCredentials() != null ? user.getBankCredentials().size() : "NULL"));
//		List<BankCredentials> bankcredentials = user.getBankCredentials();
//
//		entitymanager.close();
//
//		return bankcredentials;
		User us = userService.findById(id);
		if(us.isEmpty()) 
			throw new NoContentException("Non è stato trovato un utente con l'id selezionato");
		return bankCredentialsService.getBankCredentialsByUser(id);

	}

	// write new bank credentials by idUser
	@RequestMapping(value = "/bankCredentials/user/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BankCredentials createBankCredentials(@PathVariable Long id,
			@RequestBody BankCredentials bankCredentials, HttpServletResponse response) throws Exception {

		try {
			BankCredentials bc = bankCredentialsService.create(id, bankCredentials);
			return bc;
		}catch(RuntimeException e) {
			throw new InvalidJSONException("Non è stato possibile aggiungere la cordinata bancaria desiderata");
		}
	}

	// get bank credentials by idBankCredentials
	@RequestMapping(value = { "/bankCredentials/{id}" }, method = RequestMethod.GET)
	public @ResponseBody BankCredentials getBankcredentials(@PathVariable Long id, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("get bankCredentials by idBankCredentials: " + id);
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		BankCredentials bankCredentials = entitymanager.find(BankCredentials.class, id);
		if(bankCredentials.getId()==null) 
			throw new NoContentException("Non è stata trovata una cordinata bancaria con l'id selezionato");
		return bankCredentials;
	}

	// update existing bank credentials
	@RequestMapping(value = "/bankCredentials/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BankCredentials updateBankCredential(@PathVariable Long id, @RequestBody BankCredentials bankCredentials,
			Model model, HttpServletResponse response) throws IOException {
		
		try {
			BankCredentials bc = bankCredentialsService.update(id, bankCredentials);
			return bc;
		}catch(RuntimeException e) {
			throw new InvalidJSONException("Non è stato possibile modificare i dati della cordinata bancaria desiderata");
		}
	}

}