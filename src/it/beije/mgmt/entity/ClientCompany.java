package it.beije.mgmt.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "clienti")
@JsonInclude(Include.NON_NULL)
public class ClientCompany implements Serializable {	

	private static final long serialVersionUID = 8007456604984540322L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "business_name", nullable=false)
	@JsonProperty("business_name")
	private String businessName;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;
	
	@Column(name = "postal_code")
	@JsonProperty("postal_code")
	private String postalCode;
	
	@Column(name = "manager_name")
	@JsonProperty("manager_name")
	private String managerName;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="id_client_company")
	@Transient
	private List<Office> offices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}
	
//	@OneToMany(mappedBy = "company",
//			cascade = CascadeType.ALL,
//			orphanRemoval = true)
//	@Transient
//	@JsonProperty("relative_user")
//	private List<UserHasClient> relativeUser;


}
