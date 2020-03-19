package it.beije.mgmt.restcontroller.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.cv.Certification;
import it.beije.mgmt.service.CvService;

@RestController
@RequestMapping("api")
public class CertificationApiController {
	
	@Autowired
	CvService cvService;

	@GetMapping(value = "cv/certification/{idUser}")
	public @ResponseBody List<Certification> getCertification(@PathVariable Long idUser) {
		return cvService.getListCertificationByUserId(idUser);
	}
	
	@PostMapping(value = "cv/certification/{idUser}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertNewCertificationForUserId(@PathVariable Long idUser, @RequestBody Certification certification) {
		cvService.insertNewCertificationForUser(idUser, certification);
	}
	
	@PutMapping(value = "cv/certification/{id}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCertificationById(@PathVariable Long id, @RequestBody Certification certification) {
		cvService.updateCertificationById(id, certification);
	}
	
}
