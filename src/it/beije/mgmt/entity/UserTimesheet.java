package it.beije.mgmt.entity;

import java.io.Serializable;

import javax.persistence.*;


public class UserTimesheet implements Serializable {
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "id_user")
	private int idUser;
	
	@Column(name = "date")
	private String date;
	
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
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{\"id\" : ").append(id)
				.append(",").append("\"id_user\" : ").append(idUser)
				.append(",").append("\"start1\" : \"").append(start1)
				.append("\",").append("\"start2\" : \"").append(start2)
				.append("\"}");
		
		return builder.toString();
	}

}
