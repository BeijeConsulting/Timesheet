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

import it.beije.erp.entity.Address;
import it.beije.erp.entity.BankCredentials;
import it.beije.erp.entity.Contract;
import it.beije.erp.entity.User;
import it.beije.erp.service.JPAService;
import it.beije.erp.timesheet.entity.Timetable;
import it.beije.mgmt.dto.UserDto;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.service.BankCredentialsService;

@RestController
@RequestMapping("api")
public class BankCredentialsApiController {

	@Autowired
	private BankCredentialsService bankCredentialsService;

	/****************** BANK CREDENTIALS *****************/
	// storico banck credentials user
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

		return bankCredentialsService.getBankCredentialsByUser(id);

	}

	// write new bank credentials by idUser
	@RequestMapping(value = "/bankCredentials/user/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BankCredentials createBankCredentials(@PathVariable Long id,
			@RequestBody BankCredentials bankCredentials, HttpServletResponse response) throws Exception {

		System.out.println("insert BankCredentials: " + bankCredentials);

		return bankCredentialsService.create(id, bankCredentials);

	}

	// get bank credentials by idBankCredentials
	@RequestMapping(value = { "/bankCredentials/{id}" }, method = RequestMethod.GET)
	public @ResponseBody BankCredentials getBankcredentials(@PathVariable Long id, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("get bankCredentials by idBankCredentials: " + id);
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		BankCredentials bankcredentials = entitymanager.find(BankCredentials.class, id);

		return bankcredentials;
	}

	// update existing bank credentials
	@RequestMapping(value = "/bankCredentials/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BankCredentials updateBankCredential(@PathVariable Long id, @RequestBody BankCredentials bankCredentials,
			Model model, HttpServletResponse response) throws IOException {
		System.out.println("update bankCredentials by id: " + id);
		System.out.println("update bankCredential: " + bankCredentials);

		return bankCredentialsService.update(id, bankCredentials);
	}

}