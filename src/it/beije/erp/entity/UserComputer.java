package it.beije.erp.entity;

import java.sql.Date;
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

@Entity
@Table(name = "user_computer")
public class UserComputer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
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
	private int idUser;
	
	@Column(name="id_computer")
	private int idComputer;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(int idComputer) {
		this.idComputer = idComputer;
	}
}

//Disponibilità (tutti i pc disponibili)