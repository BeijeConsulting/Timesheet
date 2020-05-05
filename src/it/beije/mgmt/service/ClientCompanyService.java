package it.beije.mgmt.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.mgmt.entity.ClientCompany;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.repository.ClientCompanyRepository;

@Service
public class ClientCompanyService {
	
	@Autowired
	private ClientCompanyRepository clientRepository;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	

	public List<ClientCompany> caricaTutti() {
		log.debug("GET /clientcompanies");
		List<ClientCompany> allClients = clientRepository.findAll();
		if(allClients.size()==0)
			throw new NoContentException("La lista è vuota");
		return allClients;
	}

	public ClientCompany find(Long id) {
		log.debug("GET /clientcompanies/{id}");

		try {
			return clientRepository.findById(id).get();
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un cliente con l'id selezionato o i dati potrebbero essere corrotti");
		} catch (MasterException e) {
			throw e;
		}
	}

	@Transactional
	public ClientCompany create(ClientCompany client) {
		log.debug("POST /clientcompany");

		try {
			if(client.getId()!=null)
				throw new InvalidJSONException("Errore nei dati inviati");
			return clientRepository.saveAndFlush(client);
		}catch(EntityExistsException eee) {
			throw new ServiceException("Cliente già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}
	
	@Transactional
	public ClientCompany update(Long id, ClientCompany clientData) {
		log.debug("PUT /clientcompany/{id}");
		
		try {
			ClientCompany client = clientRepository.findById(id).get();
			if (clientData.getFirstName() != null) client.setFirstName(clientData.getFirstName());
	    	if (clientData.getLastName() != null) client.setLastName(clientData.getLastName());
	    	if (clientData.getEmail() != null) client.setEmail(clientData.getEmail());
	    	if (clientData.getSecondaryEmail() != null) client.setSecondaryEmail(clientData.getSecondaryEmail());
	    	if (clientData.getPhone() != null) client.setPhone(clientData.getPhone());
	    	if (clientData.getOffices() != null) client.setOffices(clientData.getOffices());
	 
			return clientRepository.saveAndFlush(client);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}
//***************************************EDIT****************************************************+
	public List<ClientCompany> getClientsByUser(Long id) {
		log.debug("GET /clientcompanies/user/{id}");
		
		try {
			List<ClientCompany> clients = null;/*clientRepository.findByIdUser(id);*/
			if (clients.size()==0)
				throw new NoContentException("La lista è vuota");
		return clients;
		}catch (MasterException e) {
			throw e;
		}
	}
}
