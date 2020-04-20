package it.beije.mgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.Computer;
import it.beije.mgmt.exception.NotExistPcException;
import it.beije.mgmt.repository.ComputerRepository;


@Service
public class ComputerService {
	
	@Autowired
	private ComputerRepository computerRepository;
	
	public List<Computer> getComputers(Boolean check) {
		System.out.println("check : " + check);
		return getComputers(check, !check);
	}
	//Non so benissimo cosa fa qusta query e quindi non so come gestire gli errori
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
	
//	public List<Computer> searchComputer(String serialNumber, Integer ram, String cpu, String hardDisk) {
//		List<Computer> computers=computerRepository.findBySerialNumberAndRamAndCpuAndHardDisk(serialNumber, ram, cpu, hardDisk);
//		return computers;
//	}
	public List<Computer> searchComputer(String serialNumber, Integer ram, String cpu, String hardDisk) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		
		List<String> searchQuery=new ArrayList<>();
		String whereClause="";
		
		if (serialNumber.length()>0) {
			searchQuery.add("a.serialNumber='"+serialNumber+"'");
			if (whereClause.length()==0)
			whereClause+="WHERE ";
		}
		if (ram!=0) {
			searchQuery.add("a.ram='"+ram.toString()+"'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		if (cpu.length()>0) {
			searchQuery.add("a.cpu='"+cpu+"'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		if (hardDisk.length()>0) {
			searchQuery.add("a.hardDisk='"+hardDisk+"'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		
		System.out.println("Sto cercando");
		
		for (int i=0;i<searchQuery.size();i++) {
			whereClause+=searchQuery.get(i);
			if (i!=searchQuery.size()-1)
				whereClause+=" AND ";
		}
		
		TypedQuery<Computer> query=entitymanager.createQuery("SELECT a from Computer a "+whereClause,Computer.class);
		
		List<Computer> computers=query.getResultList();
		
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

		Optional<Computer> pc = computerRepository.findById(id);
		if(pc==null)
			//computer non è in database
			throw new NotExistPcException("Il comuter non è presente nel database");
    	
		//ATTENZIONE SE PC== NULL, NON è GESTITO IL NULL POINTER
    	if (!Objects.isNull(computer.getBrand())) pc.get().setBrand(computer.getBrand());
    	if (computer.getModel() != null) pc.get().setModel(computer.getModel());
    	if (computer.getCpu() != null) pc.get().setCpu(computer.getCpu());
    	if (!Objects.isNull(computer.getRam())) pc.get().setRam(computer.getRam());
    	if (!Objects.isNull(computer.getHardDisk())) pc.get().setHardDisk(computer.getHardDisk());
    	if (!Objects.isNull(computer.getSerialNumber())) pc.get().setSerialNumber(computer.getSerialNumber());
    	if (!Objects.isNull(computer.getOperatingSystem())) pc.get().setOperatingSystem(computer.getOperatingSystem());
    	if (!Objects.isNull(computer.getPurchaseDate())) pc.get().setPurchaseDate(computer.getPurchaseDate());
    	if (!Objects.isNull(computer.getDisposalDate())) pc.get().setDisposalDate(computer.getDisposalDate());
    	if (computer.getNote() != null) pc.get().setNote(computer.getNote());
    	if (!Objects.isNull(computer.isMaintenance())) pc.get().setMaintenance(computer.isMaintenance());
    	
    	computerRepository.save(pc.get());
		return pc.get();
	}
//----------------------------------------------------------------------------------------------------------------
	public List<Computer> all() {
		return computerRepository.findAll();
}
//----------------------------------------------------------------------------------------------------------------
	public Computer find(Long id) {
		return  computerRepository.getOne(id);
}
}
