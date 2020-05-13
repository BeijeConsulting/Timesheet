package it.beije.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.ClientCompany;

@Repository
public interface ClientCompanyRepository extends JpaRepository<ClientCompany, Long> {

	List<ClientCompany> findByIdIn(List<Long> idList);

	
	//List<ClientCompany> findByIdUser(Long id);

}
