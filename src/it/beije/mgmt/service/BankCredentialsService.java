
package it.beije.mgmt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
//import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.entity.Computer;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.DBException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.repository.BankCredentialsRepository;


@Service
public class BankCredentialsService {
	
	@Autowired
	private BankCredentialsRepository bankCredentialsRepository;
	
	@Autowired
	private BankCredentialsService bankCredentialsService;
	
	@Autowired
	private UserService userService;
	
	public BankCredentials find(Long id) throws MasterException {
		try {
			BankCredentials bankCredentials = bankCredentialsRepository.findById(id).get();
				
			return bankCredentials;
		}catch (NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un bank credentials con l'id ");
		} catch (DBException e) {
			throw e;
		}
	}
	
	public BankCredentials create(BankCredentials bankCredentials,Long idUser) throws MasterException {
	
		try {
				if (Objects.isNull(bankCredentials.getIdUser())) {
					bankCredentials.setIdUser(idUser);
				}else if (bankCredentials.getIdUser().longValue() != idUser.longValue()) {
					throw new NoContentException("impossibile creare bank credentials");
					}
		
				if (Objects.isNull(bankCredentials.getEndDate())) {
					LocalDate date = LocalDate.now();
					java.sql.Date dateSql = java.sql.Date.valueOf(date);
					bankCredentials.setEndDate(dateSql);		
					}

		return bankCredentialsRepository.saveAndFlush(bankCredentials);
		}catch(EntityExistsException eee) {
			throw new ServiceException("Bank Credentials già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (DBException e) {
			throw e;
		}
		

	}
	
	public List<BankCredentials> getBankCredentialsByUser(Long id) {
	
	List<BankCredentials> bankCredentials = bankCredentialsRepository.findByIdUser(id);
	System.out.println("bankCredentials : " + bankCredentials.size());
	if(bankCredentials.size() == 0) {
		//System.out.println("ok");
		throw new NoContentException(" non è stato trovato nessun bank credential per questo id");
	}
	return bankCredentials;
}

	public BankCredentials update(Long id, BankCredentials bankCredentials) {
		
		try {
			BankCredentials bankcredentials = bankCredentialsService.find(id);
				
    	
			if (bankCredentials.getAccountholder() != null) bankcredentials.setAccountholder(bankCredentials.getAccountholder());
			if (bankCredentials.getIban() != null) bankcredentials.setIban(bankCredentials.getIban());
			if (bankCredentials.getSwift() != null) bankcredentials.setSwift(bankCredentials.getSwift());
			if (bankCredentials.getStartDate() != null) bankcredentials.setStartDate(bankCredentials.getStartDate());
			if (bankCredentials.getEndDate() != null) bankcredentials.setEndDate(bankCredentials.getEndDate());
			else if (Objects.isNull(bankCredentials.getEndDate())) {
				LocalDate date = LocalDate.now();
				java.sql.Date dateSql = java.sql.Date.valueOf(date);
				bankcredentials.setEndDate(dateSql);	
				
				}

			if (bankCredentials.getNotes() != null) bankcredentials.setNotes(bankCredentials.getNotes());
    	
			
			return bankCredentialsRepository.saveAndFlush(bankcredentials);

			}catch(IllegalStateException  | PersistenceException e) {
				throw new ServiceException("Al momento non è possibile soddisfare la richiesta");}
				
			catch (MasterException ee) {
				throw ee;
			}
			
		
		
	}

}
