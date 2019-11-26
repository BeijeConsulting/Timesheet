package it.beije.erp.timesheet.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import it.beije.erp.entity.Computer;
import it.beije.erp.entity.User;
import it.beije.jpa.JpaEntityManager;

public class ComputerService {
	
	public static List<Computer> getComputers(Integer check) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		List<Computer> computers = new ArrayList<>();
		if(check==0) {
		computers=entityManager.createQuery("select c from Computer c",
			    Computer.class).getResultList();
		entityManager.close();
		return computers;
		}
		else if (check==1) {
			
			computers=entityManager.createQuery("select c from Computer c where c.availability=1",
				    Computer.class).getResultList();
			entityManager.close();
			return computers;
			
		}
		
		else {
			computers=entityManager.createQuery("select c from Computer c where c.availability=0",
				    Computer.class).getResultList();
			entityManager.close();
			return computers;
		}
	}
	
	public static List<Computer> searchComputer(String serialNumber, Integer ram, String cpu, String hardDisk) {

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
	
	public static Computer create(Computer computer) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(computer);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return computer;
	}
	
public static Computer update(Computer computer) {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(computer);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return computer;
	}
	
public static List<Computer> all() {

	EntityManagerFactory emfactory = JpaEntityManager.getInstance();
	EntityManager entitymanager = emfactory.createEntityManager();

	Query q = entitymanager.createNativeQuery("SELECT * FROM computer");

	List<Computer> computer = q.getResultList();

	entitymanager.close();
	
	System.out.println("caricaTutti : " + computer.size());
	
	return computer;
}

@Transactional
public static Computer find(Long id) {
	
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
