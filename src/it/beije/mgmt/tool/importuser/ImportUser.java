package it.beije.mgmt.tool.importuser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.beije.mgmt.entity.User;

public class ImportUser {

	static Logger log = LoggerFactory.getLogger(ImportUser.class);

	public static void main(String[] args) {
		
		
		try {
			
			File f = new File("C:\\temp\\provaTimesheet.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("timesheetDB");
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
			log.debug("Contatti caricati in DB");
				    }
				    catch (Exception e)
				    {
				      log.error("Got an exception! File non caricato correttamente!");
				      log.error(e.getMessage());
				    }
				  }

	}
