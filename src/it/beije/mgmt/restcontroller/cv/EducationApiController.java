package it.beije.mgmt.restcontroller.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.cv.Education;
import it.beije.mgmt.service.*;

@RestController
@RequestMapping("api")
public class EducationApiController {
	
	@Autowired
	CvServiceOld cvService;
	
	// GET education from idUser
	@RequestMapping(value = "/cv/education/{idCv}", method = RequestMethod.GET)
	public @ResponseBody List<Education> getEducationByIdUser(@PathVariable Long idCv) {
		return cvService.getListEducationByIdCv(idCv);
	}
	
	// POST new education for specify idUser
	@PostMapping(value = "/cv/education/{idCv}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Education addNewEducationForIdUser(@PathVariable Long idCv, @RequestBody Education education) throws Exception {
		cvService.createNewEducation(education, idCv);		
		return education;
	}
	
	
	// PUT -> update education by Id
	@PutMapping(value = "cv/education/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateEducationById(@PathVariable Long id, @RequestBody Education education) {
		cvService.updateEducationById(education, id);
	}
	
}
