package com.company.consultant.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ALL_SETTINGS")
public class SettingsDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
	@Column(name = "SETTINGS_ID")
	public Long settingsId;
	
	@Column(name = "SETTINGS_DETAILS")
	public String settingsDetails;

	@Column(name = "EMPLOYEE_ID")
	public Long employeeId;
	
	@Column(name = "SETTINGS_TYPE")
	public String settingType;

	
	public SettingsDTO() {
		super();
	}

	public SettingsDTO(Long settingsId, String settingsDetails, Long employeeId, String settingType) {
		super();
		this.settingsId = settingsId;
		this.settingsDetails = settingsDetails;
		this.employeeId = employeeId;
		this.settingType = settingType;
	}

	/**
	 * @return the settingsId
	 */
	public Long getSettingsId() {
		return settingsId;
	}

	/**
	 * @param settingsId the settingsId to set
	 */
	public void setSettingsId(Long settingsId) {
		this.settingsId = settingsId;
	}

	/**
	 * @return the settingsDetails
	 */
	public String getSettingsDetails() {
		return settingsDetails;
	}

	/**
	 * @param settingsDetails the settingsDetails to set
	 */
	public void setSettingsDetails(String settingsDetails) {
		this.settingsDetails = settingsDetails;
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
	 * @return the settingType
	 */
	public String getSettingType() {
		return settingType;
	}

	/**
	 * @param settingType the settingType to set
	 */
	public void setSettingType(String settingType) {
		this.settingType = settingType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SettingsDTO [settingsId=" + settingsId + ", settingsDetails=" + settingsDetails + ", employeeId="
				+ employeeId + ", settingType=" + settingType + "]";
	}
	

	
	
	
}
