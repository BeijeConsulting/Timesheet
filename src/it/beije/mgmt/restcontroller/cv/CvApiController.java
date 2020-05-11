package it.beije.mgmt.restcontroller.cv;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.service.CvService;

@RestController
@RequestMapping("api")
public class CvApiController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CvService cvService;

	@RequestMapping(value = "/cv/{idCV}", method = RequestMethod.GET)
	public @ResponseBody CV getCvById(@PathVariable Long idCV) {
		log.debug("GET /cv/{idCV}");
		try {
			return cvService.getCvByIdCv(idCV);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "cv/user/{idUser}")
	public @ResponseBody CV getCvByUserId(@PathVariable Long idUser) {
		log.debug(" cv/user/{idUser}");
		return cvService.getCvByUserId(idUser);
	}

	@PostMapping(value="cv/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public void addNewCvByIdUser(@PathVariable Long idUser, @RequestBody CV cv) {
		log.debug(" cv/{idUser}");
		cvService.addNewCv(idUser,cv);
	}


	@RequestMapping(value = "update/cv/{idCv}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CV updateCV(@PathVariable Long idCv, @RequestBody CV cv) throws IOException {
		log.debug("PUT update/cv/{idCv}");
		return cvService.updateCv(idCv, cv);
	}
}


