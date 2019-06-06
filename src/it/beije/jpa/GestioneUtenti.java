package it.beije.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import it.beije.erp.timesheet.entity.User;

public class GestioneUtenti {

	public static void creaUtente(User user) {
	 EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		User utente = new User();
		//	      utente.setId(141);
		utente.setFirstName(user.getFirstName());
		utente.setLastName(user.getLastName());
		utente.setPersonalEmail(user.getPersonalEmail());
		utente.setWorkEmail(user.getWorkEmail());
		utente.setPhone(user.getPhone());
		utente.setFiscalCode(user.getFirstName());
		utente.setAdmin(0);
		utente.setPassword(user.getPassword());

		entitymanager.persist(utente);
		entitymanager.getTransaction().commit();

//		entitymanager.close( );
//		emfactory.close( );
	}

	public static String trovaUtente(String firstName, String lastName) {

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
//			System.out.println(
//					"ID: " + u[0]  + " " +	           
//							"Nome: "+ u[1] + " " +	  
//							"Cognome: " +u[2] + " " +	
//							"Codice Fiscale: " +u[6]			
//					);
			trovati += "<b>ID:</b> " + u[0]  + " " +	           
					"<b>Nome:</b> "+ u[1] + " " +	  
					"<b>Cognome:</b> " +u[2] + " " +	
					"<b>Codice Fiscale:</b> " +u[6] + "<br><br>";
//			System.out.println(trovati);

		}
		
		if(trovati.length()==0)
			trovati = "<b>Ops! Nessun utente trovato con questi parametri.<b>";

		return trovati;
	}


	//	public static void trovaUtente(String firstName, String lastName) {
	//		
	//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
	//		EntityManager entitymanager = emfactory.createEntityManager();
	//			
	//		
	//		User utente = entitymanager.find(User.class, firstName);
	//
	//		System.out.println("utente ID = " + utente.getId());
	//		System.out.println("utente Nome = " + utente.getFirstName());
	//		System.out.println("utente getCognome = " + utente.getLastName());
	//
	//	}

	
	public static User trovaID(int id) {
		
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		
		User user = entitymanager.find(User.class, id);

		System.out.println("trovato" + user.getFirstName());

		return user;
	}
	
	
public static void modificaUtente(User user) {


		
		
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		System.out.println("sono nel metodo modificaUtente");
		String modifica="UPDATE User a SET";

		modifica += " a.firstName= '" +user.getFirstName();
		modifica += "' , a.lastName= '" + user.getLastName();
		modifica += "' , a.personalEmail= '" +user.getPersonalEmail();
		modifica += "' , a.workEmail= '" + user.getWorkEmail();
		modifica += "' , a.phone= '" +user.getPhone();
		modifica += "' , a.fiscalCode= '" + user.getFiscalCode();
		modifica += "' , a.password= '" +user.getPassword() +"'";
		
		modifica += " WHERE a.id= "+user.getId();
//		modifica += " WHERE a.id=  10";
		Query q = entitymanager.createQuery(modifica);
		int rowsUpdated = q.executeUpdate();
		
		
		//System.out.println(rowsUpdated);

		entitymanager.getTransaction().commit();


	}
	
}
