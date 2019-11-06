package it.beije.erp.entity;
 
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

@Table(name = "contract_type")
@Entity
public class ContractType {
	
	@Id
	@Column(name="id")
	private char cod;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contract")
	private Contract contract;
	
	@Column(name="description")
	private String description;
	
	public char getCod() {
		return cod;
	}
	public void setCod(char cod) {
		this.cod = cod;
	}

	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
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
