package it.beije.mgmt.restcontroller.cv;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import it.beije.mgmt.entity.cv.Language;
import it.beije.mgmt.restcontroller.BaseController;
import it.beije.mgmt.service.CvService;

@RestController
@RequestMapping("api")
public class LanguageApiController extends BaseController{
	
	@Autowired
	private CvService cvService;
	
	@RequestMapping(value = "/language/{idCv}", method = RequestMethod.GET)
	public @ResponseBody List<Language> getLanguageById(@PathVariable Long idCv) {
		return cvService.getLanguagesById(idCv);
	}

	@RequestMapping(value = "language/{idCv}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Language setLanguage(@PathVariable Long idCv, @RequestBody Language language) throws Exception {
		return cvService.setLanguage(idCv,language);

	}

	@RequestMapping(value = "language/{idCv}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Language updateLanguage(@PathVariable Long idCv,@RequestBody Language language) throws Exception {
		return cvService.updateLanguage(idCv,language);
	}

}
	

