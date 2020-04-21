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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import it.beije.mgmt.tool.Utils;
 
@Table(name = "contratto")
@Entity
public class Contract {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="id_user")
	@JsonProperty("id_user")
	private Long idUser;
	
	@JsonIgnore
	@ManyToOne(targetEntity=ContractType.class)
    @JoinColumn(name = "contract_type")
	private ContractType type;
	
	@Column(name="ccnl")
	private String ccnl;
	
	@Column(name="liv")
	private Byte lvl;
	
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

	
	@JsonGetter
	public char getContract_type() {
		return type != null ? type.getCod() : '-';
	}
	@JsonSetter
	public void setContract_type(char contract_type) {
		log.info("setContract_type : " + contract_type);
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

	public Byte getLvl() {
		return lvl;
	}
	public void setLvl(byte lvl) {
		this.lvl = lvl;
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
	
	@JsonGetter("startDate")
	public String getJsonStartDate() {
		return Utils.formatDate(this.startDate);
	}
	@JsonSetter
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
	
	@JsonGetter("endDate")
	public String getJsonEndDate() {
		return Utils.formatDate(this.endDate);
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
