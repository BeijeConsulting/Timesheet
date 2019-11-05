package it.beije.erp.entity;

import java.time.LocalDate;

public class BankCredential {

	private int id;
	private int idUser;
	private String accountholder;
	private String iban;
	private String swift;
	private LocalDate startDate;
	private LocalDate endDate;
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
