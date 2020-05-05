package it.beije.mgmt.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.entity.cv.Certification;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.beije.mgmt.entity.cv.Education;
import it.beije.mgmt.entity.cv.Work;
import it.beije.mgmt.entity.cv.Language;
import it.beije.mgmt.repository.CertificationRepository;
import it.beije.mgmt.repository.CvRepository;
import it.beije.mgmt.repository.EducationRepository;
import it.beije.mgmt.repository.LanguageRepository;
import it.beije.mgmt.repository.WorkRepository;
import it.beije.mgmt.exception.NoContentException;

@Service
public class CvService {
	
	
	@Autowired
	private CvRepository cvrepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private CertificationRepository certificationRepository;

//---------------------------------------------------------------------------------------------------------------------------------------------------	
	/***** CV *****/
	
	@Transactional
	public CV getCvByUserId(Long idUser) {
		
		
		CV cv =cvrepository.getOne(idUser);
		if(cv==null) 
			throw new NoContentException("ATTENZIONE: Non è stato trovato alcun Cv per questo id utente");
		else
			return cv;
		}		
//---------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public CV getCvByIdCv(Long idCv) {
		
		Optional<CV> cv=cvrepository.findById(idCv);
		
		if(cv.isPresent())
			return cv.get();
		else
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
			
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public CV updateCv(Long idCv,CV newCv) {
		
		
		Optional<CV> cv= cvrepository.findById(idCv);
		
		if(!(cv.isPresent())) 
			throw new NoContentException("ATTENZIONE: Non è stato trovato alcun Cv per questo id utente");
		else {

		if(!Objects.isNull(newCv.getLanguageList())) cv.get().setLanguageList(newCv.getLanguageList());
		if(!Objects.isNull(newCv.getCertificationList())) cv.get().setCertificationList(newCv.getCertificationList());
		if(!Objects.isNull(newCv.getWorkList())) cv.get().setWorkList(newCv.getWorkList());
		if(!Objects.isNull(newCv.getNotes())) cv.get().setNotes(newCv.getNotes());
		if(!Objects.isNull(newCv.getIdUser())) cv.get().setIdUser(newCv.getIdUser());
		if(!Objects.isNull(newCv.getEducationList()))cv.get().setEducationList(newCv.getEducationList());
		if(Objects.isNull(newCv.getTitle())) cv.get().setTitle(newCv.getTitle());
		return cvrepository.saveAndFlush(cv.get());
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	/***** LANGUAGE****/
	
	@Transactional
	public List<Language> getLanguagesById(Long idCv) {

		Optional<CV> cv=cvrepository.findById(idCv);
		
		if(cv.isPresent())
			return cv.get().getLanguageList();
		else
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");	
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------	
	@Transactional
	public Language setLanguage(Long idCv, Language language) {
		
		Optional<CV> cv= cvrepository.findById(idCv);
		
		
		if(!(cv.isPresent())) 
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
		else {
		
		List<Language> languages = cv.get().getLanguageList();
		languages.add(language);
		cv.get().setLanguageList(languages);
		cvrepository.saveAndFlush(cv.get());
		return language;
		}		
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public Language updateLanguage(Long idCv, Language language){	
		
		Optional<Language> updateLanguage= languageRepository.findById(idCv);
		
		if(updateLanguage.isPresent()) {
			
			if (!Objects.isNull(language.getIdCV())) updateLanguage.get().setIdCV(language.getIdCV());
			if (!Objects.isNull(language.getIdLanguage())) updateLanguage.get().setIdLanguage(language.getIdLanguage());
			if (!Objects.isNull(language.getLevel())) updateLanguage.get().setLevel(language.getLevel());
			if (!Objects.isNull(language.getLanguage())) updateLanguage.get().setLanguage(language.getLanguage());
			languageRepository.saveAndFlush(updateLanguage.get());	
			return updateLanguage.get();
		}
		else
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun risultato");
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	/**** EDUCATION ****/
	
	@Transactional
	public List<Education> getListEducationByIdCv(Long idCv) {

		Optional<CV> cv= cvrepository.findById(idCv);
		
		if(!(cv.isPresent())) 
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
		else {
		  List<Education> listEducation = cv.get().getEducationList();
		  return listEducation;
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------	
	@Transactional
	public void createNewEducation(Long idCv, Education education){
		
		Optional<CV> cv= cvrepository.findById(idCv);
		
		if(!(cv.isPresent())) 
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
		else {
			
			List<Education> list= cv.get().getEducationList();
			list.add(education);
			cv.get().setEducationList(list);
			cvrepository.saveAndFlush(cv.get());
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public void updateEducationById(Long idCv, Education education) {
		
		Optional<Education> updateEducation = educationRepository.findById(idCv);
		
		if(updateEducation.isPresent()) {
			
			if (!Objects.isNull(education.getEndYear())) updateEducation.get().setEndYear(education.getEndYear());
			if (education.getStartYear() != null) updateEducation.get().setStartYear(education.getStartYear());
			if (education.getCourseOfStudy() != null) updateEducation.get().setCourseOfStudy(education.getCourseOfStudy());
			if (!Objects.isNull(education.getIdCV())) updateEducation.get().setIdCV(education.getIdCV());
			if (!Objects.isNull(education.getIdEducation())) updateEducation.get().setIdEducation(education.getIdEducation());
			if (!Objects.isNull(education.getInstitute())) updateEducation.get().setInstitute(education.getInstitute());
			if (!Objects.isNull(education.getTechnologies())) updateEducation.get().setTechnologies(education.getTechnologies());
			if (!Objects.isNull(education.getQualification())) updateEducation.get().setQualification(education.getQualification());
			if (!Objects.isNull(education.getScore())) updateEducation.get().setScore(education.getScore());
			if (!Objects.isNull(education.getScoreMax())) updateEducation.get().setScoreMax(education.getScoreMax());	
			educationRepository.saveAndFlush(updateEducation.get());
		}
		else
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun risultato");
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	/**** WORK ****/

	@Transactional
	public List<Work> getListWorkByUserId(Long idCv) {
		
		Optional<CV> cv= cvrepository.findById(idCv);
		
		if(!(cv.isPresent())) 
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
		else {
			List<Work> list= cv.get().getWorkList();
			return list;
		}	
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public void insertNewWorkForUser(Long idCv, Work work) {
		
		Optional<CV> cv= cvrepository.findById(idCv);
		
		if(!(cv.isPresent())) 
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
		else {
			
			List<Work> list= cv.get().getWorkList();
			list.add(work);
			cv.get().setWorkList(list);
			cvrepository.saveAndFlush(cv.get());
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------	
	@Transactional
	public void updateWorkById(Long idCv, Work work) {
		
		Optional<Work> updateWork = workRepository.findById(idCv);
		
		if(updateWork.isPresent()) {
			
			if (work.getIdWork() != null) updateWork.get().setIdWork(work.getIdWork());
			if (work.getIdCV() != null) updateWork.get().setIdCV(work.getIdCV());
			if (!Objects.isNull(work.getTitle())) updateWork.get().setTitle(work.getTitle());
			if (!Objects.isNull(work.getEmployment())) updateWork.get().setEmployment(work.getEmployment());
			if (!Objects.isNull(work.getCompany())) updateWork.get().setCompany(work.getCompany());
			if (!Objects.isNull(work.getLocation())) updateWork.get().setLocation(work.getLocation());
			if (!Objects.isNull(work.getStartDate())) updateWork.get().setStartDate(work.getStartDate());
			if (!Objects.isNull(work.getEndDate())) updateWork.get().setEndDate(work.getEndDate());
			if (!Objects.isNull(work.getDescription())) updateWork.get().setDescription(work.getDescription());
			if (!Objects.isNull(work.getTechnologies())) updateWork.get().setTechnologies(work.getTechnologies());
			workRepository.saveAndFlush(updateWork.get());
		}
		else
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun risultato");
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------		
	/**** CERTIFICATION ****/
	
	@Transactional
	public List<Certification> getListCertificationByUserId(Long idCv) {
		
		Optional<CV> cv= cvrepository.findById(idCv);
		
		if(!(cv.isPresent())) 
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
		else {
			
			List<Certification> list= cv.get().getCertificationList();
			return list;
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public void insertNewCertificationForUser(Long idCv, Certification certification){
		
		Optional<CV> cv= cvrepository.findById(idCv);
		
		if(!(cv.isPresent())) 
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
		else {
			
			List<Certification> list= cv.get().getCertificationList();
			list.add(certification);
			cv.get().setCertificationList(list);
			cvrepository.saveAndFlush(cv.get());
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public void updateCertificationById(Long id, Certification certification) {

		Optional<Certification> updateCertification = certificationRepository.findById(id);
		
		if(updateCertification.isPresent()) {

		if (!Objects.isNull(certification.getDescription())) updateCertification.get().setDescription(certification.getDescription());
		if (certification.getIdCertification() != null) updateCertification.get().setIdCertification(certification.getIdCertification());
		if (certification.getIdCV() != null) updateCertification.get().setIdCV(certification.getIdCV());
		if (!Objects.isNull(certification.getInstitution())) updateCertification.get().setInstitution(certification.getInstitution());
		if (!Objects.isNull(certification.getRating())) updateCertification.get().setRating(certification.getRating());
		if (!Objects.isNull(certification.getTechnologies())) updateCertification.get().setTechnologies(certification.getTechnologies());
		if (!Objects.isNull(certification.getTitle())) updateCertification.get().setTitle(certification.getTitle());
		certificationRepository.saveAndFlush(updateCertification.get());
		}
		else
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun risultato");
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------
	public boolean addNewCv(Long idUser,CV cv) {
		
		if(cv!=null) {
			cv.setIdUser(idUser);
			cvrepository.saveAndFlush(cv);
			return true;
		}
		else 
			throw new NoContentException("ATTENZIONE: non è possibile aggiungere un Cv vuoto");			
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------
}

