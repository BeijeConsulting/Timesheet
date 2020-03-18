package it.beije.mgmt.repository;

import java.util.List;

import it.beije.mgmt.entity.cv.Work;

public interface CVRepository {
	List<Work> findByIdWork(Long idWork);
}
