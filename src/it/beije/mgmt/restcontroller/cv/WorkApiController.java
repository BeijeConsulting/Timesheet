package it.beije.mgmt.restcontroller.cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.cv.Certification;
import it.beije.mgmt.entity.cv.Work;
import it.beije.mgmt.service.CvServiceOld;

@RestController
@RequestMapping("api")
public class WorkApiController {
	
	@Autowired
	CvServiceOld cvService;
	
	@GetMapping(value = "cv/work/{idCV}")
	public @ResponseBody List<Work> getWork(@PathVariable Long idCV) {
		return cvService.getListWorkByUserId(idCV);
	}
	
	@PostMapping(value = "cv/work/{idCV}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertNewWorkForUserId(@PathVariable Long idCV, @RequestBody Work work) throws Exception {
		cvService.insertNewWorkForUser(idCV, work);
	}
	
	@PutMapping(value = "cv/work/{id}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateWorkById(@PathVariable Long id, @RequestBody Work work) {
		cvService.updateWorkById(id, work);
	}
}
