package it.beije.mgmt.restcontroller.cv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.repository.CvRepository;
import it.beije.mgmt.service.AddressService;
import it.beije.mgmt.service.CvService;

@RestController
@RequestMapping("api")
public class CvApiController {
	
	@Autowired
	private CvService cvservice;
	
	@RequestMapping(value = "cv/{technology}", method = RequestMethod.GET)
	public @ResponseBody List<CV> getCvByTechnology(@PathVariable String technology) {
		try {
			return cvservice.findCvByTechnology(technology);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
			
	}

}
