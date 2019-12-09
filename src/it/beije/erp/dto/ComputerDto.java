package it.beije.erp.dto;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.beije.erp.entity.Address;
import it.beije.erp.entity.BankCredentials;
import it.beije.erp.entity.Computer;
import it.beije.erp.entity.Contract;
import it.beije.erp.entity.User;
import it.beije.erp.entity.UserComputer;

@JsonInclude(Include.NON_NULL)

public class ComputerDto {
	
	private static final long serialVersionUID = 4865903039190150L;
	
	private Long id;
	private String brand;
	private String model;
	private String cpu;
	private int ram;
	
	private String hardDisk;
	
	private String serialNumber;
	private String operatingSystem;
	private boolean availability;
	private Date purchaseDate;
	private Date disposalDate;
	private String note;
	private UserComputer assignments;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getCpu() {
		return cpu;
	}



	public void setCpu(String cpu) {
		this.cpu = cpu;
	}



	public int getRam() {
		return ram;
	}



	public void setRam(int ram) {
		this.ram = ram;
	}



	public String getHardDisk() {
		return hardDisk;
	}



	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}



	public String getSerialNumber() {
		return serialNumber;
	}



	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}



	public String getOperatingSystem() {
		return operatingSystem;
	}



	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}



	public boolean isAvailability() {
		return availability;
	}



	public void setAvailability(boolean availability) {
		this.availability = availability;
	}



	public Date getPurchaseDate() {
		return purchaseDate;
	}



	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}



	public Date getDisposalDate() {
		return disposalDate;
	}



	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public UserComputer getAssignments() {
		return assignments;
	}



	public void setAssignments(UserComputer assignments) {
		this.assignments = assignments;
	}



	public static ComputerDto valueOf(Computer computer) {
        ComputerDto dto = new ComputerDto();
        dto.setId(computer.getId());
        dto.setModel(computer.getModel());
        dto.setBrand(computer.getBrand());
        dto.setCpu(computer.getCpu());
        dto.setRam(computer.getRam());
        
        return dto;
    }


}
