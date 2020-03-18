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
	
	@Column(name="title")
	private String title;
	
	@Column(name="rating")
	private String rating;
	
	
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
}