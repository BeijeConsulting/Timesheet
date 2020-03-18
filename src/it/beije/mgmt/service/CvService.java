package it.beije.mgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.beije.mgmt.entity.cv.CV;
import it.beije.mgmt.repository.CvRepository;

@Service
public class CvService {
	@Autowired
	private CvRepository cvRepository;
	
	public List<CV> findCvByTechnology(String technology) throws Exception {
		List<CV> curricula = cvRepository.findByTechnology(technology);
		System.out.println("Nunmber of CV : " + curricula.size());
		return curricula;
		
		
	}
	


}
