package it.beije.mgmt.entity.cv;

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
	private String startDate;

	@Column(name="endDate")
	private String endDate;

	@Column(name="description")
	private String description;

	@Column(name = "technologies")
	private String technologies;

	@Column(name="id_cv")
	private Long idCV;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
		return idCV;
	}

	public void setIdCV(Long idCV) {
		this.idCV = idCV;
	}
}
