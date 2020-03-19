package it.beije.mgmt.service;


import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import it.beije.mgmt.entity.cv.CV;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import it.beije.mgmt.entity.cv.Formazione;
import it.beije.mgmt.entity.cv.Language;
import it.beije.mgmt.jpa.JpaEntityManager;

public class CvService {

//	public List<CV> findCvByTechnology(String technology) throws Exception {
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		List<CV> curricula = cvRepository.findByTechnology(technology);
//		System.out.println("Nunmber of CV : " + curricula.size());
//		return curricula;
//	}
	public CV findCvById(Long idUser) throws Exception {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		CV curricula= new CV();
		curricula=entityManager.createQuery("select c from cv c where c.id_user = " + idUser, CV.class).getSingleResult();
		entityManager.close();
		return curricula;
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
	
	/***** LANGUAGE****/
	
	public List<Language> getLanguagesById(Long idCv) throws Exception {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		List<Language> lingue= new ArrayList<Language>();
		lingue=entityManager.createQuery("select l from language l where l.id_cv = " + idCv, Language.class).getResultList();
		return lingue;
	}
	public Language setLanguage(Long idCv, Language language) throws Exception {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		CV cv = entityManager.find(CV.class, idCv);
		System.out.println("cv.getLanguageList()?" 
				+ (cv.getLanguageList() != null ? cv.getLanguageList().size() : "NULL"));
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
	
	/**** FORMAZIONE ****/
	// get list of Formazione by idUser
	public List<Formazione> getListFormazioneByIdUser(Long idUser) {
		
		List<Formazione> listFormazione = new ArrayList<Formazione>();
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		
		listFormazione = entityManager.createQuery("select f from Formazione f where f.id_user = " + idUser, Formazione.class).getResultList();
		
		return listFormazione;
	}
	
	// create new Formazione for user specify by idUser
	public void createNewFormazione(Formazione formazione, Long idUser) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		
		Formazione newFormazione = entityManager.find(Formazione.class, idUser);

		entityManager.persist(newFormazione);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	// update Formazione by id_formazione
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
}
