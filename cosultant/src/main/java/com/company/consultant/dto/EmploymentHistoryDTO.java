package com.company.consultant.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEMNT_HIST")
public class EmploymentHistoryDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
	@Column(name = "EMP_ID")
	private Long empId;

	@Column(name = "EMP_STATUS")
	private String empStatus;

	@Column(name = "START_DATE")
	private String startDate;

	@Column(name = "END_DATE")
	private String endDate;
	
	@Column(name = "JOB_TITLE")
	private String jobTitle;

	@Column(name = "JOB_DESC")
	private String jobDesc;

	@Column(name = "TECHNOLOGY")
	private String tech;

	@Column(name = "HOURLY_WAGE_REC")
	private String hourlyWageRec;

	@Column(name = "HOURLY_WAGE_PAY")
	private String hourlyWagePay;

	@Column(name = "TERMINATION_REASON")
	private String tempReason;

	@Column(name = "WORK_PERMIT_STATUS")
	private String workPermitStatus;

	@Column(name = "WORK_PERMIT_START")
	private String workPermitStart;

	@Column(name = "WORK_PERMIT_END")
	private String workPermitEnd;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "VENDOR_ID")
	public VendorDTO vendorDTO;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENT_ID")
	public ClientDTO clientDTO;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
	public PersonalInfoDTO personalInfoDTO;

	public EmploymentHistoryDTO() {
		super();
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getHourlyWageRec() {
		return hourlyWageRec;
	}

	public void setHourlyWageRec(String hourlyWageRec) {
		this.hourlyWageRec = hourlyWageRec;
	}

	public String getHourlyWagePay() {
		return hourlyWagePay;
	}

	public void setHourlyWagePay(String hourlyWagePay) {
		this.hourlyWagePay = hourlyWagePay;
	}

	public String getTempReason() {
		return tempReason;
	}

	public void setTempReason(String tempReason) {
		this.tempReason = tempReason;
	}

	public String getWorkPermitStatus() {
		return workPermitStatus;
	}

	public void setWorkPermitStatus(String workPermitStatus) {
		this.workPermitStatus = workPermitStatus;
	}

	public String getWorkPermitStart() {
		return workPermitStart;
	}

	public void setWorkPermitStart(String workPermitStart) {
		this.workPermitStart = workPermitStart;
	}

	public String getWorkPermitEnd() {
		return workPermitEnd;
	}

	public void setWorkPermitEnd(String workPermitEnd) {
		this.workPermitEnd = workPermitEnd;
	}

	public VendorDTO getVendorDTO() {
		return vendorDTO;
	}

	public void setVendorDTO(VendorDTO vendorDTO) {
		this.vendorDTO = vendorDTO;
	}

	public ClientDTO getClientDTO() {
		return clientDTO;
	}

	public void setClientDTO(ClientDTO clientDTO) {
		this.clientDTO = clientDTO;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public PersonalInfoDTO getPersonalInfoDTO() {
		return personalInfoDTO;
	}

	public void setPersonalInfoDTO(PersonalInfoDTO personalInfoDTO) {
		this.personalInfoDTO = personalInfoDTO;
	}

	
}
