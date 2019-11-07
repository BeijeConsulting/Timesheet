package it.beije.erp.entity;


import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	

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
		super();
	}

	public User(User user) {super();
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

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
	
	public String getIdskype() {
		return idSkype;
	}
	
	public void setIdskype(String idskype) {
		this.idSkype = idskype;
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

	public Date getArchived() {
		return archiveDate;
	}
	
	public void setArchived(Date archiveDate) {
		this.archiveDate = archiveDate;
	}
}
	
