package it.beije.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.beije.mgmt.entity.cv.CV;

@Repository
public interface CvRepository extends JpaRepository<CV, Long>{
	
	List<CV> findByTechnology(String technology);

}
