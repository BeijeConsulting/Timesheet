package it.beije.erp.timesheet.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.beije.erp.entity.Computer;
import it.beije.erp.entity.User;
import it.beije.erp.entity.UserComputer;
import it.beije.erp.service.JPAService;
import it.beije.erp.timesheet.service.UserService;


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
		JPAService.save(computer);
		return "conferma";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/idmodifycomputer", method = RequestMethod.GET)
	public String modifyComputer(@Validated Computer computer, Model model) {
		return "idmodifycomputer";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/modifycomputer", method = RequestMethod.POST)
	public String modificaDati(@Validated Computer computer, Model model) {
		
		try {
		
		computer = JPAService.getBean(Computer.class, computer.getId());
		model.addAttribute("computer", computer);
			return "modifycomputer";
		}
		catch (NullPointerException e) {
			return "idnontrovato";
		}
	
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/regmodifycomputer", method = RequestMethod.POST)
	public String regModifyComputer(@Validated Computer computer, Model model) {
		JPAService.modify(computer);
		return "conferma";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/deletecomputer", method = RequestMethod.GET)
	public String deleteComputer(@Validated Computer computer, Model model) {
		return "deletecomputer";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")		
	@RequestMapping(value = "/viewinformations", method = RequestMethod.POST)
	public String viewInformations(@Validated Computer computer, Model model) {
		
		try {
		
		computer = JPAService.getBean(Computer.class, computer.getId());
		model.addAttribute("computer", computer);
			return "viewinformations";
		}
		catch (NullPointerException e) {
			return "idnontrovato";
		}
	
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/insertdisposaldate", method = RequestMethod.POST)
	public String insertDisposalDate(@Validated Computer computer, Model model) {
		computer.setAvailability(false);
		computer.setDisposalDate(Date.valueOf(LocalDate.now()));
		JPAService.modify(computer);
		return "conferma";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/searchcomputer", method = RequestMethod.GET)
	public String searchComputer(Locale locale, Model model) {
		return "searchcomputer";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/showcomputer", method = RequestMethod.POST)
	public String showComputer(@Validated Computer computer, Locale locale, Model model, HttpServletRequest request) {
		List<Computer> computers = new ArrayList<>();
		int check = 0;
		check = Integer.valueOf(request.getParameter("check"));
		computers=JPAService.getComputers(check);
		model.addAttribute("computers", computers);
		return "showcomputer";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/searchcomputer", method = RequestMethod.POST)
	public String searchComputer(@Validated Computer computer, Locale locale, Model model, HttpServletRequest request) {
	List<Computer> computers = new ArrayList<>();
		computers=JPAService.searchComputer(computer.getSerialNumber(),
											computer.getRam(),
											computer.getCpu(),
											computer.getHardDisk());
		model.addAttribute("computers", computers);
		return "showcomputer";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/assigncomputer", method = RequestMethod.GET)
	public String assignComputer(Locale locale, Model model) {
		List<Computer> computers = new ArrayList<>();
		int check = 1;
		computers=JPAService.getComputers(check);
		model.addAttribute("computers", computers);
		return "assigncomputer";
	}
	
	@PreAuthorize("hasAnyRole('USER')")	
	@RequestMapping(value = "/assigncomputer", method = RequestMethod.POST)
	public String assignComputers(@Validated UserComputer userComputer, Locale locale, Model model, HttpServletRequest request) {
		int id=Integer.valueOf(request.getParameter("idComputer"));
		Computer computer = new Computer();
		computer=JPAService.getBean(Computer.class , id);
		computer.setAvailability(false);
		JPAService.modify(computer);
		JPAService.save(userComputer);
		return "conferma";
	}
}