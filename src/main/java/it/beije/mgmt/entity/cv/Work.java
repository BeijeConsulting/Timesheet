package it.beije.mgmt.entity.cv;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "work")
public class Work {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_work")
	private Long idWork;

	@Column(name="title")
	private String title;

	@Column(name="employment")
	private String employment;

	@Column(name="company")
	private String company;

	@Column(name="location")
	private String location;

	@Column(name="startDate")
	private Date startDate;

	@Column(name="endDate")
	private Date endDate;

	@Column(name="description")
	private String description;

	@Column(name = "technologies")
	private String technologies;

	@Column(name="id_cv")
	private Long idCv;
	

	public Work() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdWork() {
		return idWork;
	}

	public void setIdWork(Long idWork) {
		this.idWork = idWork;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}

	public Long getIdCV() {
		return idCv;
	}

	public void setIdCV(Long idCV) {
		this.idCv = idCV;
	}
}
