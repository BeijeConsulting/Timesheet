package it.beije.erp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
@Table(name = "contract")
@Entity
public class Contract {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ccnl")
	private String ccnl;
	
	@Column(name="lvl")
	private int lvl;
	
	@OneToMany(mappedBy = "contract")
	private List<ContractType> contractTypes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public List<ContractType> getContractTypes() {
		return contractTypes;
	}
	public void setContractTypes(List<ContractType> contractTypes) {
		this.contractTypes = contractTypes;
	}

	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Id: ").append(id);
		row.append("CCNL: ").append(ccnl);
		row.append("Level: ").append(lvl);
		return row.toString();
	}
	
	public void addContractType(ContractType type) {
		contractTypes.add(type);
		type.setContract(this);
	}
	
	public void removeContractType(ContractType type) {
		contractTypes.remove(type);
		type.setContract(null);
	}
}
