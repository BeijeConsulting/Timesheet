package it.beije.mgmt.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

	List<User> findByFirstNameContaining(String firstName);
	List<User> findByFirstName(String firstName);

	List<User> findByLastNameContaining(String lastName);



	List<User> findByFiscalCodeContaining(String fiscalCode);

	List<User> findByEmailContains(String email);

	List<User> findByEmailLike(String string);
	
	//Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
	
	//IM : count u from User u where id = ?
}
