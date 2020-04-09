package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.beije.mgmt.dto.UserDto;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.ErrorMessage;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
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

	///////// START USER //////////////////////
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<UserDto> getUsers(Model model, HttpServletResponse response) throws IOException {
		try{
			return userService.caricaTutti();
		}catch(MasterException e) {
			throw e;
		}
	}

	// Quando cerco "/user/{id}/{complete}, la variabile è opzionale, il valore di
	// default è false, se è true mi da lo User completo
	// Quando cerco "/user/{id} automaticamente mi da la versione short di User
//	@RequestMapping(value = {"/user/{id}/{complete}", "/user/{id}"}, method = RequestMethod.GET)

	@RequestMapping(value = { "/user/{id}" }, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody UserDto getUserDto(@PathVariable Long id, // @PathVariable(required=false) boolean complete,
			@RequestParam(required = false) boolean complete, Model model, HttpServletResponse response)
			throws IOException {
		try {
			UserDto us = userService.findApi(id, complete);
			return us;
		}catch(MasterException e) {
			throw e;
		}
	}

//	@RequestMapping(value = "/userdtolong/{id}", method = RequestMethod.GET)
//	public @ResponseBody UserDto getUserDtoLong(@PathVariable Long id, Model model,
//			HttpServletResponse response) throws IOException {
//		System.out.println("get user by id: " + id);
//
//		return userService.findApiLong(id);
//	}

	// Questo metodo consente di avere lo user completo con tutti gli storici, la
	// variabile historical è opzionale
	@RequestMapping(value =  "/user_entity/{id}" , method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable Long id, Model model, HttpServletResponse response)
			throws IOException {
		try {
			User us = userService.find(id);
			return us;
		}catch(MasterException e) {
			throw e;
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User insertUser(@RequestBody User user, HttpServletResponse response) throws IOException {
		
		System.out.println("insert user: " + user);
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
			return userService.update(id, user);
		}catch(MasterException e) {
			throw e;
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserDto> searchUser(@RequestBody UserRequest req) {

		return new UserService().trovaUtente(req);

		/*
		 * {"first_name" : "nome", "last_name" : "cognome", "email" : "email",
		 * "fiscal_code" : "codice fiscale"
		 * 
		 * }
		 */
	}

	// Gruppo delta - Rest per l'archiviazione utente
	// Torna un boolean per il controllo sull'effettiva archiviazione
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean archiveUser(@PathVariable Long id, @RequestBody User user,
			HttpServletResponse response) throws IOException {
		System.out.println("archive user by id: " + id);
		System.out.println("archve user: " + user);
		return userService.archiviaUtente(user);
	}

	///////// END USER //////////////////////

}
