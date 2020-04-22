package it.beije.mgmt.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.beije.mgmt.tool.Utils;


@Entity
@Table(name = "timesheet")
public class Timesheet implements Serializable {
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NonNull
	@Column(name = "id_user")
	@JsonProperty("id_user")
	private Long idUser;
	
	@NonNull
	@Column(name = "date")
	private Date date;
	
	@NonNull
	@Column(name = "type")
	private String type;
	
	@Column(name = "start1")
	private Time start1;
	
	@Column(name = "end1")
	private Time end1;
	
	@Column(name = "start2")
	private Time start2;
	
	@Column(name = "end2")
	private Time end2;
	
	@Column(name = "tot")
	private Double tot;
	
	@Column(name = "validated")
	private Date validated;
	
	@Column(name = "submit")
	private Date submit;
		
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	
	public Date getDate() {
		return date;
	}
	
	@JsonGetter("date")
	public String getJsonData() {
		return Utils.formatDate(this.date);
	}
	public void setDate(Date date) {
		System.out.println("set date from Date");
		this.date = date;
	}
	
//	@JsonSetter("date")
	public void setDate(String date) throws ParseException {
		System.out.println("set date from String");
		this.date = Utils.parseDate(date);
	}
	
////	@JsonGetter("date")
//	public String getJsonStartDate() {
//		return Utils.formatDate(this.date);
//	}
//	public void setJsonEndDate(String endDate) throws ParseException {
//		this.date = Utils.parseDate(endDate);
//	}
	
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public Time getStart1() {
		return start1;
	}
	public void setStart1(Time start1) {
		this.start1 = start1;
	}
	
	
	public Time getEnd1() {
		return end1;
	}
	public void setEnd1(Time end1) {
		this.end1 = end1;
	}
	
	
	public Time getStart2() {
		return start2;
	}
	public void setStart2(Time start2) {
		this.start2 = start2;
	}
	
	
	public Time getEnd2() {
		return end2;
	}
	public void setEnd2(Time end2) {
		this.end2 = end2;
	}
	
	
	public Double getTot() {
		return tot;
	}
	public void setTot(Double tot) {
		this.tot = tot;
	}

	public Date getValidated() {
		return validated;
	}
	
	@JsonGetter("validated")
	public String getJsonValidated() {
		return Utils.formatDate(this.validated);
	}
	
	public void setValidated(Date validated) {
		this.validated = validated;
	}

	
	public Date getSubmit() {
		return submit;
	}
	
	@JsonGetter("submit")
	public String getJsonSubmit() {
		return Utils.formatDate(this.submit);
	}
	
	public void setSubmit(Date submit) {
		this.submit = submit;
	}
	
	@Override
	public String toString() {
		return "Timesheet [id=" + id + ", validated=" + validated + ", submit=" + submit + ", idUser=" + idUser
				+ ", date=" + date + ", type=" + type + ", start1=" + start1 + ", end1=" + end1 + ", start2=" + start2
				+ ", end2=" + end2 + ", tot=" + tot + "]";
	}
}
