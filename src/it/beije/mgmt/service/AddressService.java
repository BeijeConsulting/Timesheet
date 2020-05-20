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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.repository.AddressRepository;


@Service
public class AddressService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AddressRepository addressRepository;
	
	@Transactional
	public Address create(Long idUser, Address address) {
		log.debug("POST /addresses/user/{id}");
		try {
			if(address.getId()!=null)
				throw new InvalidJSONException("Errore nei dati inviati");
			if (address.getIdUser()==null)
				address.setIdUser(idUser);
			else if (address.getIdUser().longValue() != idUser.longValue())
				throw new ServiceException("Dati non conformi");
			return addressRepository.saveAndFlush(address);
		}catch(EntityExistsException eee) {
			throw new ServiceException("Indirizzo già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		}catch(MasterException e) {
			throw e;
		}
	}
	
	public List<Address> getAddressByUser(Long id) {
		log.debug("GET /addresses/user/{id}");
		try {
			List<Address> address = addressRepository.findByIdUser(id, Sort.by(Sort.Direction.DESC, "startDate"));
			if (address.size()==0)
				throw new NoContentException("La lista è vuota");
			return address;
		}catch (MasterException e) {
			throw e;
		}catch (Exception e) {
			throw new ServiceException("Si è verificato un errore");
		}
	}
	
	@Transactional
	public Address update(Long id, Address addressNew) {
		log.debug("PUT /addresses/{id}");
		try {
			Address address = find(id);
    	
	    	if (!Objects.isNull(addressNew.getStreet())) address.setStreet(addressNew.getStreet());
	    	if (addressNew.getCity() != null) address.setCity(addressNew.getCity());
	    	if (addressNew.getProvince() != null) address.setProvince(addressNew.getProvince());
	    	if (!Objects.isNull(addressNew.getCap())) address.setCap(addressNew.getCap());
	    	if (!Objects.isNull(addressNew.getCountry())) address.setCountry(addressNew.getCountry());
	    	if (!Objects.isNull(addressNew.getStartDate())) address.setStartDate(addressNew.getStartDate());
	    	if (!Objects.isNull(addressNew.getEndDate())) address.setEndDate(addressNew.getEndDate());
	    	if (!Objects.isNull(addressNew.getType())) address.setType(addressNew.getType());
	    	
			return addressRepository.saveAndFlush(address);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}

	public Address find(Long id) {
		log.debug("GET /addresses/{id}");
		try {
			return addressRepository.findById(id).get();
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un indirizzo con l'id selezionato o i dati potrebbero essere corrotti");
		}
	}
	
	@Transactional
	public boolean archive(Long id) {
		log.debug("PUT /address/archive/{id}");
		try {
			Address address = find(id);
			address.setEndDate(Date.valueOf(LocalDate.now()));	
			update(id,address);
		}catch(MasterException e) {
			return false;
		}
		return true;	
	}
}


