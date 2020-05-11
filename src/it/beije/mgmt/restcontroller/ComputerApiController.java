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

@RestController
@RequestMapping("api")
public class ComputerApiController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ComputerService computerService;


	/**
	 * DA AGGIORNARE TUTTE LE API CONFRONTANDOSI CON QUELLE DI USER, COSTRUENDO UN
	 * COMPUTERDTO
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */

	
	
	//FUNZIONA MA NON IMPLEMENTATO IN JSP
		@RequestMapping(value = "/computers", method = RequestMethod.GET)
		public @ResponseBody List<Computer> getComputers(Model model) throws IOException {
	try{
			log.debug("GET /computers");
			return computerService.all();
		
		}catch(RuntimeException e) {
			throw e;
		}
}
		
	

	// FUNZIONA MA NON IMPLEMENTATO IN JSP
	@RequestMapping(value = "/computer/{id}", method = RequestMethod.GET)
	public @ResponseBody Computer getComputer(@PathVariable Long id, Model model, HttpServletResponse response)
			throws IOException {
		log.info("get computer by id: " + id);
		log.debug("GET /computers/{id}");
		try {
			//	System.out.println("prova2");
			return computerService.find(id);
		}catch(RuntimeException e) {
			throw e;
		}
		
	}

	// TESTATO MA NON FUNZIONA IL PERSIST (400- The request sent by the client was
	// syntactically incorrect.)
	@RequestMapping(value = "/computer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Computer insertComputer(@RequestBody Computer computer, Model model,
			HttpServletResponse response) throws IOException {
		log.info("insert computer: " + computer);
		log.debug("POST /computers");
		try {
			//	System.out.println("prova3");
			return computerService.create(computer);
		}catch(RuntimeException e) {
			throw e;
		}
		
	}

	@RequestMapping(value = "/computer/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Computer updateComputer(@PathVariable Long id, @RequestBody Computer computer, Model model,
			HttpServletResponse response) throws IOException {
		log.info("update computer by id: " + id);
		log.info("update computer: " + computer);
		log.debug("PUT /computer/{id}");
		try {
		//	System.out.println("prova4");
			return computerService.update(computer, id);
		}catch(RuntimeException e) {
			throw e;
		}
		
	}

}
