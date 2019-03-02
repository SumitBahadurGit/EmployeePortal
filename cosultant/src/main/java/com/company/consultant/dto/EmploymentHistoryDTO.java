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
public class EmploymentHistoryDTO implements Cloneable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
	@Column(name = "EMP_ID")
	public Long empId;

	@Column(name = "EMP_STATUS")
	public String empStatus;

	@Column(name = "START_DATE")
	public String startDate;

	@Column(name = "END_DATE")
	public String endDate;
	
	@Column(name = "JOB_TITLE")
	public String jobTitle;

	@Column(name = "JOB_DESC")
	public String jobDesc;

	@Column(name = "TECHNOLOGY")
	public String tech;

	@Column(name = "HOURLY_WAGE_REC")
	public String hourlyWageRec;

	@Column(name = "HOURLY_WAGE_PAY")
	public String hourlyWagePay;

	@Column(name = "TERMINATION_REASON")
	public String tempReason;

	@Column(name = "WORK_PERMIT_STATUS")
	public String workPermitStatus;

	@Column(name = "WORK_PERMIT_START")
	public String workPermitStart;

	@Column(name = "WORK_PERMIT_END")
	public String workPermitEnd;

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

    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmploymentHistoryDTO [empId=" + empId + ", empStatus=" + empStatus + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", jobTitle=" + jobTitle + ", jobDesc=" + jobDesc + ", tech=" + tech
				+ ", hourlyWageRec=" + hourlyWageRec + ", hourlyWagePay=" + hourlyWagePay + ", tempReason=" + tempReason
				+ ", workPermitStatus=" + workPermitStatus + ", workPermitStart=" + workPermitStart + ", workPermitEnd="
				+ workPermitEnd + ", vendorDTO=" + vendorDTO + ", clientDTO=" + clientDTO + ", personalInfoDTO="
				+ personalInfoDTO + "]";
	}
    
}
