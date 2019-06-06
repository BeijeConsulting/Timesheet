package it.beije.erp.timesheet.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import it.beije.erp.timesheet.entity.User;
import it.beije.jpa.JpaEntityManager;


@Service
public class UserService {

	public void create(User user) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		User utente = new User();
		//	      utente.setId(141);
		utente.setFirstName(user.getFirstName());
		utente.setLastName(user.getLastName());
		utente.setPersonalEmail(user.getPersonalEmail());
		utente.setWorkEmail(user.getWorkEmail());
		utente.setPhone(user.getPhone());
		utente.setFiscalCode(user.getFirstName());
		utente.setAdmin(0);
		utente.setPassword(user.getPassword());

		entitymanager.persist(utente);
		entitymanager.getTransaction().commit();

//		entitymanager.close( );
//		emfactory.close( );
	}

}
