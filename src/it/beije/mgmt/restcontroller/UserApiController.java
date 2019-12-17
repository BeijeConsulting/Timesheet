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

import it.beije.erp.dto.UserDto;
import it.beije.erp.entity.User;
import it.beije.jpa.UserRequest;
import it.beije.mgmt.service.UserService;


@RestController
@RequestMapping("api")
public class UserApiController {

	/**
	 * ANDARE NELLA CLASSE USERSERVICE PER VEDERE TUTTI I METODI UTILIZZATI PER LE API
	 */
	@Autowired
	private UserService userService;

	///////// START USER //////////////////////
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<UserDto> getUsers(Model model, HttpServletResponse response) throws IOException {
		return userService.caricaTutti();
	}
	
	//Quando cerco "/user/{id}/{complete}, la variabile è opzionale, il valore di default è false, se è true mi da lo User completo
	//Quando cerco "/user/{id} automaticamente mi da la versione short di User
//	@RequestMapping(value = {"/user/{id}/{complete}", "/user/{id}"}, method = RequestMethod.GET)
	@RequestMapping(value = {"/user/{id}"}, method = RequestMethod.GET)
	public @ResponseBody UserDto getUserDto(@PathVariable Long id,// @PathVariable(required=false) boolean complete,
			@RequestParam(required = false) boolean complete,
			Model model, HttpServletResponse response) throws IOException {
		System.out.println("get user by id: " + id);
		if (complete==false) {
		return userService.findApi(id);
		}
		else{
			return userService.findApiLong(id);
		}
	}
	
//	@RequestMapping(value = "/userdtolong/{id}", method = RequestMethod.GET)
//	public @ResponseBody UserDto getUserDtoLong(@PathVariable Long id, Model model,
//			HttpServletResponse response) throws IOException {
//		System.out.println("get user by id: " + id);
//
//		return userService.findApiLong(id);
//	}
	
	//Questo metodo consente di avere lo user completo con tutti gli storici, la variabile historical è opzionale
	@RequestMapping(value = {"/user_entity/{id}"}, method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable Long id,
			Model model,HttpServletResponse response) throws IOException {
		System.out.println("get user by id: " + id);
			return userService.find(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User insertUser(@RequestBody User user, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("insert user: " + user);

		return userService.create(user);
	}
	
	//FIXARE QUESTA API (PROBLEMA DI LAZILY INITIALIZE SUGLI INDIRIZZI)
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUser(@PathVariable Long id, @RequestBody User user, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("update user by id: " + id);
		System.out.println("update user: " + user);

		return userService.update(id, user);
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserDto> searchUser(@RequestBody UserRequest req){
		
		return new UserService().trovaUtente(req);
		
		
		
		
		/*{"first_name" : "nome",
		 * "last_name" : "cognome",
		 * "email" : "email",
		 * "fiscal_code" : "codice fiscale"
		 * 
		}
		*/
	}
	
	
	///////// END USER //////////////////////

}
