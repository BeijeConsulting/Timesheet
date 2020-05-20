package it.beije.mgmt.repository;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> , JpaSpecificationExecutor<Attachment> {


		List<Attachment> findByUserId(Long id, Sort by);
		
		

	}

