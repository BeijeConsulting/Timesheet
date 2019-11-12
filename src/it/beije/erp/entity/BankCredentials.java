package it.beije.erp.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Table(name = "bank_credentials")
@Entity
public class BankCredentials {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "id_user")
	private User user;
	
	@Column(name="accountholder")
	private String accountholder;
	
	@Column(name="iban")
	private String iban;
	
	@Column(name="swift")
	private String swift;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="notes")
	private String notes;
	
	public BankCredentials() {
		
	}
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	@JsonGetter
	public int getId_user() {
		return user != null ? user.getId() : -1;
	}
	
	@JsonSetter
	public void setId_user(int id_user) {
		System.out.println("setId_user : " + id_user);
		this.user = new User();
		user.setId(id_user);
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



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	
	@Override
	public int hashCode() {
		return (int)id;
	}
	
	
}
