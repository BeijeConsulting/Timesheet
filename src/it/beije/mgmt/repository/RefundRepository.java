package it.beije.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.model.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {

}
