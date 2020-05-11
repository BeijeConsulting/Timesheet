package it.beije.mgmt.service;

import java.util.ArrayList;
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
import it.beije.mgmt.entity.ClientOrder;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.repository.ClientCompanyRepository;
import it.beije.mgmt.repository.ClientOrderRepository;

@Service
public class ClientCompanyService {
	
	@Autowired
	private ClientCompanyRepository clientCompanyRepository;
	
	@Autowired
	private ClientOrderRepository clientOrderRepository;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	public List<ClientCompany> findAll() {
		List<ClientCompany> allClients = clientCompanyRepository.findAll();
		if(allClients.size()==0)
			throw new NoContentException("La lista è vuota");
		return allClients;
	}

	public ClientCompany find(Long id) {
		log.debug("GET /clientcompanies/{id}");

		try {
			return clientCompanyRepository.findById(id).get();
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
			return clientCompanyRepository.saveAndFlush(client);
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
			ClientCompany client = clientCompanyRepository.findById(id).get();
			if (clientData.getBusinessName() != null) client.setBusinessName(clientData.getBusinessName());
	    	if (clientData.getDescription() != null) client.setDescription(clientData.getDescription());
	    	if (clientData.getAddress() != null) client.setAddress(clientData.getAddress());
	    	if (clientData.getCity() != null) client.setCity(clientData.getCity());
	    	if (clientData.getPostalCode() != null) client.setPostalCode(clientData.getPostalCode());
	    	if (clientData.getManagerName() != null) client.setManagerName(clientData.getManagerName());
	 
			return clientCompanyRepository.saveAndFlush(client);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}

	public List<ClientCompany> getClientsByUser(Long id) {
		log.debug("GET /clientcompanies/user/{id}");
		
		try {
			List<ClientOrder> orders = clientOrderRepository.findByIdUser(id);
			List<Long> idList = new ArrayList<>();
			for(ClientOrder co : orders) {
				if(!idList.contains(co.getIdClient())) idList.add(co.getIdClient());
			}
			List<ClientCompany> clients = clientCompanyRepository.findByIdIn(idList);
			if (clients.size()==0)
				throw new NoContentException("La lista è vuota");
		return clients;
		}catch (MasterException e) {
			throw e;
		}
	}
}
