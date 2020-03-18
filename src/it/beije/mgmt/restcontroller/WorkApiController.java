package it.beije.mgmt.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.cv.Work;
import it.beije.mgmt.service.AddressService;
import it.beije.mgmt.service.CVService;

@RestController
@RequestMapping("api")
public class WorkApiController {
	
	@Autowired
	private CVService CvService;

	@Transactional
	@RequestMapping(value = "/user/work", method = RequestMethod.GET)
	public @ResponseBody List<Work> getWork(Long id) {

		return null;

	}
}
