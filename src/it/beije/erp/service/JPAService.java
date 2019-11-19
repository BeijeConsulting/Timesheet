package it.beije.erp.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.beije.erp.entity.Address;
import it.beije.erp.entity.Computer;
import it.beije.erp.entity.User;
import it.beije.jpa.JpaEntityManager;

public class JPAService {

	private static EntityManager entityManager;
	private static final String project = "timesheet";
	
	public static <T> T getBean(Class<T> cls, Object pk) {
		EntityManager entityManager = Persistence.createEntityManagerFactory(project).createEntityManager();
		T obj = entityManager.find(cls, pk);
		entityManager.close();
		return obj;
	}
	
	public static List<Computer> getComputers(Integer check) {
		EntityManager entityManager = Persistence.createEntityManagerFactory(project).createEntityManager();
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

	public static void save(Object obj) {
		EntityManager entityManager = Persistence.createEntityManagerFactory(project).createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(obj);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void modify(Object obj) {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory(project).createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(obj);
		entityManager.getTransaction().commit();
		entityManager.close();
		
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

}
