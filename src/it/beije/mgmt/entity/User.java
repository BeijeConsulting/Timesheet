package it.beije.mgmt.entity;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import it.beije.mgmt.tool.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user")
@JsonInclude(Include.NON_NULL)
public class User implements Serializable, UserDetails {
	
	private static final long serialVersionUID = 4865903039190150223L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonProperty("first_name")
	@Column(name = "first_name", nullable=false)
	private String firstName;

	@JsonProperty("last_name")
	@Column(name = "last_name", nullable=false)
	private String lastName;
	
	@Column(name = "gender", nullable=false, length=1)
	private String gender;
	
	@Column(name = "email", unique=true, nullable=false)
	private String email;

	@Column(name = "password", nullable=false)
	private String password;

	@Column(name = "phone")
	private String phone;

	@JsonProperty("secondary_email")
	@Column(name = "secondary_email")
	private String secondaryEmail;

	@JsonProperty("fiscal_code")
	@Column(name = "fiscal_code", unique=true)
	private String fiscalCode;

	@JsonProperty("birth_date")
	@Column(name = "birth_date")
	private Date birthDate;
	
	@JsonProperty("birth_place")
	@Column(name = "birth_place")
	private String birthPlace;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "document", unique=true)
	private String document;

	@JsonProperty("skype_id")
	@Column(name = "id_skype")
	private String idSkype;

	@Column(name = "admin")
	private Boolean admin;

	@JsonProperty("archive_date")
	@Column(name = "archive_date")
	private Date archiveDate;

	@Column(name = "note")
	private String note;

	@JsonProperty("pic_url")
	@Column(name = "pic_url")
	private String picUrl;

//	@OneToMany(cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
//	@JoinColumn(name="id_user")
	@Transient
	private List<Address> addresses;

//	@OneToMany(cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
//	@JoinColumn(name="id_user")
//	@Transient
//	private List<BankCredentials> bankCredentials;
//
//	@OneToMany(cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
//	@JoinColumn(name="id_user")
//	@Transient
//	private List<Contract> contracts;

	@Transient
	@JsonProperty("bank_credentials")
	private BankCredentials bankCredentials;

	@Transient
	private Contract contract;

	@Transient
	@JsonProperty("default_timesheet")
	private Timesheet defaultTimesheet;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authority",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	@JsonIgnore
	private List<Authority> authorityEntity = new ArrayList<>();
	
	@JsonIgnore
	@Transient
	private List<String> authority = createStringAuth();
	
	private List<String> createStringAuth() {
		List<String> list = new ArrayList<>();
		for(Authority r : authorityEntity) list.add(r.getAuthority());
		return list;		 
	}
	
	
//	@ElementCollection( fetch = FetchType.EAGER)
//    @Builder.Default
 //   private List<String> authority = new ArrayList<>();
	
	

	
//	@OneToMany(
//		        mappedBy = "user",
//		        cascade = CascadeType.ALL,
//		        orphanRemoval = true /*, fetch=FetchType.EAGER*/
//		    )
//	@Transient
//	@JsonProperty("relative_clients")
//	private List<UserHasClient> relativeClient;
	

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@Override
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
	
	@JsonGetter("birth_date")
	public String getJsonBirthDate() {
		return Utils.formatDate(this.birthDate);
	}
	
	@JsonSetter("birth_date")
	public void setJsonBirthDate(String birthDate) throws ParseException {
		this.birthDate = Utils.parseDate(birthDate);
	}
	
	@JsonGetter("archive_date")
	public String getJsonArchiveDate() {
		return Utils.formatDate(this.archiveDate);
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

	

/*
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
*/
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public BankCredentials getBankCredentials() {
		return bankCredentials;
	}

	public void setBankCredentials(BankCredentials bankCredentials) {
		this.bankCredentials = bankCredentials;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

//	public List<UserHasClient> getRelativeClient() {
//		return relativeClient;
//	}
//
//	public void setRelativeClient(List<UserHasClient> relativeClient) {
//		this.relativeClient = relativeClient;
//	}

	public Timesheet getDefaultTimesheet() {
		return defaultTimesheet;
	}

	public void setDefaultTimesheet(Timesheet defaultTimesheet) {
		this.defaultTimesheet = defaultTimesheet;
	}
	
//	public List<ClientCompany> getClients() {
//		List<ClientCompany> clients = new ArrayList<>();
//		for(UserHasClient uhs : getRelativeClient()) {
//			clients.add(uhs.getCompany());
//		}
//		return clients;
//	}

	public void addAddress(Address address) {
		addresses.add(address);
	}

	public void removeAddress(Address address) {
		addresses.remove(address);
	}

	@JsonIgnore
	public List<String> getAuthority() {
		return authority;
	}


	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authority.stream().map(SimpleGrantedAuthority::new).collect(toList());
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


/*
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
*/
}