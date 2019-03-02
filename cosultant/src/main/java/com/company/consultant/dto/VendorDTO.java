package com.company.consultant.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="VENDOR")
public class VendorDTO implements Cloneable{

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
@Column (name="VENDOR_ID")
private Long vendorId;

@Column(name="VENDOR_NAME")
private String vendorName;

@Column(name="VENDOR_HR_NAME")
private String vendorHrName;

@Column(name="VENDOR_HR_EMAIL")
private String vendorHrEmail;

@Column(name="VENDOR_HR_PHONE")
private String vendorHrPhone;

@Column(name="VENDOR_CONTACT_NAME")
private String vendorContactName;

@Column(name="VENDOR_CONTACT_EMAIL")
private String vendorContactEmail;

@Column(name="VENDOR_CONTACT_PHONE")
private String vendorContactPhone;

@OneToOne (cascade=CascadeType.ALL)
@JoinColumn(name="VENDOR_ADDRESS_ID")
public AddressDTO addressDTO;

public VendorDTO() {
	super();
}

public Long getVendorId() {
	return vendorId;
}

public void setVendorId(Long vendorId) {
	this.vendorId = vendorId;
}

public String getVendorName() {
	return vendorName;
}

public void setVendorName(String vendorName) {
	this.vendorName = vendorName;
}

public String getVendorHrName() {
	return vendorHrName;
}

public void setVendorHrName(String vendorHrName) {
	this.vendorHrName = vendorHrName;
}

public String getVendorHrEmail() {
	return vendorHrEmail;
}

public void setVendorHrEmail(String vendorHrEmail) {
	this.vendorHrEmail = vendorHrEmail;
}

public String getVendorHrPhone() {
	return vendorHrPhone;
}

public void setVendorHrPhone(String vendorHrPhone) {
	this.vendorHrPhone = vendorHrPhone;
}

public String getVendorContactName() {
	return vendorContactName;
}

public void setVendorContactName(String vendorContactName) {
	this.vendorContactName = vendorContactName;
}

public String getVendorContactEmail() {
	return vendorContactEmail;
}

public void setVendorContactEmail(String vendorContactEmail) {
	this.vendorContactEmail = vendorContactEmail;
}

public String getVendorContactPhone() {
	return vendorContactPhone;
}

public void setVendorContactPhone(String vendorContactPhone) {
	this.vendorContactPhone = vendorContactPhone;
}

public AddressDTO getAddressDTO() {
	return addressDTO;
}

public void setAddressDTO(AddressDTO addressDTO) {
	this.addressDTO = addressDTO;
}
	
public Object clone() throws CloneNotSupportedException {

    return super.clone();
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "VendorDTO [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorHrName=" + vendorHrName
			+ ", vendorHrEmail=" + vendorHrEmail + ", vendorHrPhone=" + vendorHrPhone + ", vendorContactName="
			+ vendorContactName + ", vendorContactEmail=" + vendorContactEmail + ", vendorContactPhone="
			+ vendorContactPhone + ", addressDTO=" + addressDTO + "]";
}

}
