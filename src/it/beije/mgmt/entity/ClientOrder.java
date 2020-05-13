package it.beije.mgmt.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "client_order")
@JsonInclude(Include.NON_NULL)
public class ClientOrder {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("user_id")
//	private User user;
	@Column(name="user_id")
	@JsonProperty("user_id")
	private Long idUser;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("clienti_id")
//	private ClientCompany company;
	@Column(name="clienti_id")
	@JsonProperty("client_id")
	private Long idClient;
	
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

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
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

	public String getOrderRef() {
		return orderRef;
	}

	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
