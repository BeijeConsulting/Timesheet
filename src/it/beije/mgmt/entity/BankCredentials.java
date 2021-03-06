package it.beije.mgmt.entity;

import java.sql.Date;
import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import it.beije.mgmt.tool.Utils;

@Table(name = "bank_credentials")
@Entity
public class BankCredentials {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="id_user")
	@JsonProperty("user_id")
	private Long idUser;
	
	@Column(name="accountholder")
	private String accountholder;
	
	@Column(name="iban")
	private String iban;
	
	@Column(name="swift")
	private String swift;
	
	@Column(name="start_date")
	@JsonProperty("start_date")
	private Date startDate;
	
	@Column(name="end_date")
	@JsonProperty("end_date")
	private Date endDate;
	
	@Column(name="notes")
	private String notes;
	
	public BankCredentials() {
		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getAccountholder() {
		return accountholder;
	}



	public void setAccountholder(String accountholder) {
		this.accountholder = accountholder;
	}



	public String getIban() {
		return iban;
	}



	public void setIban(String iban) {
		this.iban = iban;
	}



	public String getSwift() {
		return swift;
	}



	public void setSwift(String swift) {
		this.swift = swift;
	}



	public Date getStartDate() {
		return startDate;
	}

	@JsonGetter("start_date")
	public String getJsonStartDate() {
		return Utils.formatDate(this.startDate);
	}

//	@DateTimeFormat(pattern="dd-MM-yyyy")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonSetter
	public void setJsonStartDate(String startDate) throws ParseException {
		this.startDate = Utils.parseDate(startDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	@JsonGetter("end_date")
	public String getJsonEndDate() {
		return Utils.formatDate(this.endDate);
	}

//	@DateTimeFormat(pattern="dd-MM-yyyy")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@JsonSetter
	public void setJsonEndDate(String endDate) throws ParseException {
		this.endDate = Utils.parseDate(endDate);
	}
	

	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Id : ").append(id);
		row.append("Account Holder : ").append(accountholder);
		row.append("Iban : ").append(iban);
		row.append("Swift : ").append(swift);
		row.append("Start Date : ").append(startDate);
		row.append("End Date : ").append(endDate);
		row.append("Notes : ").append(notes);
		return row.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (!(o instanceof BankCredentials )) return false;
        return id == ((BankCredentials) o).getId();
	}


	public Object getAssignment() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
