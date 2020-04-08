package it.beije.mgmt.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.repository.ContractRepository;
import it.beije.mgmt.repository.UserRepository;
import it.beije.mgmt.restcontroller.exception.NoContentException;

@Transactional
@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//Aggiunge un nuovo contratto alla lista dell'utente
	// user repository non ancora finito , modificare in seguito 
	public Contract create(Long idUser, Contract contract) throws Exception {
	

		User user = userRepository.getOne(idUser); 
		if(user== null)
			throw new NoContentException("Id user invalido");

		System.out.println("user.getContract()?"
				+ (user.getContracts() != null ? user.getContracts().size() : "NULL"));

		if (Objects.isNull(contract.getIdUser())) {
			contract.setIdUser(idUser);
		} else if (contract.getIdUser().longValue() != idUser.longValue()) {
			throw new Exception();
		}

		List<Contract> contracts =  user.getContracts();
		for (Contract c : contracts) {
			if (Objects.isNull(c.getEndDate())) {
				LocalDate date = LocalDate.now();
				java.sql.Date dateSql = java.sql.Date.valueOf(date);
				c.setEndDate(dateSql);
			}
		}
		
		contractRepository.save(contract); 
		user.setContracts(contracts);

		

		return contract;
	}
	
	// ritorna la lista dei contratti dell'utente dato il suo id 
	public List<Contract> getContractByUser(Long id) {

		List<Contract> contracts = contractRepository.findByIdUser(id);
		if(contracts == null)
		{
			throw new NoContentException("contratto non valido");
		}

		System.out.println("bankCredentials : " + contracts.size());

		return contracts;
	}

	//aggiorna i risultati di un contratto
	@Transactional
	public Contract update(Long id, Contract contracts) {
		

		Contract contract = contractRepository.findByContract(contracts);
		if(contract == null)
		{
			throw new NoContentException("contratto non valido");
		}

		if (!Objects.isNull(contracts.getContract_type())) contract.setContract_type(contracts.getContract_type());
		if (contracts.getType() != null) contract.setType(contracts.getType());
		if (contracts.getCcnl() != null) contract.setCcnl(contracts.getCcnl());
		if (!Objects.isNull(contracts.getLvl())) contract.setLvl(contracts.getLvl());

		if (!Objects.isNull(contracts.getMinimoContrattuale())) contract.setMinimoContrattuale(contracts.getMinimoContrattuale());
		if (!Objects.isNull(contracts.getSuperminimo())) contract.setSuperminimo(contracts.getSuperminimo());
		if (!Objects.isNull(contracts.getRetribuzioneMensile())) contract.setRetribuzioneMensile(contracts.getRetribuzioneMensile());
		if (!Objects.isNull(contracts.getRal())) contract.setRal(contracts.getRal());
		if (!Objects.isNull(contracts.getNettoMensile())) contract.setNettoMensile(contracts.getNettoMensile());
		if (!Objects.isNull(contracts.getCostoInterno())) contract.setCostoInterno(contracts.getCostoInterno());
		if (contracts.getNote() != null) contract.setNote(contract.getNote());
		if (contracts.getStartDate() != null) contract.setStartDate(contracts.getStartDate());
		if (contracts.getEndDate() != null) contract.setEndDate(contracts.getEndDate());

		contractRepository.save(contract);


		return contract;
	}
}



