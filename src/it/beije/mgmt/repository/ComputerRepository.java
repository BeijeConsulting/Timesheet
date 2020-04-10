package it.beije.mgmt.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.Computer;

@Repository
public interface ComputerRepository  extends JpaRepository<Computer, Long>  {
	
	List<Computer> findBySerialNumberAndRamAndCpuAndHardDisk(String serialNumber, Integer ram, String cpu, String hardDisk);
}
