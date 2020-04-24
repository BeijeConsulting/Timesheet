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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.jpa.UserRequest;
import it.beije.mgmt.service.UserService;

@RestController
@RequestMapping("api")
public class UserApiController {

	/**
	 * ANDARE NELLA CLASSE USERSERVICE PER VEDERE TUTTI I METODI UTILIZZATI PER LE
	 * API
	 */
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers(Model model, HttpServletResponse response) throws IOException {
		try{
			return userService.findAll();
		}catch(MasterException e) {
			throw e;
		}
	}

	// Quando cerco "/user/{id}/{complete}, la variabile è opzionale, il valore di
	// default è false, se è true mi da lo User completo
	// Quando cerco "/user/{id} automaticamente mi da la versione short di User
//	@RequestMapping(value = {"/user/{id}/{complete}", "/user/{id}"}, method = RequestMethod.GET)

	@RequestMapping(value = { "/user/{id}" }, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody User getUser(@PathVariable Long id, // @PathVariable(required=false) boolean complete,
			@RequestParam(required = false) boolean complete, Model model, HttpServletResponse response) throws IOException {
		
		try {
			return userService.find(id, complete);
		}catch(MasterException e) {
			throw e;
		}
	}

	// Questo metodo consente di avere lo user completo con tutti gli storici, la
	// variabile historical è opzionale
	//Non è più necessario ricevere lo storico
/*	@RequestMapping(value =  "/user_entity/{id}" , method = RequestMethod.GET)
	public @ResponseBody User getUserEntity(@PathVariable Long id, Model model, HttpServletResponse response) throws IOException {
		
		try {
			return userService.findById(id);
		}catch(MasterException e) {
			throw e;
		}
	}*/

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User insertUser(@RequestBody User user, HttpServletResponse response) throws IOException {
		
		try {
			return userService.create(user);
		}catch(MasterException e) {
			throw e;
		}
	}

	// FIXARE QUESTA API (PROBLEMA DI LAZILY INITIALIZE SUGLI INDIRIZZI)
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUser(@PathVariable Long id, @RequestBody User user, HttpServletResponse response)
			throws IOException {
		
		try {
			user.setId(id);
			return userService.update(user);
		}catch(MasterException e) {
			throw e;
		}
	}
	
	// Gruppo delta - Rest per l'archiviazione utente
	// Torna un boolean per il controllo sull'effettiva archiviazione
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean archiveUser(@PathVariable Long id, @RequestBody User user,
			HttpServletResponse response) throws IOException {
		
		return userService.dismissUser(user);
	}

	@RequestMapping(value = "/user/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> searchUser(@RequestBody UserRequest req) {

		return userService.searchUser(req);

		/*
		 * {"first_name" : "nome", "last_name" : "cognome", "email" : "email",
		 * "fiscal_code" : "codice fiscale"
		 * 
		 * }
		 */
	}
}
