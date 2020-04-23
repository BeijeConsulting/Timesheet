package it.beije.mgmt.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "client_order")
public class ClientOrder {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("user_id")
	private User user;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("clienti_id")
	private ClientCompany company;
	
	@Column(name="start_date")
	@JsonProperty("start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	@JsonProperty("end_date")
	private LocalDate endDate;
	
	@Column(name="order_ref")
	@JsonProperty("order_ref")
	private String orderRef;
	
	@Column(name="label")
	private String label;
	
	@Column(name="desc")
	private String desc;
	
	@Column(name="notes")
	private String notes;	
}
