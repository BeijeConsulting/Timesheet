package it.beije.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.beije.mgmt.entity.Token;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<Token, Long> {
	
	 Token findByToken(Object token);
	
}