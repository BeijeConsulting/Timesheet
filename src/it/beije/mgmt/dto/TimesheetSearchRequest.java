package it.beije.mgmt.dto;

import java.sql.Date;

public class TimesheetSearchRequest {
	
	private Long idUser;
	private Date dateFrom;
	private Date dateTo;
	private String type;
	private boolean submit;
	private boolean validated;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getSubmit() {
		return submit;
	}
	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}
	public Boolean getValidated() {
		return validated;
	}
	public void setValidated(Boolean validate) {
		this.validated = validate;
	}
	
	
	
	

}
