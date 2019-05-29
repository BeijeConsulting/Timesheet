package it.beije.timesheet.entities;

import java.sql.Date;
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
	private Date date;
	
	@Column(name = "type")
	private char type;
	
	@Column(name = "start1")
	private String start1;
	
	@Column(name = "end1")
	private String end1;
	
	@Column(name = "start2")
	private String start2;
	
	@Column(name = "end2")
	private String end2;
	
	@Column(name = "tot")
	private double tot;
	
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
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public char getType() {
		return type;
	}
	
	public void setType(Character type) {
		this.type = type;
	}
	
	
	public String getStart1() {
		return start1;
	}
	public void setStart1(String start1) {
		this.start1 = start1;
	}
	
	
	public String getEnd1() {
		return end1;
	}
	public void setEnd1(String end1) {
		this.end1 = end1;
	}
	
	
	public String getStart2() {
		return start2;
	}
	public void setStart2(String start2) {
		this.start2 = start2;
	}
	
	
	public String getEnd2() {
		return end2;
	}
	public void setEnd2(String end2) {
		this.end2 = end2;
	}
	
	
	public double getTot() {
		return tot;
	}
	public void setTot(double tot) {
		this.tot = tot;
	}

}
