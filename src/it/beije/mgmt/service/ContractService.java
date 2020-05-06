package it.beije.mgmt.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.repository.ContractRepository;

@Transactional
@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepository;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//Aggiunge un nuovo contratto alla lista dell'utente
	// user repository non ancora finito , modificare in seguito 
	public Contract create(Long idUser, Contract contract) throws Exception {
	
		try {
			if(contract.getId()!=null)
				throw new InvalidJSONException("Errore nei dati inviati");
			if (Objects.isNull(contract.getIdUser()))
				contract.setIdUser(idUser);
			else if (contract.getIdUser().longValue() != idUser.longValue())
				throw new ServiceException("Dati non conformi");
			return contractRepository.saveAndFlush(contract);
		}catch(EntityExistsException eee) {
			throw new ServiceException("Contratto già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		}catch(MasterException e) {
			throw e;
		}
	}
	
	// ritorna la lista dei contratti dell'utente dato il suo id 
	public List<Contract> getContractByUser(Long id) {
		
		try {
			List<Contract> contracts = contractRepository.findByIdUser(id, Sort.by(Sort.Direction.DESC, "startDate"));	
			if (contracts.size()==0)
				throw new NoContentException("La lista è vuota");
			return contracts;
		}catch (MasterException e) {
			throw e;
		}catch (Exception e) {
			throw new ServiceException("Si è verificato un errore");
		}
	}

	//aggiorna i risultati di un contratto
	@Transactional
	public Contract update(Long id, Contract newContract) {
		
		try {
			Contract contract = find(id);

			if (!Objects.isNull(newContract.getContract_type())) contract.setContract_type(newContract.getContract_type());
			if (newContract.getType() != null) contract.setType(newContract.getType());
			if (newContract.getCcnl() != null) contract.setCcnl(newContract.getCcnl());
			if (!Objects.isNull(newContract.getLivello())) contract.setLivello(newContract.getLivello());
			if (!Objects.isNull(newContract.getMinimoContrattuale())) contract.setMinimoContrattuale(newContract.getMinimoContrattuale());
			if (!Objects.isNull(newContract.getSuperminimo())) contract.setSuperminimo(newContract.getSuperminimo());
			if (!Objects.isNull(newContract.getRetribuzioneMensile())) contract.setRetribuzioneMensile(newContract.getRetribuzioneMensile());
			if (!Objects.isNull(newContract.getRal())) contract.setRal(newContract.getRal());
			if (!Objects.isNull(newContract.getNettoMensile())) contract.setNettoMensile(newContract.getNettoMensile());
			if (!Objects.isNull(newContract.getCostoInterno())) contract.setCostoInterno(newContract.getCostoInterno());
			if (newContract.getNote() != null) contract.setNote(contract.getNote());
			if (newContract.getStartDate() != null) contract.setStartDate(newContract.getStartDate());
			if (newContract.getEndDate() != null) contract.setEndDate(newContract.getEndDate());

			return contractRepository.saveAndFlush(contract);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}

	public Contract find(Long id) {
		
		try {
			return contractRepository.findById(id).get();
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un contratto con l'id selezionato o i dati potrebbero essere corrotti");
		}
	}

}



