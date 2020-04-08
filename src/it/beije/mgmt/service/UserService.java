package it.beije.mgmt.service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.beije.mgmt.CustomUserDetail;
import it.beije.mgmt.dto.UserDto;
import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.exception.DBException;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.exception.NoContentException;
import it.beije.mgmt.exception.ServiceException;
import it.beije.mgmt.jpa.JpaEntityManager;
import it.beije.mgmt.jpa.UserRequest;
import it.beije.mgmt.repository.UserRepository;
import it.beije.mgmt.repository.UserRepositoryCustom;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRepositoryCustom userRepositoryCustom;

	/**
	 * NON FUNGE CORRETTAMENTE VEDI COMMENTI
	 * @param id
	 * @return
	 * @throws MasterException 
	 * @throws DBException 
	 */
	@Transactional
	public User find(Long id) throws MasterException {
		
		try {
			return userRepository.getOne(id);
		}catch (EntityNotFoundException e) {
			throw new NoContentException("Non è stato trovato un utente con l'id selezionato o i dati potrebbero essere corrotti");
		} catch (DBException e) {
			throw e;
		}
	}
	
		/** FUNZIONA: Questo metodo prima carica l'utente dal database con la query, passa al dto con il metodo BeanUtils.copyProperties ed ignora
		 * le proprietà elencate.
		 * 
		 * Se non viene trovato alcun utente tramite l'id, restituisce un utente vuoto
		 * 
		 * @param id parametro in ingresso per trovare l'utente sul database
		 * @return
		 * @throws MasterException 
		 */
	
	public UserDto findApi(Long id, boolean complete) throws MasterException {
		
		UserDto userDto = new UserDto();
		try {
			User user = find(id);
			if(complete)
				BeanUtils.copyProperties(user, userDto, "password", "secondaryEmail", "fiscalCode", "birthDate", "birthPlace", "nationality",
						"document", "idSkype", "admin", "archiveDate", "note", "addresses", "bankCredentials", "contracts");
			else
				BeanUtils.copyProperties(user, userDto, "password", "secondaryEmail", "fiscalCode", "birthDate", "birthPlace", "nationality",
						"document", "idSkype", "admin", "archiveDate", "note");
			return userDto;
		} catch (DBException e) {
			throw e;
		} catch (BeansException e) {
			throw new ServiceException("Non è stato possibile convertire i dati selezionati");
		}
	}
		/** FUNZIONA: Questo metodo prima carica l'utente dal database con la query, passa al dto con il metodo BeanUtils.copyProperties ed ignora
		 * le proprietà password ed admin
		 * 
		 * Se non viene trovato alcun utente tramite l'id, restituisce un utente vuoto
		 * 
		 * Le tre query restituiscono le 3 relative liste collegate ad User (Address,BankCredentials e Contract) validi
		 * 
		 * 
		 * Infine restituisce uno userDto con 2 indirizzi, un oggetto BankCredentials ed un oggetto Contract, all'interno di User
		 * 
		 * @param id parametro in ingresso per trovare l'utente sul database
		 * @return
		 */
//		public UserDto findApiLong(Long id) {
//			
//			EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//			EntityManager entitymanager = emfactory.createEntityManager();
//			User user;
//			UserDto userDto = new UserDto();
//			try {
//				user = entitymanager.createQuery("SELECT u FROM User u WHERE u.id = "+id,User.class).getSingleResult();
//				user.setAddresses(entitymanager.createQuery("Select a FROM Address a "
//																	+ "WHERE idUser="+id+" and a.startDate < current_date() and"
//																	+ " (a.endDate > current_date() or a.endDate is null)",
//																	Address.class).getResultList());
//				
//				user.setBankCredentials(entitymanager.createQuery("Select b FROM BankCredentials b "
//																	+ "WHERE idUser="+id+" and b.startDate < current_date() and"
//																	+ " (b.endDate > current_date() or b.endDate is null)",
//																	BankCredentials.class).getResultList());
//				
//				user.setContracts(entitymanager.createQuery("Select c FROM Contract c "
//															+ "WHERE idUser="+id+" and c.startDate < current_date() and"
//															+ " (c.endDate > current_date() or c.endDate is null)",
//															Contract.class).getResultList());
//				
//				BeanUtils.copyProperties(user, userDto, "password", "admin");
//				
//				Address[] support = new Address[user.getAddresses().size()];
//				support = user.getAddresses().toArray(support);
//				
//				if(user.getAddresses().size()>0) userDto.setAddresses(support);
//				else userDto.setAddresses(new Address[0]);
//					
//				if (user.getContracts().size()>0) userDto.setContract(user.getContracts().get(0));
//				else userDto.setContract(null);
//				
//				if (user.getBankCredentials().size()>0) userDto.setBankCredential(user.getBankCredentials().get(0));
//				else userDto.setBankCredential(null);
//				
//				return userDto;
//				
//			} catch (NoResultException e) {
//				throw new NoContentException("Non è stato trovato un utente con l'id selezionato o i dati potrebbero essere corrotti");
//			}finally {
//				entitymanager.close();
//			}
//		}
	
	/**
	 * FUNZIONA: Crea un nuovo utente sul DB e restituisce l'oggetto
	 * @param user
	 * @return
	 * @throws MasterException 
	 */
	public User create(User user) throws MasterException {
		try {
			return userRepository.saveAndFlush(user);
		}catch(EntityExistsException eee) {
			throw new ServiceException("User già presente nel database");
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (DBException e) {
			throw e;
		}
	}
	
	/**
	 * NON FUNZIONA: LAZILY INITIALIZE, MODIFICA SUL DB MA NON RITORNA IL JSON CON LE MODIFICHE
	 * @param id
	 * @param userData
	 * @return
	 * @throws DBException 
	 */
	@Transactional
	public User update(Long id, User userData) throws MasterException {
		
		try {
			User user = find(id);
			
			if (userData.getFirstName() != null) user.setFirstName(userData.getFirstName());
	    	if (userData.getLastName() != null) user.setLastName(userData.getLastName());
	    	if (userData.getEmail() != null) user.setEmail(userData.getEmail());
	    	if (userData.getSecondaryEmail() != null) user.setSecondaryEmail(userData.getSecondaryEmail());
	    	if (userData.getPhone() != null) user.setPhone(userData.getPhone());
	    	if (userData.getFiscalCode() != null) user.setFiscalCode(userData.getFiscalCode());
	    	if (userData.getBirthDate() != null) user.setBirthDate(userData.getBirthDate());
	    	if (userData.getBirthPlace() != null) user.setBirthPlace(userData.getBirthPlace());
	    	if (userData.getNationality() != null) user.setNationality(userData.getNationality());
	    	if (userData.getDocument() != null) user.setDocument(userData.getDocument());
	    	if (userData.getIdSkype() != null) user.setIdSkype(userData.getIdSkype());
	    	if (userData.getNote() != null) user.setNote(userData.getNote());
	    	if (userData.getArchiveDate() != null) user.setArchiveDate(userData.getArchiveDate());
	    	if (userData.getPassword() != null) user.setPassword(userData.getPassword());
	    	
			return userRepository.saveAndFlush(user);
		}catch(IllegalStateException  | PersistenceException e) {
			throw new ServiceException("Al momento non è possibile soddisfare la richiesta");
		} catch (MasterException e) {
			throw e;
		}
	}
	
	
	public List<UserDto> trovaUtente(UserRequest req) {
		// TODO Auto-generated method stub
		return trovaUtente(req.getFirst_name(),req.getLast_name(),req.getEmail(),req.getFiscal_code());
	}
	
	/**
	 * QUESTO METODO RICERCA TUTTI GLI UTENTI DATI IN INPUT NOME|COGNOME|EMAIL|CODICE FISCALE
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param fiscalCode
	 * @return
	 */
	/***********************************************************EDIT***************************************************/
	@Transactional
	public List<UserDto> trovaUtente(String firstName, String lastName, String email, String fiscalCode) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		
		List<String> searchQuery=new ArrayList<>();
		String whereClause="";
		
		if (firstName != null && firstName.length()>0) {
			searchQuery.add("a.firstName LIKE '%"+firstName+"%'");
			whereClause+="WHERE ";
		}
		if (lastName != null && lastName.length()>0) {
			searchQuery.add("a.lastName LIKE '%"+lastName+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		if (email != null && email.length()>0) {
			searchQuery.add("a.email LIKE '%"+email+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		if (fiscalCode != null && fiscalCode.length()>0) {
			searchQuery.add("a.fiscalCode LIKE '%"+fiscalCode+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		
		System.out.println("Sto cercando");
		
		for (int i=0;i<searchQuery.size();i++) {
			whereClause+=searchQuery.get(i);
			if (i!=searchQuery.size()-1)
				whereClause+=" OR ";
		}
		
		TypedQuery<User> query=entitymanager.createQuery("SELECT a from User a "+whereClause,User.class);
		
		List<User> userlist=query.getResultList();
		
		List<UserDto> users = new ArrayList<>();
		
		users=userlist.stream().map(e -> UserDto.valueOf(e)).collect(Collectors.toList());
		
		entitymanager.close();
		
		return users;
	}
	
	/**
	 * QUESTO METODO CARICA TUTTI GLI UTENTI DAL DATABASE E LI TRASFERISCE IN UNA LISTA DI USERDTO
	 * @return
	 * @throws ServiceException 
	 */
	public List<UserDto> caricaTutti() throws ServiceException {
		
		List<User> completeUsers = userRepository.findAll();
		List<UserDto> users = new ArrayList<>();
		if(completeUsers.size()==0)
			throw new NoContentException("La lista è vuota");
		try {
			users=completeUsers.stream()
					.filter(e -> e.getArchiveDate()==null)
					.map(e -> UserDto.valueOf(e))
					.collect(Collectors.toList());
			return users;
		}catch(Exception e) {
			throw new ServiceException("Errore di conversione in \"UserService\"");
		}
	}
	
	/**
	 * QUESTO METODO SERVE PER MODIFICARE I DATI DALLE JSP
	 * @param user
	 * @throws MasterException 
	 */
	public void modificaUtente(User userData) throws MasterException {
		
		try {
			update(userData.getId(), userData);
		} catch (MasterException e) {
			throw e;
		}

//		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
//		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.getTransaction().begin();
//		//		System.out.println("sono nel metodo modificaUtente");
//		
//		User user = entitymanager.find(User.class, userData.getId());
//    	
//    	if (userData.getFirstName() != null) user.setFirstName(userData.getFirstName());
//    	if (userData.getLastName() != null) user.setLastName(userData.getLastName());
//    	if (userData.getEmail() != null) user.setEmail(userData.getEmail());
//    	if (userData.getSecondaryEmail() != null) user.setSecondaryEmail(userData.getSecondaryEmail());
//    	if (userData.getPhone() != null) user.setPhone(userData.getPhone());
//    	if (userData.getFiscalCode() != null) user.setFiscalCode(userData.getFiscalCode());
//    	if (userData.getBirthDate() != null) user.setBirthDate(userData.getBirthDate());
//    	if (userData.getBirthPlace() != null) user.setBirthPlace(userData.getBirthPlace());
//    	if (userData.getNationality() != null) user.setNationality(userData.getNationality());
//    	if (userData.getDocument() != null) user.setDocument(userData.getDocument());
//    	if (userData.getIdSkype() != null) user.setIdSkype(userData.getIdSkype());
//    	if (userData.getNote() != null) user.setNote(userData.getNote());
//    	if (userData.getPassword() != null) user.setPassword(userData.getPassword());
//    		
//    	System.out.println("user.getAddresses() ? " + (user.getAddresses() != null ? user.getAddresses().size() : "NULL"));
//    	System.out.println("user.getBankCredentials() ? " + (user.getBankCredentials() != null ? user.getBankCredentials().size() : "NULL"));
//    	System.out.println("user.getContracts() ? " + (user.getContracts() != null ? user.getContracts().size() : "NULL"));
//
//		entitymanager.persist(user);
//		entitymanager.getTransaction().commit();
//		
//		entitymanager.close();
	}

	/**
	 * QUESTO METODO SERVE PER INSERIRE LA DATA DI ARCHIVIAZIONE DI UN UTENTE DALLE JSP
	 * @param user
	 */
	public boolean archiviaUtente(User user) {
		
//		LocalDate data = LocalDate.now();
//		EntityManager entitymanager = null;
		try {
			User archived = new User();
			archived.setArchiveDate(Date.valueOf(LocalDate.now()));
			update(archived.getId(), archived);
			
//			entitymanager = JpaEntityManager.getInstance().createEntityManager();
//			entitymanager.getTransaction().begin();
//			
//			System.out.println("sono nel metodo archiviaUtente");
//			String modifica="UPDATE User a SET";
//		
//			modifica += " a.archiveDate= '" + data + "' ";
//		
//			modifica += " WHERE a.id= "+user.getId();
//		
//			System.out.println(modifica);
//		
//			Query q = entitymanager.createQuery(modifica);
//			int rowsUpdated = q.executeUpdate();
//		
//			//System.out.println(rowsUpdated);
//			entitymanager.getTransaction().commit();
		
		}catch(MasterException e) {
			return false;
		}
//		}finally{
//			entitymanager.close();
//		}
		return true;	
	}

	/**********************************************************EDIT****************************************************/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Optional<User> user = userRepository.findByEmail(username);
			user.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
			return user.map(CustomUserDetail::new).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}		
	}
	
	/**
	 * QUESTO E' IL METODO PER RITORNARE LA LISTA DEGLI UTENTI CON TUTTI I DETTAGLI E LO STORICO(CLASSE USER
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param fiscalCode
	 * @return
	 */
	@Transactional
	public List<User> trovaUtenti(String firstName, String lastName, String email, String fiscalCode) {

		EntityManagerFactory emfactory = JpaEntityManager.getInstance();
		EntityManager entitymanager = emfactory.createEntityManager();
		
		List<String> searchQuery=new ArrayList<>();
		String whereClause="";
		
		if (firstName != null && firstName.length()>0) {
			searchQuery.add("a.firstName LIKE '%"+firstName+"%'");
			whereClause+="WHERE ";
		}
		if (lastName != null && lastName.length()>0) {
			searchQuery.add("a.lastName LIKE '%"+lastName+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		if (email != null && email.length()>0) {
			searchQuery.add("a.email LIKE '%"+email+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		if (fiscalCode != null && fiscalCode.length()>0) {
 			searchQuery.add("a.fiscalCode LIKE '%"+fiscalCode+"%'");
			if (whereClause.length()==0)
				whereClause+="WHERE ";
		}
		
		System.out.println("Sto cercando");
		
		for (int i=0;i<searchQuery.size();i++) {
			whereClause+=searchQuery.get(i);
			if (i!=searchQuery.size()-1)
				whereClause+=" AND ";
		}
		
		TypedQuery<User> query=entitymanager.createQuery("SELECT a from User a "+whereClause,User.class);
		
		List<User> userlist=query.getResultList();
		
		entitymanager.close();
		

		return userlist;
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
}
