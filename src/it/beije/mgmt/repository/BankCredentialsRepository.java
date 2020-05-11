package it.beije.mgmt.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.BankCredentials;


@Repository
public interface BankCredentialsRepository extends JpaRepository<BankCredentials, Long> {
	
	List<BankCredentials> findByIdUser(Long idUser, Sort sort);

	BankCredentials findByIdUserAndEndDate(Long idUser, Object object);
	
}
