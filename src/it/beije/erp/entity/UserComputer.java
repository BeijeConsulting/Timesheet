package it.beije.erp.entity;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import it.beije.Utils;

@Entity
@Table(name = "user_computer")
public class UserComputer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="mouse")
	private String mouse;

	@Column(name="lan_adapter")
	private boolean lanAdapter;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="note")
	private String note;

	@Column(name="id_user")
	private Long idUser;
	
	@Column(name="id_computer")
	private Long idComputer;
	
//	@OneToMany
//	@JoinColumn(name = "id")
//	private List<User> users;
//
//	public List<User> getUsers() {
//		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
//		List<User> users= new ArrayList<>();
//
//		users=entityManager.createQuery("select u from User u where id="+idUser,
//			    User.class).getResultList();
//		entityManager.close();
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMouse() {
		return mouse;
	}

	public void setMouse(String mouse) {
		this.mouse = mouse;
	}

	public boolean isLanAdapter() {
		return lanAdapter;
	}

	public void setLanAdapter(boolean lanAdapter) {
		this.lanAdapter = lanAdapter;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	@JsonGetter("startDate")
	public String getJsonStartDate() {
		return Utils.formatDate(this.startDate);
	}
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

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

	@DateTimeFormat(pattern="dd-MM-yyyy")
	public void setEndDate(Date endDate) {
		System.out.println("setendDate ? endDate = " + endDate);
		this.endDate = endDate;
	}
	
	@JsonSetter
	public void setJsonEndDate(String endDate) throws ParseException {
		System.out.println("setJsonEndDate ? endDate = " + endDate);
		this.endDate = Utils.parseDate(endDate);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(Long idComputer) {
		this.idComputer = idComputer;
	}
}

//DisponibilitÓ (tutti i pc disponibili)