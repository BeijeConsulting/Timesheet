package it.beije.formazione.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.formazione.spring.model.User;
import it.beije.jpa.GestioneUtenti;


@Controller
public class ApiController {

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
    	System.out.println("saving user: "+user);
    	
		GestioneUtenti.creaUtente(user.getFirstName(), user.getLastName(), user.getPersonalEmail(), 
				user.getWorkEmail(), user.getPhone(), user.getFiscalCode(), 0, user.getPassword());
    	
    	response.setStatus(200);
//    	response.setContentType("application/json");
//    	response.getWriter().append(user.toString());
    }

}
