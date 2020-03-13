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

import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.service.JPAService;

@Controller
public class BankCredentialsController {
	@RequestMapping(value = "/bankcredentials/{id}", method = RequestMethod.GET)
	public String getBankCredentials(@PathVariable Long id, Model model) {
		
		User user = JPAService.getBean(User.class, id);
		List<BankCredentials> bankCredentials = user.getBankCredentials();

		model.addAttribute("bankcredentials", bankCredentials);
		return "userbankcredentials";
	}
	
	//Insert form
		@RequestMapping(value = "/bankcredentialsform", method = RequestMethod.GET)
		public String getForm() {
			return "bankcredentialsform";
		}
		
		//POST : insert of new BankCredentials
		@RequestMapping(value = "/registerbankcredentials", method = RequestMethod.POST)
		public String getBankCredentials(@Validated BankCredentials bankCredentials, Model model) {
			
			bankCredentials.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
			JPAService.save(bankCredentials);
			
			model.addAttribute("bankcredentials", bankCredentials);
			return "confermabankcredentials";	
		}
}
