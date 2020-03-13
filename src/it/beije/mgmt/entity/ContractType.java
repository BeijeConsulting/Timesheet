package it.beije.mgmt.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "contract_type")
@Entity
public class ContractType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod")
	private char cod;
	
	@OneToMany(targetEntity=Contract.class, mappedBy = "type", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Contract> contracts;
	
	@Column(name="description")
	private String description;
	
	public ContractType() {
		
	}
	
	

	public char getCod() {
		return cod;
	}



	public void setCod(char cod) {
		this.cod = cod;
	}



	public List<Contract> getContracts() {
		return contracts;
	}



	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (!(o instanceof ContractType )) return false;
        return cod == ((ContractType) o).getCod();
	}
	
	@Override
	public int hashCode() {
		return (int)cod;
	}
	
	@Override
	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Code: ").append(cod);
		row.append("Description: ").append(description);
		return row.toString();
	}
	
}
