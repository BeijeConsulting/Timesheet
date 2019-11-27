package it.beije.erp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.beije.erp.entity.Contract;


@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
	
//	Optional<Contract> find(Long id); //findOne ?
	
	List<Contract> findAll();
	
//	@Query("select c from Contract c where c.userId = ?1")
//	List<Contract> getUserContract(Long userId);
}
