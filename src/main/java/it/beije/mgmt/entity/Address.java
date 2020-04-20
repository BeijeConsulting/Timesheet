package it.beije.mgmt.entity;
 
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import it.beije.mgmt.tool.Utils;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="id_user")
	private Long idUser;
	
	@Column(name="street", nullable=false)
	private String street;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="province", nullable=false)
	private String province;
	
	@Column(name="cap", nullable=false)
	private String cap;
	
	@Column(name="country" , nullable=false)
	private String country;
	
	@Column(name="start_date" , nullable=false)
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="type")
	private char type;
	
	public Address() {
		
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

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	@JsonGetter("startDate")
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
	
	@JsonGetter("endDate")
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

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
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

}
