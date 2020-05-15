package it.beije.mgmt.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.Attachment;
import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.repository.AddressRepository;
import it.beije.mgmt.repository.AttachmentRepository;


@Service
public class AttachmentService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Transactional
	public Attachment create(Long idUser, Attachment attachment) {
		log.debug("POST /attachment/user/{id}");
		try {
			if(attachment.getId()!=null)
				throw new InvalidJSONException("Errore nei dati inviati");
			if (attachment.getUserId()==null)
				attachment.setUserId(idUser);
			else if (attachment.getUserId().longValue() != idUser.longValue())
				throw new ServiceException("Dati non conformi");
			return attachmentRepository.saveAndFlush(attachment);
		}catch(EntityExistsException eee) {
			throw new ServiceException("Indirizzo già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		}catch(MasterException e) {
			throw e;
		}
	}
	
	public List<Attachment> getAttachmentByUser(Long id) {
		log.debug("GET /attachments/user/{user_id}");
		try {
			List<Attachment> attachment = attachmentRepository.findByIdUser(id, Sort.by(Sort.Direction.DESC, "startDate"));
			if (attachment.size()==0)
				throw new NoContentException("La lista è vuota");
			return attachment;
		}catch (MasterException e) {
			throw e;
		}catch (Exception e) {
			throw new ServiceException("Si è verificato un errore");
		}
	}
	
	@Transactional
	public Attachment update(Long id, Attachment attachment) {
		log.debug("PUT /attachment/{id}");
		try {
			Attachment attachment2 = find(id);
	    	if (!Objects.isNull(attachment2.getId())) attachment.setId(attachment2.getId());
	    	
			return attachmentRepository.saveAndFlush(attachment2);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}

	public Attachment find(Long id) {
		log.debug("GET /attachment/{id}");
		try {
			return attachmentRepository.findById(id).get();
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un indirizzo con l'id selezionato o i dati potrebbero essere corrotti");
		}
	}
	
	
}


