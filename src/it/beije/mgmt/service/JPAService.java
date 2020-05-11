package it.beije.mgmt.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAService {

	private static EntityManager entityManager;
	private static final String project = "timesheetDB";
	
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
