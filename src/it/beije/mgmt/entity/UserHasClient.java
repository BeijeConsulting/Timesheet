package it.beije.mgmt.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Table(name = "user_has_client")
@Entity(name = "u_has_c")
public class UserHasClient {
	
	@EmbeddedId
	private UserHasClientId id;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("userId")
	private User user;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("clientId")
	private ClientCompany company;
	
	@Column(name="start")
	private LocalDate startDate;
	
	@Column(name="end")
	private LocalDate endDate;
	
	@Column(name="notes")
	private String notes;

	public UserHasClientId getId() {
		return id;
	}

	public void setId(UserHasClientId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ClientCompany getCompany() {
		return company;
	}

	public void setCompany(ClientCompany company) {
		this.company = company;
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
}
