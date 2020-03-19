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

import it.beije.mgmt.entity.cv.Formazione;
import it.beije.mgmt.service.*;

@RestController
@RequestMapping("api")
public class FormazioneApiController {
	
	@Autowired
	CvService cvService;
	
	// GET fomazione from idUser
	@RequestMapping(value = "/cv/formazione/{idCv}", method = RequestMethod.GET)
	public @ResponseBody List<Formazione> getFormazioneByIdUser(@PathVariable Long idCv) {
		return cvService.getListFormazioneByIdCv(idCv);
	}
	
	// POST new fomazione for specify idUser
	@PostMapping(value = "/cv/formazione/{idCv}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Formazione addNewFormazioneForIdUser(@PathVariable Long idCv, @RequestBody Formazione formazione) throws Exception {
		cvService.createNewFormazione(formazione, idCv);		
		return formazione;
	}
	
	
	// PUT -> update formazione by Id
	@PutMapping(value = "cv/formazione/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateFormazioneById(@PathVariable Long id, @RequestBody Formazione formazione) {
		updateFormazioneById(id, formazione);
	}
	
}
