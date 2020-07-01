package it.beije.mgmt.service;

import java.util.List;
import java.util.Objects;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.model.Refund;
import it.beije.mgmt.repository.RefundRepository;

@Service
public class RefundService {

	
	@Autowired
	private RefundRepository refundRepository;
	
	public List<Refund> getRefunds() {
		
		try {
			List<Refund> refunds = refundRepository.findAll();
			if (refunds.size()==0)
				throw new NoContentException("La lista è vuota");
			return refunds;
		}catch (MasterException e) {
			throw e;
		}catch (Exception e) {
			throw new ServiceException("Si è verificato un errore");
		}
	}

	@Transactional
	public Refund create(Long idUser, Refund refund) {
		
		try {
			if(refund.getId()!=null)
				throw new InvalidJSONException("Errore nei dati inviati");
			if (refund.getIdUser()==null)
				refund.setIdUser(idUser);
			else if (refund.getIdUser().longValue() != idUser.longValue())
				throw new ServiceException("Dati non conformi");
			return refundRepository.saveAndFlush(refund);
		}catch(IllegalStateException  | PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		}catch(MasterException e) {
			throw e;
		}
	}

	
	@Transactional
	public Refund update(Long id, Refund refundNew) {

		if (refundNew.getIdUser() == null) {
			throw new InvalidJSONException("Errore nei dati inviati");
		}
		
		try {
			Refund refund = refundRepository.findById(id).get();
    	
	    	if (!Objects.isNull(refundNew.getReceiptDate())) refund.setReceiptDate(refundNew.getReceiptDate());
	    	if (refundNew.getDesc() != null) refund.setDesc(refundNew.getDesc());
	    	if (refundNew.getAmount() != null) refund.setAmount(refundNew.getAmount());
	    	if (!Objects.isNull(refundNew.getPath())) refund.setPath(refundNew.getPath());
	    	if (!Objects.isNull(refundNew.getPaymentDate())) refund.setPaymentDate(refundNew.getPaymentDate());
	    	if (!Objects.isNull(refundNew.getTimestamp())) refund.setTimestamp(refundNew.getTimestamp());
	    	
			return refundRepository.saveAndFlush(refund);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}
}
