package it.beije.mgmt.entity.cv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "education")
public class Education {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_education")
	private Long idEducation; 
	
	@Column(name="intitute")
	private String institute;
	
	@Column(name="qualification")
	private String qualification;
	
	@Column(name="course_of_study")
	private String courseOfStudy;
	
	@Column(name="star_year")
	private String startYear;
	
	@Column(name="end_year")
	private String endYear;
	
	@Column(name="score")
	private String score;
	
	@Column(name="score_max")
	private String scoreMax;
	
	@Column(name = "technologies")
	private String technologies;
	
	@Column(name="id_cv")
	private Long idCV;
	
	public Long getIdEducation() {
		return idEducation;
	}

	public void setIdEducation(Long idEducation) {
		this.idEducation = idEducation;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCourseOfStudy() {
		return courseOfStudy;
	}

	public void setCourseOfStudy(String courseOfStudy) {
		this.courseOfStudy = courseOfStudy;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(String scoreMax) {
		this.scoreMax = scoreMax;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}

	public Long getIdCV() {
		return idCV;
	}

	public void setIdCV(Long idCV) {
		this.idCV = idCV;
	}
	
}
