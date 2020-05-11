package it.beije.mgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.JpaEntityManager;
import it.beije.mgmt.entity.Computer;


@Service
public class ComputerService_Vecchio {
	
	public List<Computer> getComputers(Boolean check) {
		System.out.println("check : " + check);
		return getComputers(check, !check);
	}
	
	public List<Computer> getComputers(boolean check, boolean maintenance) {
		System.out.println("available : " + check);
		System.out.println("maintenance : " + maintenance);
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		List<Computer> computers = new ArrayList<>();
		
//		if(check==0) {
//		computers=entityManager.createQuery("select c from Computer c",
//			    Computer.class).getResultList();
//		entityManager.close();
//		return computers;
//		}
//		else
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
	
	public Computer create(Computer computer) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(computer);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return computer;
	}
	
public Computer update(Computer computer,Long id) {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheetDB").createEntityManager();
		entityManager.getTransaction().begin();
		Computer pc = entityManager.find(Computer.class, id);
    	
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
    	
    	
    	
    
		entityManager.persist(pc);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		
		return computer;
	}
	
public List<Computer> all() {

	EntityManagerFactory emfactory = JpaEntityManager.getInstance();
	EntityManager entitymanager = emfactory.createEntityManager();

	Query q = entitymanager.createNativeQuery("SELECT * FROM computer");

	List<Computer> computer = q.getResultList();

	entitymanager.close();
	
	System.out.println("caricaTutti : " + computer.size());
	
	return computer;
}

@Transactional
public Computer find(Long id) {
	
	EntityManagerFactory emfactory = JpaEntityManager.getInstance();
	EntityManager entitymanager = emfactory.createEntityManager();
	Computer computer;
	try {
		computer = entitymanager.createQuery("SELECT c FROM Computer c WHERE c.id = "+id,Computer.class).getSingleResult();
		Hibernate.initialize(computer.getAssignment());
	}catch (NoResultException e)
	{
		return new Computer();
	}
	
	entitymanager.close();

	return computer;
}


}
