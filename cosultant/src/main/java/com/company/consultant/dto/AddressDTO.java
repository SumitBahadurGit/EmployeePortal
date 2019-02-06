package com.company.consultant.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
public class AddressDTO {


@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
@Column (name="ADDRESS_ID")
public Long addressId;
	
@Column (name="ADDRESS_LINE_1")
public String addressLine1;
	
@Column (name="ADDRESS_LINE_2")
public String addressLine2;

@Column (name="CITY")
public String city;

@Column (name="STATE_")
public String state;

@Column (name="ZIP")
public String zip;

public AddressDTO() {
	super();
}

public Long getAddressId() {
	return addressId;
}

public void setAddressId(Long addressId) {
	this.addressId = addressId;
}

public String getAddressLine1() {
	return addressLine1;
}

public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
}

public String getAddressLine2() {
	return addressLine2;
}

public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getZip() {
	return zip;
}

public void setZip(String zip) {
	this.zip = zip;
}



}
