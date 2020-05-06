package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Computer;
import it.beije.mgmt.service.ComputerService;
import it.beije.mgmt.service.JPAService;

@RestController
@RequestMapping("api")
public class ComputerApiController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ComputerService computerService;
	
		/**
		 * DA AGGIORNARE TUTTE LE API CONFRONTANDOSI CON QUELLE DI USER, COSTRUENDO UN COMPUTERDTO
		 * @param model
		 * @return
		 * @throws IOException
		 */
		///////// START USER //////////////////////
	
	
	//FUNZIONA MA NON IMPLEMENTATO IN JSP
		@RequestMapping(value = "/computers", method = RequestMethod.GET)
		public @ResponseBody List<Computer> getComputers(Model model) throws IOException {
			// no syso
			log.debug("GET /computers");
			return computerService.all();
		}

		
		//FUNZIONA MA NON IMPLEMENTATO IN JSP
		@RequestMapping(value = "/computer/{id}", method = RequestMethod.GET)
		public @ResponseBody Computer getComputer(@PathVariable Long id, Model model,
				HttpServletResponse response) throws IOException {
			log.debug("GET /computers/{id}");
			System.out.println("get computer by id: " + id);

			return computerService.find(id);
		}

		//TESTATO MA NON FUNZIONA IL PERSIST (400- The request sent by the client was syntactically incorrect.)
		@RequestMapping(value = "/computer", method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Computer insertComputer(@RequestBody Computer computer, Model model,
				HttpServletResponse response) throws IOException {
			log.debug("POST /computers");
			System.out.println("insert computer: " + computer);

			return computerService.create(computer);
		}

		
		
		@RequestMapping(value = "/computer/{id}", method = RequestMethod.PUT,
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Computer updateComputer(@PathVariable Long id, @RequestBody Computer computer, Model model,
				HttpServletResponse response) throws IOException {
			log.debug("PUT /computer/{id}");
			System.out.println("update computer by id: " + id);
			System.out.println("update computer: " + computer);

			return computerService.update(computer, id);
		}

}
