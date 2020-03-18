package it.beije.mgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import it.beije.mgmt.entity.cv.Formazione;
import it.beije.mgmt.jpa.JpaEntityManager;

@Service
public class CvService {
	
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
