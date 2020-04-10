package it.beije.mgmt.service;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.Address;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.repository.AddressRepository;


@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address create(Long idUser, Address address) throws Exception {
		
		try {
			if(address.getId()!=null)
				throw new EntityExistsException();
			if (Objects.isNull(address.getIdUser()))
				address.setIdUser(idUser);
			else if (address.getIdUser().longValue() != idUser.longValue())
				throw new ServiceException("Dati non conformi");
			return addressRepository.saveAndFlush(address);
		}catch(EntityExistsException eee) {
			throw new ServiceException("User gi� presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non � possibile soddisfare la richiesta");
		}catch(MasterException e) {
			throw e;
		}
	}



	
	@Transactional
	public List<Address> getAddressByUser(Long id) {
		
		try {
			List<Address> address = addressRepository.findByIdUser(id);
			if (address.size()==0)
				throw new NoContentException("La lista � vuota");
		return address;
		}catch (Exception e) {
			throw new ServiceException("Si � verificato un errore");
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
			throw new ServiceException("Al momento non � possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}

	@Transactional
	public Address find(Long id) {

		try {
			return addressRepository.getOne(id);
		}catch (EntityNotFoundException e) {
			throw new NoContentException("Non � stato trovato un indirizzo con l'id selezionato o i dati potrebbero essere corrotti");
		}
	}
}


