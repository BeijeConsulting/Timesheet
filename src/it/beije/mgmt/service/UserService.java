package it.beije.mgmt.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.CustomUserDetail;
import it.beije.mgmt.dto.UserSearchRequest;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.DBException;
import it.beije.mgmt.exception.InvalidJSONException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.repository.AddressRepository;
import it.beije.mgmt.repository.BankCredentialsRepository;
import it.beije.mgmt.repository.ContractRepository;
import it.beije.mgmt.repository.SearchCriteria;
import it.beije.mgmt.repository.SearchOperation;
import it.beije.mgmt.repository.TimesheetRepository;
import it.beije.mgmt.repository.UserRepository;
import it.beije.mgmt.repository.UserSpecification;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private BankCredentialsRepository bankCredentialsRepository;
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private TimesheetRepository timesheetRepository;

	/**
	 * @param idUser
	 * @return
	 * @throws MasterException 
	 * @throws DBException 
	 */
	
	/**
	 * QUESTO METODO CARICA TUTTI GLI UTENTI DAL DATABASE E LI TRASFERISCE IN UNA LISTA DI USERDTO
	 * @return
	 * @throws ServiceException 
	 */
	public List<User> findAll() {
		
		List<User> completeUsers = userRepository.findAll();
		
		if(completeUsers.size()==0)
			throw new NoContentException("La lista è vuota");
		
		try {
			List<User> users = new ArrayList<>();
			
			for(User u : completeUsers) {
				User userDto = new User();
				BeanUtils.copyProperties(u, userDto, "gender", "password", "secondaryEmail", "fiscalCode", "birthDate", "birthPlace", "nationality",
						"document", "idSkype", "admin", "note",  "addresses", "bankCredentials", "contracts", "defaultTimesheet", "picUrl");
				users.add(userDto);
			}
			return users;
		}catch(Exception e) {
			throw new ServiceException("Errore di conversione in \"UserService\"");
		}
	}
	
	public User find(Long idUser, boolean complete) {
		
		User userDto = new User();
		try {
			//User user = userRepository.findById(idUser).get();
			UserSpecification spFindId = new UserSpecification();
			spFindId.add(new SearchCriteria("id", idUser, SearchOperation.EQUAL));
			User user = userRepository.findOne(spFindId).get();
			
			if (complete) {
				fillUser(user);
				BeanUtils.copyProperties(user, userDto, "password", "admin");
				user.setPassword(null);
				return user;
				
			} else {
				BeanUtils.copyProperties(user, userDto, "gender", "password", "secondaryEmail", "fiscalCode", "birthDate", "birthPlace", "nationality",
						"document", "idSkype", "admin", "archiveDate", "note",  "addresses", "bankCredentials", "contracts", "defaultTimesheet", "picUrl");
			}
			return userDto;
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un utente con l'id selezionato o i dati potrebbero essere corrotti");
		} catch (MasterException e) {
			throw e;
		} catch (BeansException e) {
			throw new ServiceException("Non è stato possibile convertire i dati selezionati");
		}
	}
	
	private void fillUser(User user) {
		Long idUser = user.getId();
		if (user.getPicUrl() == null || user.getPicUrl().length() == 0) {
			user.setPicUrl("https://beije.s3.eu-west-1.amazonaws.com/abstract-user-flat-1.png"); //IM 20200420 : avatar di default
		}
		user.setAddresses(addressRepository.findByIdUserAndEndDate(idUser, null));
		user.setBankCredentials(bankCredentialsRepository.findByIdUserAndEndDate(idUser, null));
		user.setContract(contractRepository.findByIdUserAndEndDate(idUser, null));
		user.setDefaultTimesheet(timesheetRepository.findByIdUserAndType(idUser, "D"));
	}
	
	/**
	 * FUNZIONA: Crea un nuovo utente sul DB e restituisce l'oggetto
	 * @param user
	 * @return
	 * @throws MasterException 
	 */
	@Transactional
	public User create(User user) {
		
		try {
			if(user.getId()!=null || user.getLastName()==null || user.getEmail()==null || user.getGender()==null
					|| !user.getGender().equalsIgnoreCase("m") || !user.getGender().equalsIgnoreCase("f"))
				
				throw new InvalidJSONException("Errore nei dati inviati");
			return userRepository.saveAndFlush(user);
		}catch(EntityExistsException eee) {
			throw new ServiceException("User già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}
	
	/**
	 * @param id
	 * @param userData
	 * @return
	 * @throws DBException 
	 */
	@Transactional
	public User update(User userData) {
		
		try {
			User user = userRepository.findById(userData.getId()).get();
			
			if (userData.getFirstName() != null) user.setFirstName(userData.getFirstName());
	    	if (userData.getLastName() != null) user.setLastName(userData.getLastName());
	    	if (userData.getGender() != null && (userData.getGender().equalsIgnoreCase("m") || userData.getGender().equalsIgnoreCase("f"))) user.setGender(userData.getGender());
	    	if (userData.getEmail() != null) user.setEmail(userData.getEmail());
	    	if (userData.getPassword() != null) user.setPassword(userData.getPassword());
	    	if (userData.getPhone() != null) user.setPhone(userData.getPhone());
	    	if (userData.getSecondaryEmail() != null) user.setSecondaryEmail(userData.getSecondaryEmail());
	    	if (userData.getFiscalCode() != null) user.setFiscalCode(userData.getFiscalCode());
	    	if (userData.getBirthDate() != null) user.setBirthDate(userData.getBirthDate());
	    	if (userData.getBirthPlace() != null) user.setBirthPlace(userData.getBirthPlace());
	    	if (userData.getNationality() != null) user.setNationality(userData.getNationality());
	    	if (userData.getDocument() != null) user.setDocument(userData.getDocument());
	    	if (userData.getIdSkype() != null) user.setIdSkype(userData.getIdSkype());
	    	if (userData.getArchiveDate() != null) user.setArchiveDate(userData.getArchiveDate());
	    	if (userData.getNote() != null) user.setNote(userData.getNote());
	    	if (userData.getPicUrl() != null) user.setPicUrl(userData.getPicUrl());
	    	
			return userRepository.saveAndFlush(user);
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un utente con l'id selezionato o i dati potrebbero essere corrotti");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}
	
	/**
	 * QUESTO METODO SERVE PER INSERIRE LA DATA DI ARCHIVIAZIONE DI UN UTENTE DALLE JSP
	 * @param user
	 */
	@Transactional
	public boolean dismissUser(User user) {
		
		try {
			User archived = new User();
			archived.setArchiveDate(Date.valueOf(LocalDate.now()));
			update(archived);	
		}catch(MasterException e) {
			return false;
		}
		return true;	
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Optional<User> user = userRepository.findByEmail(username);
			user.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
			return user.map(CustomUserDetail::new).get();
		} catch (Exception e) {
			throw e;
		}		
	}
		
	@Transactional
	public List<User> searchUser(UserSearchRequest req) {
		// TODO Auto-generated method stub
		return searchUser(req.getFirst_name(),req.getLast_name(),req.getEmail(),req.getFiscal_code());
	}
	private List<User> searchUser(String firstName, String lastName, String email, String fiscalCode) {

		List<User> list = new ArrayList<>();
		UserSpecification spSearch = new UserSpecification();
		
		if (firstName != null && firstName.length()>0)
			spSearch.add(new SearchCriteria("firstName", firstName, SearchOperation.MATCH));
		if (lastName != null && lastName.length()>0) 
			spSearch.add(new SearchCriteria("lastName", lastName, SearchOperation.MATCH));
		if (email != null && email.length()>0) 
			spSearch.add(new SearchCriteria("email", email, SearchOperation.MATCH));
		if (fiscalCode != null && fiscalCode.length()>0) 
			spSearch.add(new SearchCriteria("fiscalCode", fiscalCode, SearchOperation.MATCH));
		
		list.addAll(userRepository.findAll(spSearch));
		return list;
	}
	

	public User getShortVersion(User user) {
		User u = new User();
		u.setId(user.getId());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setPhone(user.getPhone());
		return u;
	}
	
	/*	
	public User findById(Long idUser) {
		
		try {
			User user = userRepository.findById(idUser).get();
			fillUserLists(user);
			return user;
		}catch (EntityNotFoundException | IllegalArgumentException | NoSuchElementException e) {
			throw new NoContentException("Non è stato trovato un utente con l'id selezionato o i dati potrebbero essere corrotti");
		} catch (DBException e) {
			throw e;
		}
	}
*/
}
