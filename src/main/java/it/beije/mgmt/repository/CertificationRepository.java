package it.beije.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.beije.mgmt.entity.cv.Certification;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

}
