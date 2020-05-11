package it.beije.mgmt.entity.cv;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

	@Column(name = "notes")
	private String notes;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_cv")
	private List<Education> educationList;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_cv")
	private List<Work> workList;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_cv")
	private List<Language> languageList;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_cv")
	private List<Certification> certificationList;
	
	//@Transient
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id_technologies")
//	private List<Technology> technology;
//	
//	
//	public List<Technology> getTechnology() {
//		return technology;
//	}
//
//	public void setTechnology(List<Technology> technology) {
//		this.technology = technology;
//	}

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

	public List<Education> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<Education> educationList) {
		this.educationList = educationList;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
