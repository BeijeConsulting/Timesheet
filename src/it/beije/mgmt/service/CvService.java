package it.beije.mgmt.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.entity.cv.Certification;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import it.beije.mgmt.entity.cv.Formazione;
import it.beije.mgmt.entity.cv.Work;
import it.beije.mgmt.jpa.JpaEntityManager;

@Service
public class CvService {

	//	public List<CV> findCvByTechnology(String technology) throws Exception {
	//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
	//		EntityManager entitymanager = emfactory.createEntityManager();
	//		List<CV> curricula = cvRepository.findByTechnology(technology);
	//		System.out.println("Nunmber of CV : " + curricula.size());
	//		return curricula;
	//	}
	public List<CV> findCvById(Long idUser) throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		return entityManager.createQuery("select c from cv c where c.id_user = " + idUser, CV.class).getResultList();
	}

	@Transactional
	public CV updateTitle(Long id, String title) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		CV updateCv = entitymanager.find(CV.class, id);
		if (!Objects.isNull(title)) updateCv.setTitle(title);
		entitymanager.persist(updateCv);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		return updateCv;

	}



	/**** FORMAZIONE ****/
	// get list of Formazione by idUser
	@Transactional
	public List<Formazione> getListFormazioneByIdCv(Long idCv) {

		List<Formazione> listFormazione = new ArrayList<Formazione>();

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();

		listFormazione = entityManager.createQuery("select f from Formazione f where f.id_cv = " + idCv, Formazione.class).getResultList();

		return listFormazione;
	}

	// create new Formazione for user specify by idUser
	@Transactional
	public void createNewFormazione(Formazione formazione, Long idCv) throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();

		CV cv = entityManager.find(CV.class, idCv);

		if (Objects.isNull(formazione.getIdCV())) {
			formazione.setIdCV(idCv);
		} else {
			if (formazione.getIdCV().longValue() != idCv.longValue()) {
				throw new Exception();
			}
		}

		List<Formazione> formazioni = cv.getFormazioneList();


		formazioni.add(formazione);
		cv.setFormazioneList(formazioni);

		entityManager.persist(formazione);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	// update Formazione by id_formazione
	@Transactional
	public void updateFormazioneById(Formazione formazione, Long idFormazione) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Formazione updateFormazione = entitymanager.find(Formazione.class, idFormazione);

		if (!Objects.isNull(formazione.getAnnoDiFine())) updateFormazione.setAnnoDiFine(formazione.getAnnoDiFine());
		if (formazione.getAnnoDiInizio() != null) updateFormazione.setAnnoDiInizio(formazione.getAnnoDiInizio());
		if (formazione.getCorsoDiStudi() != null) updateFormazione.setCorsoDiStudi(formazione.getCorsoDiStudi());
		if (!Objects.isNull(formazione.getIdCV())) updateFormazione.setIdCV(formazione.getIdCV());
		if (!Objects.isNull(formazione.getIdFormazione())) updateFormazione.setIdFormazione(formazione.getIdFormazione());
		if (!Objects.isNull(formazione.getIstituto())) updateFormazione.setIstituto(formazione.getIstituto());
		if (!Objects.isNull(formazione.getTecnologie())) updateFormazione.setTecnologie(formazione.getTecnologie());
		if (!Objects.isNull(formazione.getTitoloDiStudio())) updateFormazione.setTitoloDiStudio(formazione.getTitoloDiStudio());
		if (!Objects.isNull(formazione.getValutazione())) updateFormazione.setValutazione(formazione.getValutazione());
		if (!Objects.isNull(formazione.getValutazioneMax())) updateFormazione.setValutazioneMax(formazione.getValutazioneMax());

		entitymanager.persist(updateFormazione);
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}


	/**** WORK ****/
	// GET Work by User Id
	@Transactional
	public List<Work> getListWorkByUserId(Long idCv) {
		List<Work> listWorks = new ArrayList<Work>();

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();

		listWorks = entityManager.createQuery("select c from Certification c where c.id_cv = " + idCv, Work.class).getResultList();

		return listWorks;
	}


	// POST Work for user
	@Transactional
	public void insertNewWorkForUser(Long idCv, Work work) throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
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

		if (work.getTitle() != null) updateWork.setTitle(work.getTitle());
		if (work.getEmployment() != null) updateWork.setEmployment(work.getEmployment());
		if (work.getCompany() != null) updateWork.setCompany(work.getCompany());
		if (work.getLocation() != null) updateWork.setLocation(work.getLocation());
		if (work.getStartDate() != null) updateWork.setStartDate(work.getStartDate());
		if (work.getEndDate() != null) updateWork.setEndDate(work.getEndDate());
		if (work.getDescription() != null) updateWork.setDescription(work.getDescription());
		if (work.getTechnologies() != null) updateWork.setTechnologies(work.getTechnologies());
		entitymanager.persist(work);
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}

		
	/**** CERTIFICATION ****/
	// GET Certification by User Id
	@Transactional
	public List<Certification> getListCertificationByUserId(Long idCv) {
		List<Certification> listCertifications = new ArrayList<Certification>();

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();

		listCertifications = entityManager.createQuery("select c from Certification c where c.ic_cv = " + idCv, Certification.class).getResultList();

		return listCertifications;
	}

	// POST Certification for user
	@Transactional
	public void insertNewCertificationForUser(Long idCv, Certification certification) throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
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
}
