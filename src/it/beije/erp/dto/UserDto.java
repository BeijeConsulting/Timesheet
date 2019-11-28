package it.beije.erp.dto;


import java.io.Serializable;

import it.beije.erp.entity.Address;
import it.beije.erp.entity.BankCredentials;
import it.beije.erp.entity.Contract;
import it.beije.erp.entity.User;


public class UserDto implements Serializable {
	
    private static final long serialVersionUID = 4865903039190150223L;

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Address address;
	private BankCredentials bankCredential;
	private Contract contract;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BankCredentials getBankCredential() {
		return bankCredential;
	}

	public void setBankCredential(BankCredentials bankCredential) {
		this.bankCredential = bankCredential;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	
	public static UserDto valueOf(User user) {
        UserDto dto = new UserDto();
        dto.setId(Long.valueOf(user.getId()));
        dto.setFirstName(String.valueOf(user.getFirstName()));
        dto.setLastName(String.valueOf(user.getLastName()));
        dto.setEmail(String.valueOf(user.getEmail()));
        dto.setPhone(String.valueOf(user.getPhone()));
        
        return dto;
    }

}