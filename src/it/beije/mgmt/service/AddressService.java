package it.beije.mgmt.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserService userService;
	
	@Transactional
	public Address create(Long idUser, Address address) throws Exception {
		
		try {
			userService.findById(idUser);
			if(address.getId()!=null)
				throw new InvalidJSONException("Errore nei dati inviati");
			if (Objects.isNull(address.getIdUser()))
				address.setIdUser(idUser);
			else if (address.getIdUser().longValue() != idUser.longValue())
				throw new ServiceException("Dati non conformi");
			return addressRepository.saveAndFlush(address);
		}catch(EntityExistsException eee) {
			throw new ServiceException("User già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		}catch(MasterException e) {
			throw e;
		}
	}

	public List<Address> getAddressByUser(Long id) {
		
		try {
			userService.findById(id);
			List<Address> address = addressRepository.findByIdUser(id);
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
	public Address update(Long id, Address addresses) {
		
		try {
			Address address = find(id);
    	
	    	if (!Objects.isNull(addresses.getStreet())) address.setStreet(addresses.getStreet());
	    	if (addresses.getCity() != null) address.setCity(addresses.getCity());
	    	if (addresses.getProvince() != null) address.setProvince(addresses.getProvince());
	    	if (!Objects.isNull(addresses.getCap())) address.setCap(addresses.getCap());
	    	if (!Objects.isNull(addresses.getCountry())) address.setCountry(addresses.getCountry());
	    	if (!Objects.isNull(addresses.getStartDate())) address.setStartDate(addresses.getStartDate());
	    	if (!Objects.isNull(addresses.getEndDate())) address.setEndDate(addresses.getEndDate());
	    	if (!Objects.isNull(addresses.getType())) address.setType(addresses.getType());
	    	
			return addressRepository.saveAndFlush(address);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}

	public Address find(Long id) {

		try {
			return addressRepository.findById(id).get();
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un indirizzo con l'id selezionato o i dati potrebbero essere corrotti");
		}
	}
}


