/*package it.beije.mgmt.dto;


import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.BankCredentials;
import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.entity.User;

@JsonInclude(Include.NON_NULL)
public class UserDto implements Serializable {
	
    private static final long serialVersionUID = 4865903039190150223L;

	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String phone;
	private String password;
	private String secondaryEmail;
	
	private String fiscalCode;
	
	@JsonProperty("birth_date")
	private Date birthDate;
	
	@JsonProperty("birth_place")
	private String birthPlace;
	
	private String nationality;
	private String document;
	
	@JsonProperty("skype_id")
	private String idSkype;
	
	private Boolean admin;
	
	@JsonProperty("archive_date")
	private Date archiveDate;
	
	private String note;
	
	@JsonProperty("pic_url")
	private String picUrl;

	private Address addresses[];
	private BankCredentials bankCredential;
	private Contract contract;
	private Timesheet defaultTimesheet;

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

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getIdSkype() {
		return idSkype;
	}

	public void setIdSkype(String idSkype) {
		this.idSkype = idSkype;
	}

	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Address[] getAddresses() {
		return addresses;
	}

	public void setAddresses(Address[] addresses) {
		this.addresses = addresses;
	}

	public BankCredentials getBankCredential() {
		return bankCredential;
	}

	public void setBankCredential(BankCredentials bankCredential) {
		this.bankCredential = bankCredential;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	
	public Timesheet getDefaultTimesheet() {
		return defaultTimesheet;
	}

	public void setDefaultTimesheet(Timesheet defaultTimesheet) {
		this.defaultTimesheet = defaultTimesheet;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static UserDto valueOf(User user) {
        UserDto dto = new UserDto();
        dto.setId(Long.valueOf(user.getId()));
        dto.setFirstName(String.valueOf(user.getFirstName()));
        dto.setLastName(String.valueOf(user.getLastName()));
        dto.setEmail(String.valueOf(user.getEmail()));
        dto.setPhone(String.valueOf(user.getPhone()));
        
        return dto;
    }

	public boolean isEmpty() {
        if(this.id==null) return true;
        else return false;
    }
}*/