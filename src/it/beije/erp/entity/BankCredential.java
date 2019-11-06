package it.beije.erp.entity;
 
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "bank_credentials")
@Entity
public class BankCredential {

	//TODO: Add ManyToOne relationship with User
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="id_user")
	private int idUser;
	
	@Column(name="accountholder")
	private String accountholder;
	
	@Column(name="iban")
	private String iban;
	
	@Column(name="swift")
	private String swift;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="notes")
	private String notes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
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
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
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
		row.append("Id User : ").append(idUser);
		row.append("Account Holder : ").append(accountholder);
		row.append("Iban : ").append(iban);
		row.append("Swift : ").append(swift);
		row.append("Start Date : ").append(startDate);
		row.append("End Date : ").append(endDate);
		row.append("Notes : ").append(notes);
		return row.toString();
	}
	
	
}
