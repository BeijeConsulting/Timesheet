package it.beije.mgmt.entity;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "company")
public class ClientCompany implements Serializable {	

	private static final long serialVersionUID = 8007456604984540322L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", nullable=false)
	@JsonProperty("first_name")
	private String firstName;

	@Column(name = "last_name", nullable=false)
	@JsonProperty("last_name")
	private String lastName;

	@Column(name = "email", unique=true, nullable=false)
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "secondary_email")
	@JsonProperty("secondary_email")
	private String secondaryEmail;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="id_client_company")
	@Transient
	private List<Office> offices;
	
//	@OneToMany(mappedBy = "company",
//			cascade = CascadeType.ALL,
//			orphanRemoval = true)
//	@Transient
//	@JsonProperty("relative_user")
//	private List<UserHasClient> relativeUser;

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

	public String getLastName() {
		return lastName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

//	public List<UserHasClient> getRelativeUser() {
//		return relativeUser;
//	}
//
//	public void setRelativeUser(List<UserHasClient> relativeUser) {
//		this.relativeUser = relativeUser;
//	}
	
	public ClientCompany() {
		super();
	}

	public ClientCompany(ClientCompany client) {
		super();
		this.id = client.id;
		this.firstName = client.firstName;
		this.lastName = client.lastName;
		this.email = client.email;
		this.phone = client.phone;
		this.secondaryEmail = client.secondaryEmail;
		this.offices = client.offices;
//		this.relativeUser = client.relativeUser;
	}
}
