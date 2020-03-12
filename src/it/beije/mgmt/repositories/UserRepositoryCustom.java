package it.beije.mgmt.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Repository;

import it.beije.erp.entity.User;
import it.beije.mgmt.jpa.JpaEntityManager;


@Repository
public class UserRepositoryCustom {
	
	public List<User> load() {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		List<User> users = entitymanager.createQuery("SELECT u FROM User u", User.class).getResultList();

		entitymanager.close();
		
		return users;
	}

}
