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
@Table(name="CLIENT")
public class ClientDTO implements Cloneable{


@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
@Column (name="CLIENT_ID")
private Long clientId;

@Column(name="CLIENT_NAME")
private String clientName;

@Column(name="CLIENT_HR_NAME")
private String clientHrName;

@Column(name="CLIENT_HR_EMAIL")
private String clientHrEmail;

@Column(name="CLIENT_HR_PHONE")
private String clientHrPhone;

@Column(name="CLIENT_CONTACT_NAME")
private String clientContactName;

@Column(name="CLIENT_CONTACT_EMAIL")
private String clientContactEmail;

@Column(name="CLIENT_CONTACT_PHONE")
private String clientContactPhone;

@OneToOne (cascade=CascadeType.ALL)
@JoinColumn(name="CLIENT_ADDRESS_ID")
public AddressDTO addressDTO;

public ClientDTO() {
	super();
}

public Long getClientId() {
	return clientId;
}

public void setClientId(Long clientId) {
	this.clientId = clientId;
}

public String getClientName() {
	return clientName;
}

public void setClientName(String clientName) {
	this.clientName = clientName;
}

public String getClientHrName() {
	return clientHrName;
}

public void setClientHrName(String clientHrName) {
	this.clientHrName = clientHrName;
}

public String getClientHrEmail() {
	return clientHrEmail;
}

public void setClientHrEmail(String clientHrEmail) {
	this.clientHrEmail = clientHrEmail;
}

public String getClientHrPhone() {
	return clientHrPhone;
}

public void setClientHrPhone(String clientHrPhone) {
	this.clientHrPhone = clientHrPhone;
}

public String getClientContactName() {
	return clientContactName;
}

public void setClientContactName(String clientContactName) {
	this.clientContactName = clientContactName;
}

public String getClientContactEmail() {
	return clientContactEmail;
}

public void setClientContactEmail(String clientContactEmail) {
	this.clientContactEmail = clientContactEmail;
}

public String getClientContactPhone() {
	return clientContactPhone;
}

public void setClientContactPhone(String clientContactPhone) {
	this.clientContactPhone = clientContactPhone;
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
	return "ClientDTO [clientId=" + clientId + ", clientName=" + clientName + ", clientHrName=" + clientHrName
			+ ", clientHrEmail=" + clientHrEmail + ", clientHrPhone=" + clientHrPhone + ", clientContactName="
			+ clientContactName + ", clientContactEmail=" + clientContactEmail + ", clientContactPhone="
			+ clientContactPhone + ", addressDTO=" + addressDTO + "]";
}

}
