
package it.beije.mgmt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.entity.Computer;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.DBException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.repository.BankCredentialsRepository;


@Service
public class BankCredentialsService {
	
	@Autowired
	private BankCredentialsRepository bankCredentialsRepository;

	public BankCredentials create(Long idUser, BankCredentials bankCredentials) throws Exception {

	EntityManager entitymanager = null;
	User user=null;
	try {
		entitymanager = JpaEntityManager.getInstance().createEntityManager();
		entitymanager.getTransaction().begin();
	
		user = entitymanager.find(User.class, idUser);

		System.out.println("user.getBankCredentials()?"
			+ (user.getBankCredentials() != null ? user.getBankCredentials().size() : "NULL"));

		if (Objects.isNull(bankCredentials.getIdUser())) {
		bankCredentials.setIdUser(idUser);
		} else if (bankCredentials.getIdUser().longValue() != idUser.longValue()) {
		throw new NoContentException("userID non corrispondenti");
		}
	
	List<BankCredentials> bankcredentials = user.getBankCredentials();
	for (BankCredentials bc : bankcredentials) {
		if (Objects.isNull(bc.getEndDate())) {
			LocalDate date = LocalDate.now();
			java.sql.Date dateSql = java.sql.Date.valueOf(date);
			bc.setEndDate(dateSql);
			
		}
	}
	bankcredentials.add(bankCredentials);
	user.setBankCredentials(bankcredentials);
	return bankCredentials;
	}catch (IllegalArgumentException ee) {
		throw new NoContentException("Non è stato trovata nessuna entità con l'id selezionato o l'id non è corretto");
	}catch (DBException e) {
		throw e;
	}finally {


	
	entitymanager.persist(user);
	entitymanager.getTransaction().commit();
	entitymanager.close();}

	
}


public List<BankCredentials> getBankCredentialsByUser(Long id) {
	List<BankCredentials> bankCredentials = bankCredentialsRepository.findByIdUser(id);
	System.out.println("bankCredentials : " + bankCredentials.size());
	if(bankCredentials.size() == 0) {
		System.out.println("ok");
		throw new NoContentException(" non è stato trovato nessun bank credential per questo id");
	}
	return bankCredentials;
}

	@Transactional
	public BankCredentials update(Long id, BankCredentials bankCredentials) {
		EntityManager entitymanager = null;
		try {
			entitymanager = JpaEntityManager.getInstance().createEntityManager();
			entitymanager.getTransaction().begin();
			BankCredentials bankcredentials = entitymanager.find(BankCredentials.class, id);
    	
			if (bankCredentials.getAccountholder() != null) bankcredentials.setAccountholder(bankCredentials.getAccountholder());
			if (bankCredentials.getIban() != null) bankcredentials.setIban(bankCredentials.getIban());
			if (bankCredentials.getSwift() != null) bankcredentials.setSwift(bankCredentials.getSwift());
			if (bankCredentials.getStartDate() != null) bankcredentials.setStartDate(bankCredentials.getStartDate());
			if (bankCredentials.getEndDate() != null) bankcredentials.setEndDate(bankCredentials.getEndDate());
			if (bankCredentials.getNotes() != null) bankcredentials.setNotes(bankCredentials.getNotes());
    	
			entitymanager.persist(bankcredentials);
			entitymanager.getTransaction().commit();
			return bankcredentials;
			}catch (IllegalArgumentException ee) {
				throw new NoContentException("Non è stato trovata nessuna entità con l'id selezionato o l'id non è corretto");
			}catch (DBException e) {
				throw e;
			}
			finally {
			
		entitymanager.close();}
		
		
	}

}
