package it.beije.mgmt.entity;

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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import it.beije.mgmt.tool.Utils;
 
@Table(name = "contratto")
@Entity
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="id_user")
	@JsonProperty("user_id")
	private Long idUser;
	
	
	@ManyToOne(targetEntity=ContractType.class)
    @JoinColumn(name = "contract_type")
	private ContractType type;
	
	@Column(name="ccnl")
	private String ccnl;
	
	@Column(name="livello")
	private String livello;
	
	@Column(name="rimborso_spese")
	@JsonProperty("rimborso_spese")
	private Double rimborsoSpese;
	
	@Column(name="minimo_contrattuale")
	@JsonProperty("minimo_contrattuale")
	private Double minimoContrattuale;
	
	@Column(name="superminimo")
	private Double superminimo;
	
	@Column(name="retribuzione_mensile")
	@JsonProperty("retribuzione_mensile")
	private Double retribuzioneMensile;
	
	@Column(name="ral")
	private Double ral;
	
	@Column(name="netto_mensile")
	@JsonProperty("netto_mensile")
	private Double nettoMensile;
	
	@Column(name="costo_interno")
	@JsonProperty("costo_interno")
	private Double costoInterno;
	
	@Column(name="note")
	private String note;
	
	@Column(name="start_date")
	@JsonProperty("start_date")
	private Date startDate;
	
	@Column(name="end_date")
	@JsonProperty("end_date")
	private Date endDate;
	
	public Contract() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonGetter("contract_type")
	public char getContract_type() {
		return type != null ? type.getCod() : '-';
	}
	@JsonSetter("contract_type")
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

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public Double getRimborsoSpese() {
		return rimborsoSpese;
	}

	public void setRimborsoSpese(Double rimborsoSpese) {
		this.rimborsoSpese = rimborsoSpese;
	}

	public Double getMinimoContrattuale() {
		return minimoContrattuale;
	}
	public void setMinimoContrattuale(double minimoContrattuale) {
		this.minimoContrattuale = minimoContrattuale;
	}

	public Double getSuperminimo() {
		return superminimo;
	}
	public void setSuperminimo(double superminimo) {
		this.superminimo = superminimo;
	}

	public Double getRetribuzioneMensile() {
		return retribuzioneMensile;
	}
	public void setRetribuzioneMensile(double retribuzioneMensile) {
		this.retribuzioneMensile = retribuzioneMensile;
	}

	public Double getRal() {
		return ral;
	}
	public void setRal(double ral) {
		this.ral = ral;
	}

	public Double getNettoMensile() {
		return nettoMensile;
	}
	public void setNettoMensile(double nettoMensile) {
		this.nettoMensile = nettoMensile;
	}

	public Double getCostoInterno() {
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
//	@DateTimeFormat(pattern="dd-MM-yyyy")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonGetter("start_date")
	public String getJsonStartDate() {
		return Utils.formatDate(this.startDate);
	}
	@JsonSetter("start_date")
	public void setJsonStartDate(String startDate) throws ParseException {
		this.startDate = Utils.parseDate(startDate);
	}
	
	public Date getEndDate() {
		return endDate;
	}
//	@DateTimeFormat(pattern="dd-MM-yyyy")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@JsonGetter("end_date")
	public String getJsonEndDate() {
		return Utils.formatDate(this.endDate);
	}
	@JsonSetter("end_date")
	public void setJsonEndDate(String endDate) throws ParseException {
		this.endDate = Utils.parseDate(endDate);
	}
	
	@Override
	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Id: ").append(id);
		row.append("CCNL: ").append(ccnl);
		row.append("Level: ").append(livello);
		return row.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (!(o instanceof Contract )) return false;
        return id == ((Contract) o).getId();
	}

}
