package it.beije.mgmt.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import  it.beije.mgmt.entity.Timesheet;


public class TimesheetDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("user_id")
	private Long idUser;
	
	@JsonProperty("user_name")
	private String userName;
	
	@JsonProperty("timesheets")
	private List<Timesheet> activeTimesheets = new ArrayList<>();

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Timesheet> getActiveTimesheets() {
		return activeTimesheets;
	}
	
	public void addTimesheet(Timesheet timesheet) {
		activeTimesheets.add(timesheet);
	}
}
