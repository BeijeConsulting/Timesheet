package it.beije.mgmt.service;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.entity.cv.Work;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.repository.CVRepository;

@Service
public class CVService {
	
	@Autowired
	private CVRepository CvRepository;
	
	public Work setWork(Long idWork, Work work) throws Exception {
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		entityManager.getTransaction().begin();
		
		Work w = entityManager.find(Work.class, idWork);
		CV cv = new CV();
		
		System.out.println("cv.getWorkList()?" 
		+ (cv.getWorkList() != null ? cv.getWorkList().size() : "NULL"));
		
		
		if (Objects.isNull(w.getIdWork())) {
			w.setIdWork(idWork);
		} else if (w.getIdWork().longValue() != idWork.longValue()) {
			throw new Exception();
		}	
		
		List<Work> Works = cv.getWorkList();
		
		Works.add(work);
		cv.setWorkList(Works);
		
		entityManager.persist(cv);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return work;
	}
	
	
	@Transactional
	public List<Work> getWork(Long idWork) {
		
		List<Work> Works = CvRepository.findByIdWork(idWork);
		
		System.out.println("addresses Size : " + Works.size());
		
		return Works;
	}
	
	@Transactional
	public Work updateWork(Long idWork, Work work) {
		EntityManagerFactory emfactory = JpaEntityManager.getInstance();

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Work w = entitymanager.find(Work.class, idWork);
		
		if (work.getTitle() != null) w.setTitle(work.getTitle());
		if (work.getEmployment() != null) w.setEmployment(work.getEmployment());
		if (work.getCompany() != null) w.setCompany(work.getCompany());
		if (work.getLocation() != null) w.setLocation(work.getLocation());
		if (work.getStartDate() != null) w.setStartDate(work.getStartDate());
		if (work.getEndDate() != null) w.setEndDate(work.getEndDate());
		if (work.getDescription() != null) w.setDescription(work.getDescription());
		if (work.getTechnologies() != null) w.setTechnologies(work.getTechnologies());
		
		entitymanager.persist(work);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		
		return work;
	}
}
