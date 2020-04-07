package it.beije.mgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.entity.Computer;
import it.beije.mgmt.repository.ComputerRepository;


@Service
public class ComputerService {
	@Autowired
	private static ComputerRepository computerRepository;
	public List<Computer> getComputers(Boolean check) {
		System.out.println("check : " + check);
		return getComputers(check, !check);
	}
	
	public List<Computer> getComputers(boolean check, boolean maintenance) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		List<Computer> computers = new ArrayList<>();
		if (check) {
			computers=entityManager.createQuery("select c from Computer c where c.id not in " + 
					"(select uc.idComputer from UserComputer uc where uc.endDate is null) " + 
					"and c.maintenance is false" +
					"and c.disposal_date is null",
				    Computer.class).getResultList();
			
			entityManager.close();
			return computers;
			
		}
		else {
			computers=entityManager.createQuery("select c from Computer c left outer join UserComputer uc on c.id = uc.idComputer " +  
					"where c.maintenance is true " +  
					"and c.disposal_date is null" +
					"OR (uc.startDate is not null AND uc.endDate is null) " + 
					"group by c.id"
				    ,Computer.class).getResultList();
			entityManager.close();
			return computers;
		}
	}
// Da fare!
//----------------------------------------------------------------------------------------------------------------
	
	public List<Computer> searchComputer(String serialNumber, Integer ram, String cpu, String hardDisk) {
		List<Computer> computers=computerRepository.findBySerialNumberAndRamAndCpuAndHardDisk(serialNumber, ram, cpu, hardDisk);
		return computers;
	}
//----------------------------------------------------------------------------------------------------------------
@Transactional
	public Computer create(Computer computer) {
		return computerRepository.save(computer);
	}
//----------------------------------------------------------------------------------------------------------------
@Transactional	
public Computer update(Computer computer,Long id) {

		Computer pc = ComputerService.find(id);
    	
		//ATTENZIONE SE PC== NULL, NON è GESTITO IL NULL POINTER
    	if (!Objects.isNull(computer.getBrand())) pc.setBrand(computer.getBrand());
    	if (computer.getModel() != null) pc.setModel(computer.getModel());
    	if (computer.getCpu() != null) pc.setCpu(computer.getCpu());
    	if (!Objects.isNull(computer.getRam())) pc.setRam(computer.getRam());
    	
    	if (!Objects.isNull(computer.getHardDisk())) pc.setHardDisk(computer.getHardDisk());
    	if (!Objects.isNull(computer.getSerialNumber())) pc.setSerialNumber(computer.getSerialNumber());
    	if (!Objects.isNull(computer.getOperatingSystem())) pc.setOperatingSystem(computer.getOperatingSystem());
    	if (!Objects.isNull(computer.getPurchaseDate())) pc.setPurchaseDate(computer.getPurchaseDate());
    	if (!Objects.isNull(computer.getDisposalDate())) pc.setDisposalDate(computer.getDisposalDate());
    	if (computer.getNote() != null) pc.setNote(computer.getNote());
    	if (!Objects.isNull(computer.isMaintenance())) pc.setMaintenance(computer.isMaintenance());
    	
    	computerRepository.save(pc);
		return pc;
	}
//----------------------------------------------------------------------------------------------------------------
public List<Computer> all() {
	return computerRepository.findAll();
}
//----------------------------------------------------------------------------------------------------------------
public static Computer find(Long id) {
	return  computerRepository.getOne(id);
}
}
