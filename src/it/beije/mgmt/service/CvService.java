package it.beije.mgmt.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.entity.cv.Certification;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.beije.mgmt.entity.cv.Education;
import it.beije.mgmt.entity.cv.Work;
import it.beije.mgmt.entity.cv.Language;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.repository.CvRepository;
import it.beije.mgmt.restcontroller.exception.NoContentException;

@Service
public class CvService {
	
	@Autowired
	private static CvRepository cvrepository;

	
	/***** CV *****/
	
	@Transactional
	public CV findCvByUserId(Long idUser) {
		CV cv = new CV();


		cv=cvrepository.findByUserId(idUser);
		if(cv==null) 
			throw new NoContentException("ATTENZIONE: Non è stato trovato alcun Cv per questo id utente");
		else
			return cv;
		}
		

	@Transactional
	public CV findCvById(Long idCv) {
		
		Optional<CV> cv;
		cv= cvrepository.findById(idCv);
		if(cv.isPresent())
			
			return cv.get();
		else
			throw new NoContentException("ATTENZIONE: non è stato trovato alcun Cv");
			
	}

	@Transactional
	public CV updateCv(Long idCv,CV newCv) {
		
		CV cv= findCvById(idCv);
		
		if(!Objects.isNull(newCv.getLanguageList())) cv.setLanguageList(newCv.getLanguageList());
		if(!Objects.isNull(newCv.getCertificationList())) cv.setCertificationList(newCv.getCertificationList());
		if(!Objects.isNull(newCv.getWorkList())) cv.setWorkList(newCv.getWorkList());
		if(!Objects.isNull(newCv.getNotes())) cv.setNotes(newCv.getNotes());
		if(!Objects.isNull(newCv.getIdUser())) cv.setIdUser(newCv.getIdUser());
		if(!Objects.isNull(newCv.getEducationList()))cv.setEducationList(newCv.getEducationList());
		if(Objects.isNull(newCv.getTitle())) cv.setTitle(newCv.getTitle());
			
		return cvrepository.saveAndFlush(cv);
	}

	/***** LANGUAGE****/
	
	@Transactional
	public List<Language> getLanguagesById(Long idCv) {
		CV cv= new CV();
		cvrepository.findById(idCv);
		return cv.getLanguageList();
	}
	
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

//DA FARE DOMANI XK PROBABILMENTE VA CREATO REPOSITORY
//	@Transactional
//	public Language updateLanguage(Long idCv, Language language){	
//	
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.getTransaction().begin();
//		Language updateLanguage = entitymanager.find(Language.class, idCv);
//		if (!Objects.isNull(language.getIdCV())) updateLanguage.setIdCV(language.getIdCV());
//		if (!Objects.isNull(language.getIdLanguage())) updateLanguage.setIdLanguage(language.getIdLanguage());
//		if (!Objects.isNull(language.getLevel())) updateLanguage.setLevel(language.getLevel());
//		if (!Objects.isNull(language.getLanguage())) updateLanguage.setLanguage(language.getLanguage());
//		entitymanager.persist(updateLanguage);
//		entitymanager.getTransaction().commit();
//		entitymanager.close();
//		return updateLanguage;	
//	}

	
	/**** EDUCATION ****/
	// get list of Education by idUser
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

	// create new Education for user specify by idUser
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

	
//	//DA FARE DOMANI XK PROBABILMENTE VA CREATO REPOSITORY
//	// update Education by id_education
//	@Transactional
//	public void updateEducationById(Long idEducation, Education education) {
//		
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.getTransaction().begin();
//		Education updateEducation = entitymanager.find(Education.class, idEducation);
//		if (!Objects.isNull(education.getEndYear())) updateEducation.setEndYear(education.getEndYear());
//		if (education.getStartYear() != null) updateEducation.setStartYear(education.getStartYear());
//		if (education.getCourseOfStudy() != null) updateEducation.setCourseOfStudy(education.getCourseOfStudy());
//		if (!Objects.isNull(education.getIdCV())) updateEducation.setIdCV(education.getIdCV());
//		if (!Objects.isNull(education.getIdEducation())) updateEducation.setIdEducation(education.getIdEducation());
//		if (!Objects.isNull(education.getInstitute())) updateEducation.setInstitute(education.getInstitute());
//		if (!Objects.isNull(education.getTechnologies())) updateEducation.setTechnologies(education.getTechnologies());
//		if (!Objects.isNull(education.getQualification())) updateEducation.setQualification(education.getQualification());
//		if (!Objects.isNull(education.getScore())) updateEducation.setScore(education.getScore());
//		if (!Objects.isNull(education.getScoreMax())) updateEducation.setScoreMax(education.getScoreMax());
//		entitymanager.persist(updateEducation);
//		entitymanager.getTransaction().commit();
//		entitymanager.close();
//	}


	/**** WORK ****/
	// GET Work by User Id
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


	// POST Work for user
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
	
	//DA FARE DOMANI XK PROBABILMENTE VA CREATO REPOSITORY
//	// PUT Work by Id
//	@Transactional
//	public void updateWorkById(Long id, Work work) {
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//
//		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.getTransaction().begin();
//
//		Work updateWork = entitymanager.find(Work.class, id);
//
//		if (work.getIdWork() != null) updateWork.setIdWork(work.getIdWork());
//		if (work.getIdCV() != null) updateWork.setIdCV(work.getIdCV());
//		if (!Objects.isNull(work.getTitle())) updateWork.setTitle(work.getTitle());
//		if (!Objects.isNull(work.getEmployment())) updateWork.setEmployment(work.getEmployment());
//		if (!Objects.isNull(work.getCompany())) updateWork.setCompany(work.getCompany());
//		if (!Objects.isNull(work.getLocation())) updateWork.setLocation(work.getLocation());
//		if (!Objects.isNull(work.getStartDate())) updateWork.setStartDate(work.getStartDate());
//		if (!Objects.isNull(work.getEndDate())) updateWork.setEndDate(work.getEndDate());
//		if (!Objects.isNull(work.getDescription())) updateWork.setDescription(work.getDescription());
//		if (!Objects.isNull(work.getTechnologies())) updateWork.setTechnologies(work.getTechnologies());
//		entitymanager.persist(updateWork);
//		entitymanager.getTransaction().commit();
//		entitymanager.close();
//	}

		
	/**** CERTIFICATION ****/
	// GET Certification by User Id
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

	// POST Certification for user
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

	//DA FARE DOMANI XK VA CREATO PROBABILMENTE REPOSITORY
//	// PUT Certification by IdCertification
//	@Transactional
//	public void updateCertificationById(Long id, Certification certification) {
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//
//		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.getTransaction().begin();
//
//		Certification updateCertification = entitymanager.find(Certification.class, id);
//
//		if (!Objects.isNull(certification.getDescription())) updateCertification.setDescription(certification.getDescription());
//		if (certification.getIdCertification() != null) updateCertification.setIdCertification(certification.getIdCertification());
//		if (certification.getIdCV() != null) updateCertification.setIdCV(certification.getIdCV());
//		if (!Objects.isNull(certification.getInstitution())) updateCertification.setInstitution(certification.getInstitution());
//		if (!Objects.isNull(certification.getRating())) updateCertification.setRating(certification.getRating());
//		if (!Objects.isNull(certification.getTechnologies())) updateCertification.setTechnologies(certification.getTechnologies());
//		if (!Objects.isNull(certification.getTitle())) updateCertification.setTitle(certification.getTitle());
//
//		entitymanager.persist(updateCertification);
//		entitymanager.getTransaction().commit();
//		entitymanager.close();
//	}

	
	// NON funzionante!!!!
	public void addNewCv(Long idUser, CV cv) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();

		cv.setIdUser(idUser);
		entityManager.persist(cv);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}

