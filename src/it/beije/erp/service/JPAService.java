package it.beije.erp.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import it.beije.erp.entity.Address;
import it.beije.erp.entity.Computer;
import it.beije.erp.entity.User;
import it.beije.mgmt.jpa.JpaEntityManager;

public class JPAService {

	private static EntityManager entityManager;
	private static final String project = "timesheet";
	
	public static <T> T getBean(Class<T> cls, Object pk) {
		EntityManager entityManager = Persistence.createEntityManagerFactory(project).createEntityManager();
		T obj = entityManager.find(cls, pk);
		entityManager.close();
		return obj;
	}
	

	public static void save(Object obj) {
		EntityManager entityManager = Persistence.createEntityManagerFactory(project).createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(obj);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void modify(Object obj) {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory(project).createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(obj);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	
}
