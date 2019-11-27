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
@Table(name = "computer")
public class Computer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="brand")
	private String brand;

	@Column(name="model")
	private String model;
	
	@Column(name="cpu")
	private String cpu;
	
	@Column(name="ram")
	private int ram;
	
	@Column(name="hard_disk")
	private String hardDisk;
	
	@Column(name="serial_number")
	private String serialNumber;
	
	@Column(name="operating_system")
	private String operatingSystem;
	
	@Column(name="availability")
	private boolean availability;
	
	@Column(name="purchase_date")
	private Date purchaseDate;
	
	@Column(name="disposal_date")
	private Date disposalDate;
	
	@Column(name="note")
	private String note;
	
	@OneToMany
	@JoinColumn(name = "id_computer")
	private List<UserComputer> assignments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getDisposalDate() {
		return disposalDate;
	}

	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<UserComputer> getAssignment() {
		EntityManager entityManager = Persistence.createEntityManagerFactory("timesheet").createEntityManager();
		List<UserComputer> userComputer = new ArrayList<>();

		userComputer=entityManager.createQuery("select uc from UserComputer uc where id_computer="+id,
			    UserComputer.class).getResultList();
		entityManager.close();
		return userComputer;
	}

	public void setUsers(List<UserComputer> assignments) {
		this.assignments = assignments;
	}
	
	
}