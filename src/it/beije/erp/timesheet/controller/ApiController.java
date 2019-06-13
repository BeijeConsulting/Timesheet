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

import com.fasterxml.jackson.databind.ObjectMapper;

import it.beije.erp.timesheet.entity.Timetable;
import it.beije.erp.timesheet.entity.User;
import it.beije.erp.timesheet.service.UserService;


@Controller
@RequestMapping("/api")
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
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	response.setStatus(200);//STATO RISPOSTA
    	response.setContentType("application/json");//TIPO RISPOSTA
    	response.getWriter().append(objectMapper.writeValueAsString(user));//CORPO RISPOSTA
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("saving user: "+user.getFirstName());
    	
    	userService.create(user);
    	
       	ObjectMapper objectMapper = new ObjectMapper();
    	response.setStatus(200);//STATO RISPOSTA
    	response.setContentType("application/json");//TIPO RISPOSTA
    	response.getWriter().append(objectMapper.writeValueAsString(user));//CORPO RISPOSTA
    }


	@RequestMapping(value = "/testJsonTT", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void handleJsonPostRequest(@RequestBody Timetable timetable, Model model,
    		HttpServletResponse response) throws IOException {
    	System.out.println("timetable : "+timetable);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	response.setStatus(200);//STATO RISPOSTA
    	response.setContentType("application/json");//TIPO RISPOSTA
    	response.getWriter().append(objectMapper.writeValueAsString(timetable));//CORPO RISPOSTA
    }

}
