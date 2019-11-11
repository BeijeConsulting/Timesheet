package it.beije.erp.timesheet.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.beije.erp.entity.User;
import it.beije.erp.timesheet.entity.CustomUserDetail;
import it.beije.jpa.JpaEntityManager;
import it.beije.timesheet.repositories.UserRepository;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	public User find(int id) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		User user = entitymanager.find(User.class, id);

//		System.out.println("trovato" + user.getFirstName());
		
		entitymanager.close();

		return user;
	}
	
	public User create(User user) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(user);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		
		return user;
	}
	
	public User update(int id, User userData) {
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
	
	public String trovaUtente(String firstName, String lastName) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		String ricerca =" WHERE ";

		//se null o vuoto non metto condizione WHERE
		if((firstName  == null || firstName.length() == 0 )
				&&( lastName  == null || lastName.length() == 0)) {
			ricerca ="";

		} else {

			//Condizione WHERE
			if(firstName.length() > 0 && lastName.length() > 0) {
				ricerca += "a.first_name = '" +firstName + "'";
				ricerca += " AND ";
				ricerca += "a.last_name = '" +lastName + "'";
			}else {
				if(firstName.length() > 0)
					ricerca += "a.first_name = '" +firstName + "'";
				if(lastName.length() > 0)
					ricerca += "a.last_name = '" +lastName + "'";
			}

		}
		System.out.println("Sto cercando");
		//OK
		//		Query q = entitymanager.createNativeQuery("SELECT a.first_name, a.last_name FROM user a");
		//		Query q = entitymanager.createNativeQuery("SELECT a.id, a.first_name, a.last_name FROM user a"
		//				+ " WHERE a.first_name = " + "'"+ firstName +"'");

		Query q = entitymanager.createNativeQuery("SELECT * FROM user a"
				+ ricerca);

		List<Object[]> utenti = q.getResultList();

		String trovati ="";
		//		System.out.println("Utenti trovati: ");
		
		for (Object[] u : utenti) {
			
			trovati += "<b>ID:</b> " + u[0]  + " " +	           
					"<b>Nome:</b> "+ u[1] + " " +	  
					"<b>Cognome:</b> " +u[2] + " " +	
					"<b>Codice Fiscale:</b>" +u[6] + " " ;
			
			if (u[9] == null) {
			trovati +=	"<b>Utente attivo</b> "  + "<br><br>";
			}
			
			else trovati +=	"<b>Archiviato in data:</b> " +u[9].toString().substring(0, 10) + "<br><br>";
					
		}
		System.out.println(trovati);
		if(trovati.length()==0)
			trovati = "<b>Ops! Nessun utente trovato con questi parametri.<b>";

		return trovati;
	}
	
	public List<User> caricaTutti() {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();

		Query q = entitymanager.createQuery("SELECT u FROM User u");

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
