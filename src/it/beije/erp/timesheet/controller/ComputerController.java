package it.beije.erp.timesheet.controller;

import java.util.Locale;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.beije.erp.entity.Computer;
import it.beije.erp.entity.User;
import it.beije.erp.service.JPAService;


@Controller
@SessionAttributes("computer")
public class ComputerController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/insertcomputer", method = RequestMethod.GET)
	public String insertComputer(Locale locale, Model model) {
		return "insertcomputer";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/homecomputer", method = RequestMethod.GET)
	public String renderPreHome(Locale locale, Model model) {
		return "homecomputer";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/confirmdatacomputer", method = RequestMethod.POST)
	public String confirmData(@Validated Computer computer, Model model) {
		model.addAttribute("computer", computer);
		return "confirmdatacomputer";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/registercomputer", method = RequestMethod.POST)
	public String registerComputer(@Validated Computer computer, Model model) {
		computer.setAvailability(true);
		JPAService.save(computer);
		return "conferma";
	}
}