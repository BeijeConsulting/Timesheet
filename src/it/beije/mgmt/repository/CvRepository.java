package it.beije.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.mgmt.entity.cv.CV;

public interface CvRepository extends JpaRepository<CV, Long> {
	

}
