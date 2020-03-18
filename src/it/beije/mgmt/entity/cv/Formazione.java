package it.beije.mgmt.entity.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formazione")
public class Formazione {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_formazione")
	private Long idFormazione;
	
	@Column(name="istituto")
	private String istituto;
	
	@Column(name="titolo_studio")
	private String titoloDiStudio;
	
	@Column(name="corso_studio")
	private String corsoDiStudi;
	
	@Column(name="anno_inizio")
	private String annoDiInizio;
	
	@Column(name="anno_fine")
	private String annoDiFine;
	
	@Column(name="valutazione")
	private String valutazione;
	
	@Column(name="valutazione_max")
	private String valutazioneMax;
	
	

	public Long getIdFormazione() {
		return idFormazione;
	}

	public void setIdFormazione(Long idFormazione) {
		this.idFormazione = idFormazione;
	}

	public String getIstituto() {
		return istituto;
	}

	public void setIstituto(String istituto) {
		this.istituto = istituto;
	}

	public String getTitoloDiStudio() {
		return titoloDiStudio;
	}

	public void setTitoloDiStudio(String titoloDiStudio) {
		this.titoloDiStudio = titoloDiStudio;
	}

	public String getCorsoDiStudi() {
		return corsoDiStudi;
	}

	public void setCorsoDiStudi(String corsoDiStudi) {
		this.corsoDiStudi = corsoDiStudi;
	}

	public String getAnnoDiInizio() {
		return annoDiInizio;
	}

	public void setAnnoDiInizio(String annoDiInizio) {
		this.annoDiInizio = annoDiInizio;
	}

	public String getAnnoDiFine() {
		return annoDiFine;
	}

	public void setAnnoDiFine(String annoDiFine) {
		this.annoDiFine = annoDiFine;
	}

	public String getValutazione() {
		return valutazione;
	}

	public void setValutazione(String valutazione) {
		this.valutazione = valutazione;
	}

	public String getValutazioneMax() {
		return valutazioneMax;
	}

	public void setValutazioneMax(String valutazioneMax) {
		this.valutazioneMax = valutazioneMax;
	}
	
}
