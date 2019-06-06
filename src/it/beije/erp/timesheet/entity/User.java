package it.beije.erp.timesheet.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
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
	private int admin;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "archived")
	private Date archived;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String nome) {
		this.firstName = nome;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String cognome) {
		this.lastName = cognome;
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

	public void setWorkEmail(String workEmail) {
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

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int i) {
		this.admin = i;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getArchived() {
		return archived;
	}

	public void setArchived(Date archived) {
		this.archived = archived;
	}

	
	public String toString() {
		StringBuilder builder = new StringBuilder("{\"id\" : ").append(id)
				.append(",").append("\"firstName\" : \"").append(firstName)
				.append("\",").append("\"lastName\" : \"").append(lastName)
				.append("\",").append("\"personalEmail\" : \"").append(personalEmail)
				.append("\"}");
		
		return builder.toString();
	}
}