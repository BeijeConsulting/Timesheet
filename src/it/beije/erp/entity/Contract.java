package it.beije.erp.entity;

public class Contract {
	
	private int id;
	private char idContract;
	private String ccnl;
	private int lvl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getIdContract() {
		return idContract;
	}
	public void setIdContract(char idContract) {
		this.idContract = idContract;
	}
	public String getCcnl() {
		return ccnl;
	}
	public void setCcnl(String ccnl) {
		this.ccnl = ccnl;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Id: ").append(id);
		row.append("Id Contract: ").append(idContract);
		row.append("CCNL: ").append(ccnl);
		row.append("Level: ").append(lvl);
		return row.toString();
	}
	

}
