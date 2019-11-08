package it.beije.erp.controller.api;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

import it.beije.erp.entity.User;
import it.beije.erp.timesheet.service.UserService;


@RestController
@RequestMapping("api")
public class UserApiController {

	@Autowired
	private UserService userService;

	///////// START USER //////////////////////
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers(Model model, HttpServletResponse response) throws IOException {
		return userService.caricaTutti();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable int id, Model model,
			HttpServletResponse response) throws IOException {
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

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUser(@PathVariable int id, @RequestBody User user, Model model,
			HttpServletResponse response) throws IOException {
		System.out.println("update user by id: " + id);
		System.out.println("update user: " + user);

		return userService.update(id, user);
	}
	///////// END USER //////////////////////

}
