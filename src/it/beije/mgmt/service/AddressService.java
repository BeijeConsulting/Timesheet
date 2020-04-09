package it.beije.mgmt.service;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.repository.AddressRepository;


@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	public Address create(Long idUser, Address address) throws Exception {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();
		
		User user = entityManager.find(User.class, idUser);

		System.out.println("user.getAddresses()?"
				+ (user.getAddresses() != null ? user.getAddresses().size() : "NULL"));

		if (Objects.isNull(address.getIdUser())) {
			address.setIdUser(idUser);
		} else if (address.getIdUser().longValue() != idUser.longValue()) {
			throw new Exception();
		}
		
		List<Address> addresses = user.getAddresses();
		

		addresses.add(address);
		user.setAddresses(addresses);

		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();

		return address;
	}



	
	@Transactional
	public List<Address> getAddressByUser(Long id) {
		
		List<Address> address = addressRepository.findByIdUser(id);
		
		System.out.println("addresses Size : " + address.size());
		
		return address;
	}
	
	@Transactional
	public Address update(Long id, Address addresses) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Address address = entitymanager.find(Address.class, id);
    	
    	if (!Objects.isNull(addresses.getStreet())) address.setStreet(addresses.getStreet());
    	if (addresses.getCity() != null) address.setCity(addresses.getCity());
    	if (addresses.getProvince() != null) address.setProvince(addresses.getProvince());
    	if (!Objects.isNull(addresses.getCap())) address.setCap(addresses.getCap());
    	if (!Objects.isNull(addresses.getCountry())) address.setCountry(addresses.getCountry());
    	if (!Objects.isNull(addresses.getStartDate())) address.setStartDate(addresses.getStartDate());
    	if (!Objects.isNull(addresses.getEndDate())) address.setEndDate(addresses.getEndDate());
    	if (!Objects.isNull(addresses.getType())) address.setType(addresses.getType());
    	
		entitymanager.persist(address);
		entitymanager.getTransaction().commit();
		entitymanager.close();
			
			return address;
		}
}


