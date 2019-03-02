package com.company.consultant.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIMESHEETS")
public class TimesheetsDTO implements Cloneable, Comparable<TimesheetsDTO>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
	@Column(name = "TIMESHEET_ID")
	public Long timeSheetId;

	@Column(name = "TIMESHEET_GROUP_ID")
	public Long timesheetGroupId;
	
	@Column(name = "EMPLOYEE_ID")
	public Long employeeId;

	@Column(name = "TIMESHEET_DATE")
	public Date timeSheetDate;

	@Column(name = "STATUS")
	public String status;
		
	@Column(name = "START_TIME")
	public String startTime;

	@Column(name = "END_TIME")
	public String endTime;

	@Column(name = "TOTAL_HOURS")
	public double totalHours;

	@Column(name = "OVER_TIME")
	public double overTime;

	@Column(name = "PROJECT_DETAILS")
	public String projectDetails;

	@Column(name = "PROJECT_LOCATION")
	public String projectLocation;

	@Column(name = "IS_APPROVED")
	public String isApproved;

	@Column(name = "LAST_UPDATED")
	public Date lastUpdated;

	@Column(name = "SUBMITTED")
	public Date submittedDate;

	@Column(name = "APPROVED")
	public Date approvedDate;
	
	@Column(name = "APPROVED_BY")
	public String approvedBy;

	@Column(name = "TS_DESC")
	public String desc;

	public TimesheetsDTO() {
		super();
	}



	public TimesheetsDTO(Long timeSheetId, Long timesheetGroupId, Long employeeId, Date timeSheetDate, String status,
			String startTime, String endTime, double totalHours, double overTime, String projectDetails,
			String projectLocation, String isApproved, Date lastUpdated, Date submittedDate, Date approvedDate,
			String approvedBy, String desc) {
		super();
		this.timeSheetId = timeSheetId;
		this.timesheetGroupId = timesheetGroupId;
		this.employeeId = employeeId;
		this.timeSheetDate = timeSheetDate;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalHours = totalHours;
		this.overTime = overTime;
		this.projectDetails = projectDetails;
		this.projectLocation = projectLocation;
		this.isApproved = isApproved;
		this.lastUpdated = lastUpdated;
		this.submittedDate = submittedDate;
		this.approvedDate = approvedDate;
		this.approvedBy = approvedBy;
		this.desc = desc;
	}



	/**
	 * @return the timeSheetId
	 */
	public Long getTimeSheetId() {
		return timeSheetId;
	}

	/**
	 * @param timeSheetId the timeSheetId to set
	 */
	public void setTimeSheetId(Long timeSheetId) {
		this.timeSheetId = timeSheetId;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the tDate
	 */
	public Date getTimeSheetDate() {
		return timeSheetDate;
	}

	/**
	 * @param tDate the tDate to set
	 */
	public void setTimeSheetDate(Date timeSheetDate) {
		this.timeSheetDate = timeSheetDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the totalHours
	 */
	public double getTotalHours() {
		return totalHours;
	}

	/**
	 * @param totalHours the totalHours to set
	 */
	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	/**
	 * @return the overTime
	 */
	public double getOverTime() {
		return overTime;
	}

	/**
	 * @param overTime the overTime to set
	 */
	public void setOverTime(double overTime) {
		this.overTime = overTime;
	}

	/**
	 * @return the projectDetails
	 */
	public String getProjectDetails() {
		return projectDetails;
	}

	/**
	 * @param projectDetails the projectDetails to set
	 */
	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}

	/**
	 * @return the projectLocation
	 */
	public String getProjectLocation() {
		return projectLocation;
	}

	/**
	 * @param projectLocation the projectLocation to set
	 */
	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	/**
	 * @return the isApproved
	 */
	public String getIsApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate the submittedDate to set
	 */
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	/**
	 * @return the approvedDate
	 */
	public Date getApprovedDate() {
		return approvedDate;
	}

	/**
	 * @param approvedDate the approvedDate to set
	 */
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the approvedBy
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * @param approvedBy the approvedBy to set
	 */
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimesheetsDTO [timeSheetId=" + timeSheetId + ", timesheetGroupId=" + timesheetGroupId + ", employeeId="
				+ employeeId + ", timeSheetDate=" + timeSheetDate + ", status=" + status + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", totalHours=" + totalHours + ", overTime=" + overTime + ", projectDetails="
				+ projectDetails + ", projectLocation=" + projectLocation + ", isApproved=" + isApproved
				+ ", lastUpdated=" + lastUpdated + ", submittedDate=" + submittedDate + ", approvedDate=" + approvedDate
				+ ", approvedBy=" + approvedBy + ", desc=" + desc + "]";
	}



	/**
	 * @return the timesheetGroupId
	 */
	public Long getTimesheetGroupId() {
		return timesheetGroupId;
	}



	/**
	 * @param timesheetGroupId the timesheetGroupId to set
	 */
	public void setTimesheetGroupId(Long timesheetGroupId) {
		this.timesheetGroupId = timesheetGroupId;
	}



	@Override
	public int compareTo(TimesheetsDTO o) {
		return this.getTimeSheetDate().compareTo(o.getTimeSheetDate());
	}	
}
