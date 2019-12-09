package it.beije.erp.entity;


import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 4865903039190150223L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", nullable=false)
	private String firstName;

	@Column(name = "last_name", nullable=false)
	private String lastName;

	@Column(name = "email", unique=true, nullable=false)
	private String email;

	@Column(name = "password", nullable=false)
	private String password;

	@Column(name = "phone")
	private String phone;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "secondary_email")
	private String secondaryEmail;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "fiscal_code", unique=true)
	private String fiscalCode;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "birth_date")
	private Date birthDate;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "birth_place")
	private String birthPlace;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "nationality")
	private String nationality;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "document", unique=true)
	private String document;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "id_skype")
	private String idSkype;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "admin")
	private Boolean admin;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "archive_date")
	private Date archiveDate;

	//	@JsonInclude(Include.NON_NULL)
	@Column(name = "note")
	private String note;
	
	@Column(name = "pic_url")
	private String picUrl;

	@OneToMany
	@JoinColumn(name="id_user")
	private List<Address> addresses;

	@OneToMany
	@JoinColumn(name="id_user")
	private List<BankCredentials> bankCredentials;

	@OneToMany
	@JoinColumn(name="id_user")
	private List<Contract> contracts;

	public User() {
		super();
	}

	public User(User user) {
		super();
		this.id = user.id;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.secondaryEmail = user.secondaryEmail;
		this.phone = user.phone;
		this.fiscalCode = user.fiscalCode;
		this.birthDate = user.birthDate;
		this.document = user.document;
		this.idSkype = user.idSkype;
		this.admin = user.admin;
		this.password = user.password;
		this.archiveDate = user.archiveDate;
		this.note = user.note;
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


	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
	
	public Boolean getAdmin() {
		return admin;
	}

	public Boolean isAdmin() {
		return this.admin;
	}

	public void setAdmin(Boolean admin) {
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
	
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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