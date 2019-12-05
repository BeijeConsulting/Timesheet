package it.beije.erp.controller.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.erp.entity.Computer;
import it.beije.erp.service.JPAService;
import it.beije.erp.timesheet.service.ComputerService;

@RestController
@RequestMapping("api")
public class ComputerApiController {
	

		///////// START USER //////////////////////
		@RequestMapping(value = "/computers", method = RequestMethod.GET)
		public @ResponseBody List<Computer> getUsers(Model model) throws IOException {
			return ComputerService.all();
		}

		@RequestMapping(value = "/computer/{id}", method = RequestMethod.GET)
		public @ResponseBody Computer getComputer(@PathVariable Long id, Model model,
				HttpServletResponse response) throws IOException {
			System.out.println("get computer by id: " + id);

			return ComputerService.find(id);
		}

		@RequestMapping(value = "/computer", method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Computer insertComputer(@RequestBody Computer computer, Model model,
				HttpServletResponse response) throws IOException {
			System.out.println("insert computer: " + computer);

			return ComputerService.create(computer);
		}

		@RequestMapping(value = "/computer/{id}", method = RequestMethod.PUT,
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Computer updateComputer(@PathVariable Long id, @RequestBody Computer computer, Model model,
				HttpServletResponse response) throws IOException {
			System.out.println("update computer by id: " + id);
			System.out.println("update computer: " + computer);

			return ComputerService.update(computer, id);
		}

}
