package it.beije.erp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
@Table(name = "contract")
@Entity
public class Contract {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_type")
	private ContractType type;
	
	@Column(name="ccnl")
	private String ccnl;
	
	@Column(name="lvl")
	private byte lvl;
	
	@Column(name="minimo_contrattuale")
	private double minimoContrattuale;
	
	@Column(name="superminimo")
	private double superminimo;
	
	@Column(name="retribuzione_mensile")
	private double retribuzioneMensile;
	
	@Column(name="ral")
	private double ral;
	
	@Column(name="netto_mensile")
	private double nettoMensile;
	
	@Column(name="costo_interno")
	private double costoInterno;
	
	@Column(name="note")
	private String note;
	
	public Contract() {
		
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public ContractType getType() {
		return type;
	}



	public void setType(ContractType type) {
		this.type = type;
	}



	public String getCcnl() {
		return ccnl;
	}



	public void setCcnl(String ccnl) {
		this.ccnl = ccnl;
	}



	public byte getLvl() {
		return lvl;
	}



	public void setLvl(byte lvl) {
		this.lvl = lvl;
	}



	public double getMinimoContrattuale() {
		return minimoContrattuale;
	}



	public void setMinimoContrattuale(double minimoContrattuale) {
		this.minimoContrattuale = minimoContrattuale;
	}



	public double getSuperminimo() {
		return superminimo;
	}



	public void setSuperminimo(double superminimo) {
		this.superminimo = superminimo;
	}



	public double getRetribuzioneMensile() {
		return retribuzioneMensile;
	}



	public void setRetribuzioneMensile(double retribuzioneMensile) {
		this.retribuzioneMensile = retribuzioneMensile;
	}



	public double getRal() {
		return ral;
	}



	public void setRal(double ral) {
		this.ral = ral;
	}



	public double getNettoMensile() {
		return nettoMensile;
	}



	public void setNettoMensile(double nettoMensile) {
		this.nettoMensile = nettoMensile;
	}



	public double getCostoInterno() {
		return costoInterno;
	}



	public void setCostoInterno(double costoInterno) {
		this.costoInterno = costoInterno;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	@Override
	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Id: ").append(id);
		row.append("CCNL: ").append(ccnl);
		row.append("Level: ").append(lvl);
		return row.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (!(o instanceof Contract )) return false;
        return id == ((Contract) o).getId();
	}
	
	@Override
	public int hashCode() {
		return (int)id;
	}
}
