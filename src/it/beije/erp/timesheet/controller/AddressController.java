package it.beije.erp.timesheet.controller;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.erp.entity.Address;
import it.beije.erp.entity.User;

@Controller
public class AddressController {
	
	//TODO: lista, form e post, modifica
	
	// GET : read addresses from User within id
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public String getAddresses(@RequestParam(name="id") int idUser, Model model) {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("Timesheet").createEntityManager();
		
		//Get user with the specified id
		List<Address> addresses = entityManager.find(User.class, idUser).getAddresses();

		entityManager.close();
		
		model.addAttribute("addresses", addresses);

		return "addresses";
	}
	
	//Insert form
	@RequestMapping(value = "/addressform", method = RequestMethod.GET)
	public String getForm() {
		return "addressform";
	}
	
	//POST : insert of new User
	@RequestMapping(value = "/registeraddress", method = RequestMethod.POST)
	public String getAddresses(@Validated Address address, Model model) {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("Timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		
		//Register new address
		entityManager.persist(address);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		model.addAttribute("address", address);
		
		return "";	
	}
	
}
