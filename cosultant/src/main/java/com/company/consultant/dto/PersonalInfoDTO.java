package com.company.consultant.dto;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONAL_INFO")
public class PersonalInfoDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
	@Column(name = "EMPLOYEE_ID")
	public Long employeeId;

	@Column(name = "FIRST_NAME")
	public String firstName;

	@Column(name = "MIDDLE_NAME")
	public String middleName;

	@Column(name = "LAST_NAME")
	public String lastName;

	@Column(name = "DOB")
	public String dob;

	@Column(name = "VISA_STATUS")
	public String visaStatus;

	@Column(name = "COUNTRY")
	public String country;

	@Column(name = "EMAIL")
	public String email;

	@Column(name = "PHONE")
	public String phone;

	@Column(name = "PHONE_2")
	public String secondPhone;

	@Column(name = "EMAIL_2")
	public String secondEmail;

	@Column(name = "JOINED")
	public String joinedDate;

	@Column(name = "HIRED")
	public String hiredDate;

	@Column(name = "MANAGER_NAME")
	public String managerName;

	@Column(name = "RECRUITER_NAME")
	public String recruiterName;

	@Column(name = "SSN")
	public String ssn;

	@Column(name = "LAST_UPDATED")
	public Timestamp lastUpdated;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "WORK_ID")
	public WorkDTO workDTO;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EDUCATION_ID")
	public EducationDTO educationDTO;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	public AddressDTO addressDTO;

	@OneToMany(mappedBy = "personalInfoDTO", cascade = CascadeType.ALL)
	public Set<EmploymentHistoryDTO> employmentHistoryDTO;

	@OneToMany(mappedBy = "personalInfoDTO", cascade = CascadeType.ALL)
	public Set<DocumentsDTO> documentsDTO;

	public PersonalInfoDTO() {
		super();
	}

	public PersonalInfoDTO(Long employeeId, String firstName, String lastName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public PersonalInfoDTO(Long employeeId, String firstName, String lastName, String dob, String visaStatus,
			String country, String email, String phone, WorkDTO workDTO, EducationDTO educationDTO,
			AddressDTO addressDTO) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.visaStatus = visaStatus;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.workDTO = workDTO;
		this.educationDTO = educationDTO;
		this.addressDTO = addressDTO;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getVisaStatus() {
		return visaStatus;
	}

	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public WorkDTO getWorkDTO() {
		return workDTO;
	}

	public void setWorkDTO(WorkDTO workDTO) {
		this.workDTO = workDTO;
	}

	public EducationDTO getEducationDTO() {
		return educationDTO;
	}

	public void setEducationDTO(EducationDTO educationDTO) {
		this.educationDTO = educationDTO;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public Set<EmploymentHistoryDTO> getEmploymentHistoryDTO() {
		return employmentHistoryDTO;
	}

	public void setEmploymentHistoryDTO(Set<EmploymentHistoryDTO> employmentHistoryDTO) {
		this.employmentHistoryDTO = employmentHistoryDTO;
	}

	public String getSecondPhone() {
		return secondPhone;
	}

	public void setSecondPhone(String secondPhone) {
		this.secondPhone = secondPhone;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<DocumentsDTO> getDocumentsDTO() {
		return documentsDTO;
	}

	public void setDocumentsDTO(Set<DocumentsDTO> documentsDTO) {
		this.documentsDTO = documentsDTO;
	}
	
}
