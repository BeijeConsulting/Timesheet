package it.beije.mgmt.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.service.JPAService;

@Controller
public class ContractController {

	@RequestMapping(value = "/contract/user/{id}", method = RequestMethod.GET)
	public String getContract(@PathVariable int id, Model model) {
		
		User user = JPAService.getBean(User.class, id);
		List<Contract> contracts = null/*user.getContracts()*/;
		
		model.addAttribute("contracts", contracts);
		return "usercontracts";
	}
	
	@RequestMapping(value = "/contractform", method = RequestMethod.GET)
	public String getForm() {
		return "contractform";
	}
	
	@RequestMapping(value = "/contract", method = RequestMethod.POST)
	public String postContract(@Validated Contract address, Model model) {
		
		JPAService.save(address);
		
		model.addAttribute("address", address);
		return "home";	
	}
	
	@RequestMapping(value = "/contractupdate", method = RequestMethod.PUT)
	public String putContracts(@Validated Contract address, Model model) {
		
		JPAService.save(address);
		
		model.addAttribute("address", address);
		return "home";	
	}
	
}
