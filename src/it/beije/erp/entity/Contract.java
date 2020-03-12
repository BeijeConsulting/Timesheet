package it.beije.erp.entity;

import java.sql.Date;
import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import it.beije.Utils;
 
@Table(name = "contratto")
@Entity
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="id_user")
	private Long idUser;
	
	@JsonIgnore
	@ManyToOne		//fetchtype = "LAZY" ???
    @JoinColumn(name = "contract_type")
	private ContractType type;
	
	@Column(name="ccnl")
	private String ccnl;
	
	@Column(name="liv")
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
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	public Contract() {
		
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	
	@JsonGetter
	public char getContract_type() {
		return type != null ? type.getCod() : '-';
	}
	
	@JsonSetter
	public void setContract_type(char contract_type) {
		System.out.println("setContract_type : " + contract_type);
		this.type = new ContractType();
		type.setCod(contract_type);
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


	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Date getStartDate() {
		return startDate;
	}

	@JsonGetter("startDate")
	public String getJsonStartDate() {
		return Utils.formatDate(this.startDate);
	}
	
//	@DateTimeFormat(pattern="dd-MM-yyyy")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonSetter
	public void setJsonStartDate(String startDate) throws ParseException {
		this.startDate = Utils.parseDate(startDate);
	}
	
	public Date getEndDate() {
		return endDate;
	}

	@JsonGetter("endDate")
	public String getJsonEndDate() {
		return Utils.formatDate(this.endDate);
	}
	
//	@DateTimeFormat(pattern="dd-MM-yyyy")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@JsonSetter
	public void setJsonEndDate(String endDate) throws ParseException {
		this.endDate = Utils.parseDate(endDate);
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

}
