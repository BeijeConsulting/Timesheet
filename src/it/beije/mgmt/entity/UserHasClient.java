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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("clientId")
	private ClientCompany company;
	
	@Column(name="start")
	private LocalDate startDate;
	
	@Column(name="end")
	private LocalDate endDate;
	
	@Column(name="notes")
	private String notes;
}
