package it.beije.timesheet;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HDButils {
	
	//CONNESSIONE AL FACTORY
	public static SessionFactory getFactory(Class<?> className) throws Exception {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(className)
				.buildSessionFactory();		
		return factory;
	}
	
}