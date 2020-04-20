package it.beije.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.mgmt.entity.cv.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {

}
