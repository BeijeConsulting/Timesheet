package it.beije.mgmt.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;

import org.springframework.lang.NonNull;


@Entity
@Table(name = "timesheet")
public class Timesheet implements Serializable {
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "validated")
		private LocalDateTime validated;
		
	@NonNull
	@Column(name = "id_user")
	private Long idUser;
	
	@NonNull
	@Column(name = "date")
	private LocalDate date;
	
	@NonNull
	@Column(name = "type")
	private String type;
	
	@Column(name = "start1")
	private LocalTime start1;
	
	@Column(name = "end1")
	private LocalTime end1;
	
	@Column(name = "start2")
	private LocalTime start2;
	
	@Column(name = "end2")
	private LocalTime end2;
	
	@Column(name = "tot")
	private Double tot;
	
	public LocalDateTime getValidated() {
		return validated;
	}
	public void setValidated(LocalDateTime validated) {
		this.validated = validated;
	}
	
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
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public LocalTime getStart1() {
		return start1;
	}
	public void setStart1(LocalTime start1) {
		this.start1 = start1;
	}
	
	
	public LocalTime getEnd1() {
		return end1;
	}
	public void setEnd1(LocalTime end1) {
		this.end1 = end1;
	}
	
	
	public LocalTime getStart2() {
		return start2;
	}
	public void setStart2(LocalTime start2) {
		this.start2 = start2;
	}
	
	
	public LocalTime getEnd2() {
		return end2;
	}
	public void setEnd2(LocalTime end2) {
		this.end2 = end2;
	}
	
	
	public Double getTot() {
		return tot;
	}
	public void setTot(Double tot) {
		this.tot = tot;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{\"id\" : ").append(id)
				.append(",").append("\"id_user\" : ").append(idUser)
				.append(",").append("\"start1\" : \"").append(start1)
				.append("\",").append("\"start2\" : \"").append(start2)
				.append("\"}");
		
		return builder.toString();
	}

}
