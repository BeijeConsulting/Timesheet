package it.beije.timesheet.entities;


import javax.persistence.*;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.Criteria;



@Entity
@Table(name = "user")
public class User {
	
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "personal_email")
	private String personal_email;
	
	@Column(name = "work_email")
	private String work_email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "fiscal_code")
	private String fiscal_code;
	
	@Column(name = "admin")
	private Boolean admin;
	
	@Column(name = "password")
	private String password;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	
	public String getPersonal_email() {
		return personal_email;
	}
	public void setPersonal_email(String personal_email) {
		this.personal_email = personal_email;
	}
	
	
	public String getWork_email() {
		return work_email;
	}
	public void setWork_email(String work_email) {
		this.work_email = work_email;
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getFiscal_code() {
		return fiscal_code;
	}
	public void setFiscal_code(String fiscal_code) {
		this.fiscal_code = fiscal_code;
	}
	
	
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Get User By ID
	public User getUser(int id) throws Exception {
		
		SessionFactory factory = User.getFactory();
		Session session = factory.openSession();
		
	    User user = null;
	    try {
	        user = session.get(User.class, id);
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
	public User getUser(String fiscalCode) throws Exception {
		
		SessionFactory factory = User.getFactory();
		Session session = factory.openSession();
			
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("fiscal_code", fiscalCode));

		List<User> user = null;
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
	    return user.get(0);
	}
	
//	//Get Users By firstName and lastName
//	public List<User> getUsers(String firstName, String lastName) throws Exception {
//		
//		SessionFactory factory = User.getFactory();
//		Session session = factory.openSession();
//		
//		Criteria criteria = session.createCriteria(User.class);
//		criteria.add(Restrictions.eq("first_name", firstName)).add(Restrictions.eq("last_name", lastName));
//
//		List<User> user = null;
//	    try {
//	        user = criteria.list();
//	        Hibernate.initialize(user);
//	    } catch (Exception e) {
//	       e.printStackTrace();
//	    } finally {
//	        if (session != null && session.isOpen()) {
//	            session.close();
//	            factory.close();
//	        }
//	    }
//	    return user;
//	}
	
	//Get Users By firstName or lastName or personalEmail or workEmail
	public List<User> getUsers(String firstName, String lastName, String personalEmail, String workEmail) throws Exception {
		
		SessionFactory factory = User.getFactory();
		Session session = factory.openSession();
		
		Criteria criteria = session.createCriteria(User.class);
		Criterion crt1 = Restrictions.eq("first_name", firstName);
		Criterion crt2 = Restrictions.eq("last_name", lastName);
		Criterion crt3 = Restrictions.eq("personalEmail", personalEmail);
		Criterion crt4 = Restrictions.eq("workEmail", workEmail);
		criteria.add(Restrictions.or(crt1, crt2, crt3, crt4));

		List<User> user = null;
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
	    return user;
	}
	
	
	//CONNESSIONE AL FACTORY
	public static SessionFactory getFactory() throws Exception {

		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
				
		return factory;
	}
	
}
	
