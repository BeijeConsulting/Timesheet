package it.beije.jpa;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import it.beije.formazione.spring.model.User;

public class GestioneUtenti {

		public static void creaUtente(
//				public void creaUtente(
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

	      entitymanager.close( );
	      emfactory.close( );
	}
	
	public void trovaUtente(int id) {
		
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		User utente = entitymanager.find(User.class, id);

		System.out.println("utente ID = " + utente.getId());
		System.out.println("utente Nome = " + utente.getFirstName());
		System.out.println("utente getCognome = " + utente.getLastName());
//		System.out.println("utente Email = " + utente.getEmail());

	}

//	public static void main( String[ ] args ) {
//		
//		GestioneUtenti gestioneUtenti = new GestioneUtenti();
//		gestioneUtenti.creaUtente("xun", "yang","pmail","wmail","phone", "12oakh78Yaothkha",0,"passd");
//		gestioneUtenti.trovaUtente(7);
//		
//	}
}
