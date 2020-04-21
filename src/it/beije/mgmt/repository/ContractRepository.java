package it.beije.mgmt.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.Contract;


@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
	
	List<Contract> findByIdUser(Long idUser);

	Contract findByIdUserAndEndDate(Long idUser, Date endDate);

}