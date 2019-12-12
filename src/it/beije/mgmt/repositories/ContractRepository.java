package it.beije.mgmt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.erp.entity.Contract;


@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
	
	List<Contract> findByIdUser(Long idUser);
	
}