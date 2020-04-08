package it.beije.mgmt.jpa;


import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import it.beije.mgmt.exception.DBException;

@Component
public class JpaEntityManager {
	
	private static EntityManagerFactory emfactory = null;
	
	static {
		emfactory = Persistence.createEntityManagerFactory("timesheetDB");
	}
	
	private JpaEntityManager() {}
	
	@Bean(name = "entityManagerFactory")	
	public static synchronized EntityManagerFactory getInstance() {
		try {
			if (emfactory == null) {
				emfactory = Persistence.createEntityManagerFactory("timesheetDB");
			}
			return emfactory;
		}catch(Exception e) {
			throw new DBException("Errore nel caricamento del database");
		}
	}
}
