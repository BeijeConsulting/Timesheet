package it.beije.erp.timesheet.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.beije.erp.entity.User;
import it.beije.erp.repositories.UserRepository;
import it.beije.erp.timesheet.entity.CustomUserDetail;
import it.beije.jpa.JpaEntityManager;
import it.beije.jpa.UserRequest;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	public User find(Long id) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User user;
		try {
			user = entitymanager.createQuery("SELECT u FROM User u WHERE u.id = "+id,User.class).getSingleResult();
		}catch (NoResultException e)
		{
			return new User();
		}
		System.out.println("trovato" + user.getFirstName());
		
		entitymanager.close();

		return user;
	}
	
	public User create(User user) {
		//EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("timesheet");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(user);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		
		return user;
	}
	
	public User update(Long id, User userData) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		User user = entitymanager.find(User.class, id);
    	
    	if (userData.getFirstName() != null) user.setFirstName(userData.getFirstName());
    	if (userData.getLastName() != null) user.setLastName(userData.getLastName());
    	if (userData.getEmail() != null) user.setEmail(userData.getEmail());
    	if (userData.getSecondaryEmail() != null) user.setSecondaryEmail(userData.getSecondaryEmail());
    	if (userData.getPhone() != null) user.setPhone(userData.getPhone());
    	if (userData.getFiscalCode() != null) user.setFiscalCode(userData.getFiscalCode());
    	if (userData.getPassword() != null) user.setPassword(userData.getPassword());
    	if (userData.getArchiveDate() != null) user.setArchiveDate(userData.getArchiveDate());

		entitymanager.persist(user);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		
		return user;
	}
	
	public List<User> trovaUtente(UserRequest req) {
		// TODO Auto-generated method stub
		return trovaUtente(req.getFirst_name(),req.getLast_name(),req.getEmail(),req.getFiscal_code());
	}
	
	public List<User> trovaUtente(String firstName, String lastName, String email, String fiscalCode) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		
		List<String> searchQuery=new ArrayList<>();
		String whereClause="";
		
		if (firstName != null && firstName.length()>0) {
			searchQuery.add("a.firstName LIKE '%"+firstName+"%'");
			whereClause+="WHERE ";
		}
		if (lastName != null && lastName.length()>0) {
			searchQuery.add("a.lastName LIKE '%"+lastName+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		if (email != null && email.length()>0) {
			searchQuery.add("a.email LIKE '%"+email+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		if (fiscalCode != null && fiscalCode.length()>0) {
			searchQuery.add("a.fiscalCode='"+fiscalCode+"'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		
		System.out.println("Sto cercando");
		
		for (int i=0;i<searchQuery.size();i++) {
			whereClause+=searchQuery.get(i);
			if (i!=searchQuery.size()-1)
				whereClause+=" AND ";
		}
		
		TypedQuery<User> query=entitymanager.createQuery("SELECT a from User a "+whereClause,User.class);
		
		List<User> userlist=query.getResultList();
		

		return userlist;
	}
	
	public List<User> caricaTutti() {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		Query q = entitymanager.createNativeQuery("SELECT * FROM user");

		List<User> utenti = q.getResultList();

		entitymanager.close();
		
		System.out.println("caricaTutti : " + utenti.size());
		
		return utenti;
	}
	
	public void modificaUtente(User user) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		//		System.out.println("sono nel metodo modificaUtente");
		String modifica="UPDATE User a SET";

		modifica += " 	a.firstName= '" +user.getFirstName();
		modifica += "' , a.lastName= '" + user.getLastName();
		modifica += "' , a.email= '" + user.getEmail();
		modifica += "' , a.secondaryEmail= '" +user.getSecondaryEmail();
		modifica += "' , a.phone= '" +user.getPhone();
		modifica += "' , a.fiscalCode= '" + user.getFiscalCode();

		modifica += "' , a.password= '" +user.getPassword();
		modifica += "' , a.birthDate= '" +user.getBirthDate();
		modifica += "' , a.birthPlace= '" + user.getBirthPlace();
		modifica += "' , a.nationality= '" +user.getNationality();
		modifica += "' , a.document= '" +user.getDocument();
		modifica += "' , a.idSkype= '" + user.getIdSkype();
		modifica += "' , a.note= '" +user.getNote() +"'";

		modifica += " WHERE a.id= "+user.getId();
		//		modifica += " WHERE a.id=  10";
		Query q = entitymanager.createQuery(modifica);
		int rowsUpdated = q.executeUpdate();	
		//System.out.println(rowsUpdated);

		entitymanager.getTransaction().commit();

	}

	public void archiviaUtente(User user) {

		LocalDate data = LocalDate.now();
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		System.out.println("sono nel metodo archiviaUtente");
		String modifica="UPDATE User a SET";

		modifica += " a.archiveDate= '" + data + "' ";

		modifica += " WHERE a.id= "+user.getId();

		System.out.println(modifica);

		Query q = entitymanager.createQuery(modifica);
		int rowsUpdated = q.executeUpdate();

		//System.out.println(rowsUpdated);
		entitymanager.getTransaction().commit();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Optional<User> user = userRepository.findByEmail(username);
			user.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
			return user.map(CustomUserDetail::new).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}		
	}

}
