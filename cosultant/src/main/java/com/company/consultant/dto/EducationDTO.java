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
@Table(name="EDUCATION")
public class EducationDTO {


@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
@Column (name="EDUCATION_ID")
public Long education_id;
	
@Column (name="COLLEGE_DEGREE")
public String collegeDegree;
	
@Column (name="HIGHEST_DEGREE")
public String highestDegree;

@Column (name="GRAD_YEAR")
public String gradYear;

@Column (name="LAST_COLLEGE_COUNTRY")
public String lastCollCountry;

@Column (name="LAST_COLLEGE_NAME")
public String lastCollName;

public EducationDTO() {
	super();
}



public Long getEducation_id() {
	return education_id;
}

public void setEducation_id(Long education_id) {
	this.education_id = education_id;
}

public String getCollegeDegree() {
	return collegeDegree;
}

public void setCollegeDegree(String collegeDegree) {
	this.collegeDegree = collegeDegree;
}

public String getHighestDegree() {
	return highestDegree;
}

public void setHighestDegree(String highestDegree) {
	this.highestDegree = highestDegree;
}

public String getGradYear() {
	return gradYear;
}

public void setGradYear(String gradYear) {
	this.gradYear = gradYear;
}

public String getLastCollCountry() {
	return lastCollCountry;
}

public void setLastCollCountry(String lastCollCountry) {
	this.lastCollCountry = lastCollCountry;
}

public String getLastCollName() {
	return lastCollName;
}

public void setLastCollName(String lastCollName) {
	this.lastCollName = lastCollName;
}




}
