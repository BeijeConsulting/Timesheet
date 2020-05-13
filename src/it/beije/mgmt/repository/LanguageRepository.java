package it.beije.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.mgmt.entity.cv.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {
	
	

}
