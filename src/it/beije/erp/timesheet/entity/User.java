package it.beije.erp.timesheet.entity;


import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "personal_email")
	private String personalEmail;
	
	@Column(name = "work_email")
	private String workEmail;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "fiscal_code")
	private String fiscalCode;
	
	@Column(name = "admin")
	private Boolean admin;
	
	@Column(name = "password")
	private String password;

	@Column(name = "archived")
	private Date archived;
	
	public Date getArchived() {
		return archived;
	}
	public void setArchived(Date archived) {
		this.archived = archived;
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
	public void setFirst_name(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String last_name) {
		this.lastName = lastName;
	}
	
	
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	
	
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String work_email) {
		this.workEmail = workEmail;
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
	
	
	public Boolean getAdmin() {
		return admin;
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
}
	
