package it.beije.erp.entity;

public class ContractType {
	
	private char cod;
	private String description;
	
	public char getCod() {
		return cod;
	}
	public void setCod(char cod) {
		this.cod = cod;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Code: ").append(cod);
		row.append("Description: ").append(description);
		return row.toString();
	}
	
	

}
