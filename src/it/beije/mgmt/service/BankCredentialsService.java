
package it.beije.mgmt.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.repository.BankCredentialsRepository;


@Service
public class BankCredentialsService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BankCredentialsRepository bankCredentialsRepository;

	
	private BankCredentials create(BankCredentials bankCredentials) {
		try {
			if (bankCredentials.getId() != null) {
				throw new InvalidJSONException("Errore nei dati inviati");
			}
			bankCredentials.setStartDate(Date.valueOf(LocalDate.now()));
			
			return bankCredentialsRepository.saveAndFlush(bankCredentials);
		} catch (EntityExistsException eee) {
			throw new ServiceException("Valore già presente nel database");
		} catch (IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}

	@Transactional
	public BankCredentials create(Long idUser, BankCredentials bankCredentials) {
		if (bankCredentials.getId() != null || bankCredentials.getStartDate() != null || bankCredentials.getEndDate() != null) {
			throw new InvalidJSONException("Errore nei dati inviati");
		}
		if (Objects.isNull(bankCredentials.getIdUser())) {
			bankCredentials.setIdUser(idUser);
		} else if (bankCredentials.getIdUser().longValue() != idUser.longValue()) {
			throw new ServiceException("Dati non conformi");
		}
		bankCredentials.setStartDate(Date.valueOf(LocalDate.now()));
		
		return this.create(bankCredentials);
	}

	public List<BankCredentials> getBankCredentialsByUser(Long id) {
		
		try {
			List<BankCredentials> bankCred = bankCredentialsRepository.findByIdUser(id);
			log.info("bankCredentials : " + bankCred.size());
			if (bankCred.size()==0)
				throw new NoContentException("La lista è vuota");
			return bankCred;
		}catch (MasterException e) {
			throw e;
		}catch (Exception e) {
			throw new ServiceException("Si è verificato un errore");
		}
	}
	
	@Transactional
	public BankCredentials update(Long id, BankCredentials bankCredentials) {
		if (bankCredentials.getIdUser() == null) {
			throw new InvalidJSONException("Errore nei dati inviati");
		}
		
		try {
			BankCredentials bankCredentialOld = find(id);
			if (bankCredentialOld.getEndDate()==null) {
				bankCredentialOld.setEndDate(Date.valueOf(LocalDate.now()));
			}
			bankCredentials.setId(null);
			bankCredentialsRepository.saveAndFlush(bankCredentialOld);
			
			return this.create(bankCredentials);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}

	public BankCredentials find(Long id) {

		try {
			return bankCredentialsRepository.findById(id).get();
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un bank credentials con l'id selezionato o i dati potrebbero essere corrotti");
		}
	}
}
