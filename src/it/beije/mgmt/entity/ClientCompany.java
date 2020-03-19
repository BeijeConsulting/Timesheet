package it.beije.mgmt.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "client_company")
public class ClientCompany {	
		
	private static final long serialVersionUID = 4865903039190150223L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", nullable=false)
	private String firstName;

	@Column(name = "last_name", nullable=false)
	private String lastName;

	@Column(name = "email", unique=true, nullable=false)
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "secondary_email")
	private String secondaryEmail;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_client_company")
	private List<Office> offices;

	
}
