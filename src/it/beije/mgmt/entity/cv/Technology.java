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
	@Column(name="id_technology")
	private Long idTechnology ;
	
	@Column(name="parent_id")
	private String parentId;
	
	@Column(name="technology")
	private String technology;
	
	public Technology() {
		
	}

	public Long getIdTechnology() {
		return idTechnology;
	}

	public void setIdTechnology(Long idTechnology) {
		this.idTechnology = idTechnology;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
}
