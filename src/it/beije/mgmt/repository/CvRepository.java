package it.beije.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.mgmt.entity.Address;

public interface CvRepository extends JpaRepository<Address, Long> {
	
	List<Address> findCvById(Long idUser);
	
}