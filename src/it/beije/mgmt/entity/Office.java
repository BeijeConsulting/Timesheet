package it.beije.mgmt.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "office")
public class Office {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "street", nullable=false)
	private String street;

	@Column(name = "city", nullable=false)
	private String city;

	@Column(name = "province", nullable=false)
	private String province;
	
	@Column(name = "cap", nullable=false)
	private String cap;
	
	@Column(name = "country", nullable=false)
	private String country;
	
	@Column(name = "start_date", nullable=false)
	@JsonProperty("start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date", nullable=true)
	@JsonProperty("end_date")
	private LocalDate endDate;
	
	@Column(name = "type", nullable=true)
	private String type;
	
	@Column(name = "notes", nullable=true)
	private String notes;
}
