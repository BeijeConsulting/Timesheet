package it.beije.erp.entity;

import java.time.LocalDate;

public class Address {
	
	private int id;
	private int idUser;
	private String street;
	private String city;
	private String province;
	private int cap;
	private String country;
	private LocalDate startDate;
	private LocalDate endDate;
	
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public int getCap() {
		return cap;
	}
	public void setCap(int cap) {
		this.cap = cap;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	
	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Id: ").append(id);
		row.append("Id Utenti: ").append(idUser);
		row.append("Street: ").append(street);
		row.append("City: ").append(city);
		row.append("Province: ").append(province);
		row.append("Cap: ").append(cap);
		row.append("Country: ").append(country);
		row.append("Start Date: ").append(startDate);
		row.append("End Date: ").append(endDate);
		return row.toString();
		
	}
	

}
