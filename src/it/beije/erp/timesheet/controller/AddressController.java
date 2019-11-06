package it.beije.erp.timesheet.controller;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.erp.entity.User;

@Controller
public class AddressController {
	
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public String getAddresses(@RequestParam(name="id") int idUser, Model model) {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("MalangMVC").createEntityManager();
		entityManager.getTransaction().begin();
		
		//Get user with the specified id
		User user = entityManager.find(User.class, idUser);
		
		//TODO: show addresses
		
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		model.addAttribute("result", "Nuovo delivery aggiunto");
		
		return "addresses";
	}
	
}
