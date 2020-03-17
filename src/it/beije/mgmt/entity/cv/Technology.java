package it.beije.mgmt.entity.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "technology")
public class Technology {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	@Column(name="tecnologia")
	private String tecnologia;
	
	public Technology() {
		
	}
	
	public String getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}
}
