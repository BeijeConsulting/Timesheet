package it.beije.mgmt.restcontroller.cv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.service.AddressService;
import it.beije.mgmt.service.CvService;


@RestController
@RequestMapping("api")
public class CvApiController {

	@Autowired
	private CvService cvService;

//	@RequestMapping(value = "cv/{technology}", method = RequestMethod.GET)
//	public @ResponseBody List<CV> getCvByTechnology(@PathVariable String technology) {
//		try {
//			return cvservice.findCvByTechnology(technology);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		
//			
//	}

	@RequestMapping(value = "/cv/{idCV}", method = RequestMethod.GET)
	public @ResponseBody CV getCvById(@PathVariable Long idCV) {
		try {
			return cvService.findCvById(idCV);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "cv/user/{idUser}")
	public @ResponseBody CV getCvByUserId(@PathVariable Long idUser) {
		return cvService.findCvByUserId(idUser);
	}

	
	@RequestMapping(value = "update/cv/{idCv}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CV updateCV(@PathVariable Long idCv, @RequestBody CV cv) throws IOException {
		return cvService.updateCv(idCv, cv);
	}
}


