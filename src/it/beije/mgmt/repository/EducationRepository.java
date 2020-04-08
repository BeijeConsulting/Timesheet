package it.beije.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.mgmt.entity.cv.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

}
