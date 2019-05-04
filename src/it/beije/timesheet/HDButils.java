package it.beije.timesheet;

import java.util.Iterator;
import java.util.List;

import org.geolatte.geom.M;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import it.beije.timesheet.entities.User;
import it.beije.timesheet.entities.Utente;
import it.beije.timesheet.entities.methods.UserMethods;



public class HDButils {
	
//	public static Connection getConnection() throws ClassNotFoundException, SQLException {
//		Connection conn = null;
//		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//		
//		return conn;
//	}

	public static Session getSession() throws Exception {
		
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Utente.class)
				.buildSessionFactory();
		
		System.out.println("is open?" + factory.isOpen());
		
		return factory.openSession();
		
	}


	public static void main(String argv[]) throws Exception {
		
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Utente.class)
				.buildSessionFactory();
		
		System.out.println("is open?" + factory.isOpen());
		
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
//		String hql = "SELECT id FROM Utente";
//		Query query = session.createQuery(hql);
//		for (Object u : query.list()) {
//			Long utente = (Long) u;
//			System.out.println(utente);
//		}
		
		Criteria criteria = session.createCriteria(Utente.class);
		List utenti = criteria.list();//.add(Restrictions.eq("nome", "pippo"))

		Iterator itr = utenti.iterator();
		while (itr.hasNext()) {
//			Transaction transaction = session.beginTransaction();
			Utente u = (Utente) itr.next();
			System.out.println(u.getId());
			System.out.println(u.getCognome());		
			
//			StringBuilder b = new StringBuilder();
//			for (int i = 0; i < (u.getId()*5); i++) {
//				b.append('c');
//			}
//			u.setNome(b.toString());
//			System.out.println("new name : " + b.toString());
//			transaction.commit();
		}
		
		
		//System.out.println(query.list());


		//transaction.commit();
		session.close();
		factory.close();
		System.out.println("is open?" + factory.isOpen());
	}
	
	
}