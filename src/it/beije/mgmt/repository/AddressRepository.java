package it.beije.mgmt.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	List<Address> findByIdUser(Long idUser);
	
}