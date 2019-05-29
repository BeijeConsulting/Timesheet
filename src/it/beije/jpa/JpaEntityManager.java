package it.beije.jpa;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManager {
	
	private static EntityManagerFactory emfactory = null;
	
	private JpaEntityManager() {}
	
	public static synchronized EntityManagerFactory getInstance() {
		if (emfactory == null) {
			emfactory = Persistence.createEntityManagerFactory("timesheet");
		}
		
		return emfactory;
	}
	
}
