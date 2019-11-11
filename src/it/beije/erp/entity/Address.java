package it.beije.erp.entity;
 
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@Column(name="id")
	private int id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "id_user")
	private User user;

	@Column(name="street", nullable=false)
	private String street;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="province", nullable=false)
	private String province;
	
	@Column(name="cap", nullable=false)
	private int cap;
	
	@Column(name="country" , nullable=false)
	private String country;
	
	@Column(name="start_date" , nullable=false)
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	public Address() {
		
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
		row.append("Id: ").append(id).append("\n");
		row.append("Street: ").append(street).append("\n");
		row.append("City: ").append(city).append("\n");
		row.append("Province: ").append(province).append("\n");
		row.append("Cap: ").append(cap).append("\n");
		row.append("Country: ").append(country).append("\n");
		row.append("Start Date: ").append(startDate).append("\n");
		row.append("End Date: ").append(endDate).append("\n");
		return row.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (!(o instanceof Address )) return false;
        return id == ((Address) o).getId();
	}
	
	@Override
	public int hashCode() {
		return (int)id;
	}

}
