package it.beije.erp.timesheet.controller;

import java.sql.Date;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.erp.entity.Address;
import it.beije.erp.entity.User;
import it.beije.erp.service.JPAService;

@Controller
public class AddressController {
	
	// GET : read addresses from User within id
	@RequestMapping(value = "/addresses/{id}", method = RequestMethod.GET)
	public String getAddresses(@PathVariable Long id, Model model) {
		
		User user = JPAService.getBean(User.class, id);
		List<Address> addresses = user.getAddresses();

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
		
		address.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
		JPAService.save(address);
		
		model.addAttribute("address", address);
		return "addressform";	
	}
	
}
