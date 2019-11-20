package it.beije.erp.entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToMany
	@JoinColumn(name="id")
	private List<Address> addresses;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id")
	private List<BankCredentials> bankCredentials;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id")
	private List<Contract> contracts;
	
	@OneToMany
	@JoinColumn(name = "id_user")
	private List<UserComputer> computers;

	@Column(name = "first_name", nullable=false)
	private String firstName;

	@Column(name = "last_name", nullable=false)
	private String lastName;

	@Column(name = "email", unique=true, nullable=false)
	private String email;

	@Column(name = "password", nullable=false)
	private String password;

	@Column(name = "secondary_email")
	private String secondaryEmail;

	@Column(name = "phone")
	private String phone;

	@Column(name = "fiscal_code", unique=true)
	private String fiscalCode;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "birth_place")
	private String birthPlace;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "document", unique=true)
	private String document;

	@Column(name = "id_skype")
	private String idSkype;

	@Column(name = "admin")
	private boolean admin;

	@Column(name = "archive_date")
	private Date archiveDate;

	@Column(name = "note")
	private String note;

	public User() {
		
	}
	
	public User(User user) {
		this.id = user.id;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.secondaryEmail = user.secondaryEmail;
		this.phone = user.phone;
		this.fiscalCode = user.fiscalCode;
		this.birthDate = user.birthDate;
		this.birthPlace = user.birthPlace;
		this.nationality = user.nationality;
		this.document = user.document;
		this.idSkype = user.idSkype;
		this.admin = user.admin;
		this.password = user.password;
		this.archiveDate = user.archiveDate;
		this.note = user.note;
	}

	public String getBirthplace() {
		return birthPlace;
	}

	public void setBirthplace(String birthplace) {
		this.birthPlace = birthplace;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public Date getBirthdate() {
		return birthDate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthDate = birthdate;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public boolean isAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getIdSkype() {
		return idSkype;
	}

	public void setIdSkype(String idSkype) {
		this.idSkype = idSkype;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<BankCredentials> getBankCredentials() {
		return bankCredentials;
	}
	

	public void setBankCredentials(List<BankCredentials> bankCredentials) {
		this.bankCredentials = bankCredentials;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public void addAddress(Address address) {
		addresses.add(address);
	}

	public void removeAddress(Address address) {
		addresses.remove(address);
	}

	public void addBankCredentials(BankCredentials credentials) {
		bankCredentials.add(credentials);
	}

	public void removeBankCredentials(BankCredentials credentials) {
		bankCredentials.remove(credentials);
	}

	public void addContract(Contract contract) {
		contracts.add(contract);
	}

	public void removeContractType(Contract contract) {
		contracts.remove(contract);
	}

}