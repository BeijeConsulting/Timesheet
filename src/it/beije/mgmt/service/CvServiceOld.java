package it.beije.mgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.entity.cv.Certification;
import it.beije.mgmt.entity.cv.Education;
import it.beije.mgmt.entity.cv.Language;
import it.beije.mgmt.entity.cv.Work;

@Service
public class CvServiceOld {

	
	/***** CV *****/
	
	@Transactional
	public CV findCvByUserId(Long idUser) {
		List<CV> cvs = new ArrayList<CV>();

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();

		cvs = entityManager.createQuery("select c from CV c where c.idUser = " + idUser, CV.class).getResultList();

		if(cvs.get(0) != null) {
			return cvs.get(0);
		}
		return null;
	}

	@Transactional
	public CV findCvById(Long idCv) throws Exception {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();
		CV curricula= new CV();
		curricula=entityManager.createQuery("select c from CV c where c.idCv = " + idCv, CV.class).getSingleResult();
		entityManager.close();
		return curricula;
	}

	@Transactional
	public CV updateCv(Long idCv, CV cv) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		CV oldCv = entitymanager.find(CV.class, idCv);
		if (!Objects.isNull(cv.getTitle())) oldCv.setTitle(cv.getTitle());
		if (!Objects.isNull(cv.getEducationList())) oldCv.setEducationList(cv.getEducationList());
		if (!Objects.isNull(cv.getCertificationList())) oldCv.setCertificationList(cv.getCertificationList());
		if (!Objects.isNull(cv.getLanguageList())) oldCv.setLanguageList(cv.getLanguageList());
		if (!Objects.isNull(cv.getIdUser())) oldCv.setIdUser(cv.getIdUser());
		if (!Objects.isNull(cv.getIdCv())) oldCv.setIdCv(cv.getIdCv());
		//if (!Objects.isNull(cv.getTechnology())) oldCv.setTechnology(cv.getTechnology());
		if (!Objects.isNull(cv.getWorkList())) oldCv.setWorkList(cv.getWorkList());
		entitymanager.persist(oldCv);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		
		return oldCv;

	}

	/***** LANGUAGE****/
	
	@Transactional
	public List<Language> getLanguagesById(Long idCv) {

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();
		List<Language> lingue = new ArrayList<Language>();
		lingue = entityManager.createQuery("select l from Language l where l.idCV = " + idCv, Language.class).getResultList();
		entityManager.close();
		return lingue;
	}
	
	@Transactional
	public Language setLanguage(Long idCv, Language language) throws Exception {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();
		CV cv = entityManager.find(CV.class, idCv);
		if (Objects.isNull(language.getIdCV())) {
			language.setIdCV(idCv);
		} else if (language.getIdCV().longValue() != idCv.longValue()) {
			throw new Exception();
		}	
		List<Language> Languages = cv.getLanguageList();
		Languages.add(language);
		cv.setLanguageList(Languages);
		entityManager.persist(language);
		entityManager.getTransaction().commit();
		entityManager.close();
		return language;
	}
	@Transactional
	public Language updateLanguage(Long idCv, Language language) throws Exception{		
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Language updateLanguage = entitymanager.find(Language.class, idCv);
		if (!Objects.isNull(language.getIdCV())) updateLanguage.setIdCV(language.getIdCV());
		if (!Objects.isNull(language.getIdLanguage())) updateLanguage.setIdLanguage(language.getIdLanguage());
		if (!Objects.isNull(language.getLevel())) updateLanguage.setLevel(language.getLevel());
		if (!Objects.isNull(language.getLanguage())) updateLanguage.setLanguage(language.getLanguage());
		entitymanager.persist(updateLanguage);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		return updateLanguage;	
	}

	
	/**** EDUCATION ****/
	// get list of Education by idUser
	@Transactional
	public List<Education> getListEducationByIdCv(Long idCv) {

		List<Education> listEducation = new ArrayList<Education>();

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();

		listEducation = entityManager.createQuery("select e from Education e where e.idCV = " + idCv, Education.class).getResultList();

		return listEducation;
	}

	// create new Education for user specify by idUser
	@Transactional
	public void createNewEducation(Education education, Long idCv) throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();

		CV cv = entityManager.find(CV.class, idCv);

		if (Objects.isNull(education.getIdCV())) {
			education.setIdCV(idCv);
		} else {
			if (education.getIdCV().longValue() != idCv.longValue()) {
				throw new Exception();
			}
		}

		List<Education> educations = cv.getEducationList();


		educations.add(education);
		cv.setEducationList(educations);

		entityManager.persist(education);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	// update Education by id_education
	@Transactional
	public void updateEducationById(Education education, Long idEducation) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Education updateEducation = entitymanager.find(Education.class, idEducation);

		if (!Objects.isNull(education.getEndYear())) updateEducation.setEndYear(education.getEndYear());
		if (education.getStartYear() != null) updateEducation.setStartYear(education.getStartYear());
		if (education.getCourseOfStudy() != null) updateEducation.setCourseOfStudy(education.getCourseOfStudy());
		if (!Objects.isNull(education.getIdCV())) updateEducation.setIdCV(education.getIdCV());
		if (!Objects.isNull(education.getIdEducation())) updateEducation.setIdEducation(education.getIdEducation());
		if (!Objects.isNull(education.getInstitute())) updateEducation.setInstitute(education.getInstitute());
		if (!Objects.isNull(education.getTechnologies())) updateEducation.setTechnologies(education.getTechnologies());
		if (!Objects.isNull(education.getQualification())) updateEducation.setQualification(education.getQualification());
		if (!Objects.isNull(education.getScore())) updateEducation.setScore(education.getScore());
		if (!Objects.isNull(education.getScoreMax())) updateEducation.setScoreMax(education.getScoreMax());

		entitymanager.persist(updateEducation);
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}


	/**** WORK ****/
	// GET Work by User Id
	@Transactional
	public List<Work> getListWorkByUserId(Long idCv) {
		List<Work> listWorks = new ArrayList<Work>();

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();

		listWorks = entityManager.createQuery("select w from Work w where w.idCv = " + idCv, Work.class).getResultList();

		return listWorks;
	}


	// POST Work for user
	@Transactional
	public void insertNewWorkForUser(Long idCv, Work work) throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();

		CV cv = entityManager.find(CV.class, idCv);

		if (Objects.isNull(work.getIdCV())) {
			work.setIdWork(idCv);
		} else {
			if (work.getIdCV().longValue() != idCv.longValue()) {
				throw new Exception();
			}
		}

		List<Work> Works = cv.getWorkList();


		Works.add(work);
		cv.setWorkList(Works);

		entityManager.persist(work);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	// PUT Work by IdCertification
	@Transactional
	public void updateWorkById(Long id, Work work) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Work updateWork = entitymanager.find(Work.class, id);

		if (work.getIdWork() != null) updateWork.setIdWork(work.getIdWork());
		if (work.getIdCV() != null) updateWork.setIdCV(work.getIdCV());
		if (!Objects.isNull(work.getTitle())) updateWork.setTitle(work.getTitle());
		if (!Objects.isNull(work.getEmployment())) updateWork.setEmployment(work.getEmployment());
		if (!Objects.isNull(work.getCompany())) updateWork.setCompany(work.getCompany());
		if (!Objects.isNull(work.getLocation())) updateWork.setLocation(work.getLocation());
		if (!Objects.isNull(work.getStartDate())) updateWork.setStartDate(work.getStartDate());
		if (!Objects.isNull(work.getEndDate())) updateWork.setEndDate(work.getEndDate());
		if (!Objects.isNull(work.getDescription())) updateWork.setDescription(work.getDescription());
		if (!Objects.isNull(work.getTechnologies())) updateWork.setTechnologies(work.getTechnologies());
		entitymanager.persist(updateWork);
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}

		
	/**** CERTIFICATION ****/
	// GET Certification by User Id
	@Transactional
	public List<Certification> getListCertificationByUserId(Long idCv) {
		List<Certification> listCertifications = new ArrayList<Certification>();

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();

		listCertifications = entityManager.createQuery("select c from Certification c where c.idCV = " + idCv, Certification.class).getResultList();

		return listCertifications;
	}

	// POST Certification for user
	@Transactional
	public void insertNewCertificationForUser(Long idCv, Certification certification) throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();

		CV cv = entityManager.find(CV.class, idCv);

		if (Objects.isNull(certification.getIdCV())) {
			certification.setIdCertification(idCv);
		} else {
			if (certification.getIdCV().longValue() != idCv.longValue()) {
				throw new Exception();
			}
		}

		List<Certification> certifications = cv.getCertificationList();


		certifications.add(certification);
		cv.setCertificationList(certifications);

		entityManager.persist(certification);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	// PUT Certification by IdCertification
	@Transactional
	public void updateCertificationById(Long id, Certification certification) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Certification updateCertification = entitymanager.find(Certification.class, id);

		if (!Objects.isNull(certification.getDescription())) updateCertification.setDescription(certification.getDescription());
		if (certification.getIdCertification() != null) updateCertification.setIdCertification(certification.getIdCertification());
		if (certification.getIdCV() != null) updateCertification.setIdCV(certification.getIdCV());
		if (!Objects.isNull(certification.getInstitution())) updateCertification.setInstitution(certification.getInstitution());
		if (!Objects.isNull(certification.getRating())) updateCertification.setRating(certification.getRating());
		if (!Objects.isNull(certification.getTechnologies())) updateCertification.setTechnologies(certification.getTechnologies());
		if (!Objects.isNull(certification.getTitle())) updateCertification.setTitle(certification.getTitle());

		entitymanager.persist(updateCertification);
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}

	
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
