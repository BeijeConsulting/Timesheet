package it.beije.mgmt.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	List<Address> findByIdUser(Long idUser, Sort sort);

	List<Address> findByIdUserAndEndDate(Long idUser, Date endDate);
}