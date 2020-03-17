package it.beije.mgmt.entity.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "varie_cv")
public class VarieCv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_varie_cv")
	private Long idVarieCV;
	
	@Column(name="titolo")
	private String titolo;
	
	@Column(name="descrizione")
	private String descrizione;

	
	public Long getIdVarieCV() {
		return idVarieCV;
	}

	public void setIdVarieCV(Long idVarieCV) {
		this.idVarieCV = idVarieCV;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	

}
