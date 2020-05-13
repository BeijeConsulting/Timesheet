package it.beije.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.mgmt.entity.ClientOrder;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

	List<ClientOrder> findByIdUser(Long id);

}
