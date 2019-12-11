package it.beije.mgmt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.erp.entity.BankCredentials;


@Repository
public interface BankCredentialsRepository extends JpaRepository<BankCredentials, Long> {
	
	List<BankCredentials> findByIdUser(Long idUser);
	
}
