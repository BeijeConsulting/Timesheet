package it.beije.mgmt.restcontroller.cv;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import it.beije.mgmt.entity.cv.Language;
import it.beije.mgmt.service.CvService;

@RestController
@RequestMapping("api")
public class LanguageApiController {
	
	@Autowired
	private CvService cvService;
	
	@RequestMapping(value = "language/{idCv}", method = RequestMethod.GET)
	public @ResponseBody List<Language> getLanguageById(@PathVariable Long idCv) {
		try {
			return cvService.getLanguagesById(idCv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "setLanguage/{idCv}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Language setLanguage(@PathVariable Long idCv, @RequestBody Language language) {
		return setLanguage(idCv,language);
		
	}
}
	

