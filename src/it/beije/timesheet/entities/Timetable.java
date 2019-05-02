package it.beije.timesheet.entities;

import java.time.*;

import javax.persistence.*;

@Entity
@Table(name = "timetable")
public class Timetable {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "id_user")
	private int id_user;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "type")
	private Character type;
	
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	public Character getType() {
		return type;
	}
	public void setType(Character type) {
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

}
