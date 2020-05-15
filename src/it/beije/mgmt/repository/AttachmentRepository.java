package it.beije.mgmt.repository;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.beije.mgmt.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> , JpaSpecificationExecutor<Attachment> {

	
		
		
		Optional<Attachment> findById(Long id);

		List<Attachment> findByIdUser(Long id, Sort by);
		
		

	}

