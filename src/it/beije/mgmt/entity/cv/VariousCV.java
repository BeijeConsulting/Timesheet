package it.beije.mgmt.entity.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "various_cv")
public class VariousCV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_various_cv")
	private Long idVariousCv;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;

	
	public Long getIdVariousCV() {
		return idVariousCv;
	}

	public void setIdVariousCV(Long idVariousCv) {
		this.idVariousCv = idVariousCv;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
