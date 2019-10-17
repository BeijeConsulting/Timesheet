package it.beije.erp.timesheet.entity;


import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "work_email")
	private String email;
	
	@Column(name = "personal_email")
	private String secondaryEmail;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "fiscal_code")
	private String fiscalCode;
	
	@Column(name = "admin")
	private boolean admin;
	
	@Column(name = "password")
	private String password;

	@Column(name = "archived")
	private Date archived;
	
	@Column(name = "note")
	private String note;

	
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
		return archived;
	}
	public void setArchived(Date archived) {
		this.archived = archived;
	}
}
	
