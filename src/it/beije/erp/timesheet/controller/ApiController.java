package it.beije.erp.timesheet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.erp.timesheet.entity.Timetable;
import it.beije.erp.timesheet.entity.User;
import it.beije.erp.timesheet.service.UserService;


@Controller
public class ApiController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/testJson", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseStatus(HttpStatus.CREATED)
    public void handleJsonPostRequest(@RequestBody User user, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("saving user: "+user);
    	
    	user.setId(user.getId() + 1);
    	
    	response.setStatus(200);
    	response.setContentType("application/json");
    	response.getWriter().append(user.toString());
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("saving user: "+user.getFirstName());
    	
    	userService.create(user);
    	
    	response.setStatus(200);
//    	response.setContentType("application/json");
//    	response.getWriter().append(user.toString());
    }


	@RequestMapping(value = "/testJsonTT", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void handleJsonPostRequest(@RequestBody Timetable timetable, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("timetable : "+timetable);
    	
    	response.setStatus(200);
    	response.setContentType("application/json");
    	response.getWriter().append(timetable.toString());
    }

}