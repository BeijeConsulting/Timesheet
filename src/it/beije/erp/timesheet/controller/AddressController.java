package it.beije.erp.timesheet.controller;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.erp.entity.Address;
import it.beije.erp.entity.User;
import it.beije.erp.service.JPAService;

@Controller
public class AddressController {
	
	// GET : read addresses from User within id
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public String getAddresses(@RequestParam(name="id") int idUser, Model model) {
		
		List<Address> addresses = JPAService.getBean(User.class, idUser).getAddresses();

		model.addAttribute("addresses", addresses);
		return "useraddresses";
	}
	
	//Insert form
	@RequestMapping(value = "/addressform", method = RequestMethod.GET)
	public String getForm() {
		return "addressform";
	}
	
	//POST : insert of new User
	@RequestMapping(value = "/registeraddress", method = RequestMethod.POST)
	public String getAddresses(@Validated Address address, Model model) {
		
		System.out.println("Input : " + address);
		
		address.setStartDate(LocalDate.now());
		JPAService.save(address);
		
		model.addAttribute("address", address);
		return "addressform";	
	}
	
}
