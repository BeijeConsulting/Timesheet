package it.beije.mgmt.entity.cv;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cv")
public class CV {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cv")
	private Long idCv;
	
	@Column(name="id_user")
	private Long idUser;
	
	@Column(name="title")
	private String title;
	
	@Column(name="formazioneList")
	private List<Formazione> formazioneList;

	@Column(name="workList")
	private List<Work> workList;
	
	@Column(name="languageList")
	private List<Language> languageList;
	
	@Column(name="certificationList")
	private List<Certification> certificationList;
	
	@Column(name="technologyList")
	private String technologyList;
	
	public CV(){
		
	}

	public Long getIdCv() {
		return idCv;
	}

	public void setIdCv(Long idCv) {
		this.idCv = idCv;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Formazione> getFormazioneList() {
		return formazioneList;
	}

	public void setFormazioneList(List<Formazione> formazioneList) {
		this.formazioneList = formazioneList;
	}

	public List<Work> getWorkList() {
		return workList;
	}

	public void setWorkList(List<Work> workList) {
		this.workList = workList;
	}

	public List<Language> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<Language> languageList) {
		this.languageList = languageList;
	}

	public List<Certification> getCertificationList() {
		return certificationList;
	}

	public void setCertificationList(List<Certification> certificationList) {
		this.certificationList = certificationList;
	}

	public String getTechnologyList() {
		return technologyList;
	}

	public void setTechnologyList(String technologyList) {
		this.technologyList = technologyList;
	}	
	
}
