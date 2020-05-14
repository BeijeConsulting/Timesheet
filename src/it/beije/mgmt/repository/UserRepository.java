package it.beije.mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	Optional<User> findByEmail(String email);


	
	
	//Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
	
	//IM : count u from User u where id = ?
}
