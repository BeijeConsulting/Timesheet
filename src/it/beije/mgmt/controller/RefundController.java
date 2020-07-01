package it.beije.mgmt.controller;

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

import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.model.Refund;
import it.beije.mgmt.service.RefundService;

@RestController
public class RefundController extends BaseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RefundService refundService;
	
	
	@RequestMapping(value = "/refunds", method = RequestMethod.GET)
	public @ResponseBody List<Refund> getRefunds() {
		
		log.debug("GET /refunds");
		try {
			return refundService.getRefunds();
		}catch(MasterException e) {
			throw e;
		}
	}
	
	
	@RequestMapping(value = "/refund/user/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Refund createRefund(@PathVariable Long id,
			@RequestBody Refund refund, HttpServletResponse response) throws Exception {
		
		log.debug("POST /refund/user/{id}");
		try {
			return refundService.create(id, refund);
		}catch(RuntimeException e) {
			throw e;
		}
	}
	
	
	@RequestMapping(value = "/refund/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Refund updateRefund(@PathVariable Long id, @RequestBody Refund refund,
			Model model, HttpServletResponse response) throws IOException {
		
		log.debug("PUT /refund/{id}");
		try {
			return refundService.update(id, refund);
		}catch(MasterException e) {
			throw e;
		}
	}
}