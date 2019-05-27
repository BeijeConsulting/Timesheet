package it.beije.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import it.beije.web.bean.User;


public class HDButils {
	
	private static SessionFactory factory = null;
	
	public static Session getSession() throws IOException {
		
		if (factory == null) {
			factory = new Configuration().configure()
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
		}
		
		System.out.println("is open?" + factory.isOpen());
		
		return factory.openSession();
		
	}
	
}