package it.beije.mgmt.entity.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "certification")
public class Certification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_certification")
	private Long idCertification;
	
	@Column(name="institution")
	private String institution;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="rating")
	private String rating;
	
	@Column(name = "technologies")
	private String technologies;
	
	@Column(name="id_cv")
	private Long idCV;

	
	
	public String getTitle() {
		return title;
	}
	
	public Long getIdCertification() {
		return idCertification;
	}

	public void setIdCertification(Long idCertification) {
		this.idCertification = idCertification;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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