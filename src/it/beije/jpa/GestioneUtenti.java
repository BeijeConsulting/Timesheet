package it.beije.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import it.beije.formazione.spring.model.User;

public class GestioneUtenti {

	public static void creaUtente(

			String firstName,
			String lastName,
			String personalEmail,
			String workEmail,
			String phone,
			String fiscalCode,
			int admin,
			String pass

			)
	{
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		User utente = new User();
		//	      utente.setId(141);
		utente.setFirstName(firstName);
		utente.setLastName(lastName);
		utente.setPersonalEmail(personalEmail);
		utente.setWorkEmail(workEmail);
		utente.setPhone(phone);
		utente.setFiscalCode(fiscalCode);
		utente.setAdmin(0);
		utente.setPassword(pass);

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

}