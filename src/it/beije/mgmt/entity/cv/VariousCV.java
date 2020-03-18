package it.beije.mgmt.entity.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "varous_cv")
public class VariousCV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_varous_cv")
	private Long idVarousCv;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;

	
	public Long getIdVariousCV() {
		return idVarousCv;
	}

	public void setIdVarieCV(Long idVarousCv) {
		this.idVarousCv = idVarousCv;
	}

	public String getTitle() {
		return title;
	}

	public void setTitolo(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescrizione(String description) {
		this.description = description;
	}
	
	

}
