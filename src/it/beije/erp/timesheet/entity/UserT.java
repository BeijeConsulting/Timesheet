package it.beije.erp.timesheet.entity;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserT {
	
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "personal_email")
	private String personal_email;
	
	@Column(name = "work_email")
	private String work_email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "fiscal_code")
	private String fiscal_code;
	
	@Column(name = "admin")
	private Boolean admin;
	
	@Column(name = "password")
	private String password;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	
	public String getPersonal_email() {
		return personal_email;
	}
	public void setPersonal_email(String personal_email) {
		this.personal_email = personal_email;
	}
	
	
	public String getWork_email() {
		return work_email;
	}
	public void setWork_email(String work_email) {
		this.work_email = work_email;
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getFiscal_code() {
		return fiscal_code;
	}
	public void setFiscal_code(String fiscal_code) {
		this.fiscal_code = fiscal_code;
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
	
