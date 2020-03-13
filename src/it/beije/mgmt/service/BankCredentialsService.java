
package it.beije.mgmt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import it.beije.erp.entity.BankCredentials;
import it.beije.erp.entity.Computer;
import it.beije.erp.entity.User;
import it.beije.jpa.JpaEntityManager;
import it.beije.mgmt.repositories.BankCredentialsRepository;


@Service
public class BankCredentialsService {
	
	@Autowired
	private BankCredentialsRepository bankCredentialsRepository;

	public BankCredentials create(Long idUser, BankCredentials bankCredentials) throws Exception {
		EntityManager entityManager = JpaEntityManager.getInstance().createEntityManager();
		entityManager.getTransaction().begin();
		
		User user = entityManager.find(User.class, idUser);

		System.out.println("user.getBankCredentials()?"
				+ (user.getBankCredentials() != null ? user.getBankCredentials().size() : "NULL"));

		if (Objects.isNull(bankCredentials.getIdUser())) {
			bankCredentials.setIdUser(idUser);
		} else if (bankCredentials.getIdUser().longValue() != idUser.longValue()) {
			throw new Exception();
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

		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();

		return bankCredentials;
	}

//	public static List<BankCredentials> all() {
//
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//
//		Query q = entitymanager.createNativeQuery("SELECT * FROM bank_credentials WHERE end_date = NULL");
//
//		List<BankCredentials> bankCredentials = q.getResultList();
//
//		entitymanager.close();
//
//		System.out.println("caricaTutti : " + bankCredentials.size());
//
//		return bankCredentials;
//	}

//	@Transactional
//	public BankCredentials find(Long id) {
//
//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		BankCredentials bankCredentials;
//		try {
//			bankCredentials = entitymanager
//					.createQuery("SELECT b FROM bankCredentials b WHERE b.id = " + id,
//							BankCredentials.class)
//					.getSingleResult();
//			Hibernate.initialize(bankCredentials.getAssignment());
//		} catch (NoResultException e) {
//			return new BankCredentials();
//		}
//
//		entitymanager.close();
//
//		return bankCredentials;
//	}
	
	
	public List<BankCredentials> getBankCredentialsByUser(Long id) {
		
		List<BankCredentials> bankCredentials = bankCredentialsRepository.findByIdUser(id);
		
		System.out.println("bankCredentials : " + bankCredentials.size());
		
		return bankCredentials;
	}
	
	@Transactional
	public BankCredentials update(Long id, BankCredentials bankCredentials) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
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
		entitymanager.close();
		
		return bankcredentials;
	}

}
