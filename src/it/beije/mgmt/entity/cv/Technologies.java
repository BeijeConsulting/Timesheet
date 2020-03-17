package it.beije.mgmt.entity.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "technologies")
public class Technologies {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_teconology")
	private Long idTeconlogy;
	
	@Column(name="technology")
	private String technology;
	
	public Technologies() {
		
	}
	
	

	public Long getIdTeconlogy() {
		return idTeconlogy;
	}


	public void setIdTeconlogy(Long idTeconlogy) {
		this.idTeconlogy = idTeconlogy;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

}
