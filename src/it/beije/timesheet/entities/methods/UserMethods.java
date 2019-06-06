package it.beije.timesheet.entities.methods;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import it.beije.erp.timesheet.entity.UserT;

public class UserMethods {

	//Get User By ID
	public static UserT getUser(int id) throws Exception {
		
		SessionFactory factory = UserMethods.getFactory();
		Session session = factory.openSession();
		
	    UserT user = null;
	    try {
	        user = session.get(UserT.class, id);
	        Hibernate.initialize(user);
	    } catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	            factory.close();
	        }
	    }
	    return user;
	}
	
	//Get User By FiscalCode
	public static UserT getUser(String fiscalCode) throws Exception {
		
		SessionFactory factory = UserMethods.getFactory();
		Session session = factory.openSession();
			
		Criteria criteria = session.createCriteria(UserT.class);
		criteria.add(Restrictions.eq("fiscal_code", fiscalCode));

		List<UserT> user = null;
	    try {
	        user = criteria.list();
	        Hibernate.initialize(user);
	    } catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	            factory.close();
	        }
	    }

	    if(user.size()>0) {
	    return user.get(0);
	    }
	    
	    return null;
	}
	
	//Get Users By firstName
	public static List<UserT> getUsersByFirstName(String firstName) throws Exception {	
		return getUsers(firstName, null, null, null);
	}
	
	//Get Users By lastName
	public static List<UserT> getUsersByLastName(String lastName) throws Exception {	
		return getUsers(null, lastName, null, null);
	}
	
	//Get Users By firstName and lastName
	public static List<UserT> getUsers(String firstName, String lastName) throws Exception {	
		return getUsers(firstName, lastName, null, null);
	}
	
	//Get Users By firstName and lastName and personalEmail
	public static List<UserT> getUsers(String firstName, String lastName, String personalEmail) throws Exception {	
		return getUsers(firstName, lastName, personalEmail, null);
	}
	
	//Get Users and firstName and lastName and personalEmail and workEmail
	public static List<UserT> getUsers(String firstName, String lastName, String personalEmail, String workEmail) throws Exception {
		
		SessionFactory factory = UserMethods.getFactory();
		Session session = factory.openSession();
		
		Criteria criteria = session.createCriteria(UserT.class);
		
		if(firstName != null && firstName.length()>0) {
			criteria.add(Restrictions.eq("first_name", firstName));
		}
		
		if(lastName != null && lastName.length()>0) {
			criteria.add(Restrictions.eq("last_name", lastName));
		}
		
		if(personalEmail != null && personalEmail.length()>0) {
			criteria.add(Restrictions.eq("personalEmail", personalEmail));
		}
		
		if(workEmail != null && workEmail.length()>0) {
			criteria.add(Restrictions.eq("workEmail", workEmail));
		}

		List<UserT> user = null;

	    user = criteria.list();
	    
	    if (session != null && session.isOpen()) {
	    	session.close();
	        factory.close();
	        }

	    return user;
	}

	//CONNESSIONE AL FACTORY
	private static SessionFactory getFactory() throws Exception {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(UserT.class)
				.buildSessionFactory();		
		return factory;
	}
	
} //END CLASS
