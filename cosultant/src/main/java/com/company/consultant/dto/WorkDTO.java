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
@Table(name="WORK")
public class WorkDTO {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
@Column (name="WORK_ID")
public Long work_id;
	
@Column (name="AREA_OF_WORK")
public String areaOfWork;
	
@Column (name="IS_CURR_EMPLOYED")
public String isCurrEmployed;

@Column (name="LAST_EMPLOYER")
public String lastEmployer;

@Column (name="WORK_EXPNC_YEAR")
public String workExpInYrs;


public WorkDTO() {
	super();
}



public Long getWork_id() {
	return work_id;
}

public void setWork_id(Long work_id) {
	this.work_id = work_id;
}

public String getAreaOfWork() {
	return areaOfWork;
}

public void setAreaOfWork(String areaOfWork) {
	this.areaOfWork = areaOfWork;
}

public String getIsCurrEmployed() {
	return isCurrEmployed;
}

public void setIsCurrEmployed(String isCurrEmployed) {
	this.isCurrEmployed = isCurrEmployed;
}

public String getLastEmployer() {
	return lastEmployer;
}

public void setLastEmployer(String lastEmployer) {
	this.lastEmployer = lastEmployer;
}

public String getWorkExpInYrs() {
	return workExpInYrs;
}

public void setWorkExpInYrs(String workExpInYrs) {
	this.workExpInYrs = workExpInYrs;
}





}
