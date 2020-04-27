//package it.beije.mgmt.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//import org.springframework.stereotype.Repository;
//
//import it.beije.mgmt.JpaEntityManager;
//import it.beije.mgmt.entity.User;
//import it.beije.mgmt.exception.DBException;
//
//
//@Repository
//public class UserRepositoryCustom {
//	
//	public List<User> load() {
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		try {
//		List<User> users = entitymanager.createQuery("SELECT u FROM User u", User.class).getResultList();
//		return users;
//		}catch(Exception e) {
//			throw new DBException("Non è stato possibile caricare la lista degli utenti");
//		}finally {
//			entitymanager.close();
//		}
//	}
//
//}
