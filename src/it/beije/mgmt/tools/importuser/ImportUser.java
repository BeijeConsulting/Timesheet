package it.beije.mgmt.tools.importuser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.beije.erp.entity.User;

public class ImportUser {

	public static void main(String[] args) {
		
		try {
			
			File f = new File("C:\\temp\\provaTimesheet.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("timesheet");
			EntityManager entityManager = factory.createEntityManager();
			
			StringTokenizer tokenizer=null;
			User user=null;
			tokenizer = new StringTokenizer(br.readLine(), ";");
			LocalDate date = null;
			
			while(br.ready()) {
			tokenizer = new StringTokenizer(br.readLine(), ";");
			user = new User();
			user.setFirstName(tokenizer.nextToken());
			user.setLastName(tokenizer.nextToken());
			user.setEmail(tokenizer.nextToken());
			user.setPassword(tokenizer.nextToken());
			user.setSecondaryEmail(tokenizer.nextToken());
			user.setPhone(tokenizer.nextToken());
			user.setFiscalCode(tokenizer.nextToken());
			date=LocalDate.parse(tokenizer.nextToken());
			Date date1 = Date.valueOf(date);
			user.setBirthDate(date1);
			user.setBirthPlace(tokenizer.nextToken());
			user.setNationality(tokenizer.nextToken());
			user.setDocument(tokenizer.nextToken());
			user.setIdSkype(tokenizer.nextToken());
			user.setAdmin(Boolean.valueOf(tokenizer.nextToken()));
			user.setNote(tokenizer.nextToken());		
			
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			}
			br.close();
				      
			entityManager.close();
			System.out.println("Contatti caricati in DB");
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! File non caricato correttamente!");
				      System.err.println(e.getMessage());
				    }
				  }

	}
