//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.03.01 at 01:11:47 PM CST 
//


package com.company.consultant.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for timesheets complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="timesheets"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="timeSheetId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="timesheetGroupId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="employeeId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="timesheetDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="totalHours" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="overTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="projectDetails" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="projectLocation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="isApproved" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="lastUpdated" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="submittedDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="approvedDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="approvedBy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timesheets", propOrder = {
    "timeSheetId",
    "timesheetGroupId",
    "employeeId",
    "status",
    "timesheetDate",
    "startTime",
    "endTime",
    "totalHours",
    "overTime",
    "projectDetails",
    "projectLocation",
    "isApproved",
    "lastUpdated",
    "submittedDate",
    "approvedDate",
    "desc",
    "approvedBy"
})
public class Timesheets {

    @XmlElement(required = true)
    protected String timeSheetId;
    @XmlElement(required = true)
    protected String timesheetGroupId;
    @XmlElement(required = true)
    protected String employeeId;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String timesheetDate;
    @XmlElement(required = true)
    protected String startTime;
    @XmlElement(required = true)
    protected String endTime;
    @XmlElement(required = true)
    protected String totalHours;
    @XmlElement(required = true)
    protected String overTime;
    @XmlElement(required = true)
    protected String projectDetails;
    @XmlElement(required = true)
    protected String projectLocation;
    @XmlElement(required = true)
    protected String isApproved;
    @XmlElement(required = true)
    protected String lastUpdated;
    @XmlElement(required = true)
    protected String submittedDate;
    @XmlElement(required = true)
    protected String approvedDate;
    @XmlElement(required = true)
    protected String desc;
    @XmlElement(required = true)
    protected String approvedBy;

    /**
     * Gets the value of the timeSheetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeSheetId() {
        return timeSheetId;
    }

    /**
     * Sets the value of the timeSheetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeSheetId(String value) {
        this.timeSheetId = value;
    }

    /**
     * Gets the value of the timesheetGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimesheetGroupId() {
        return timesheetGroupId;
    }

    /**
     * Sets the value of the timesheetGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimesheetGroupId(String value) {
        this.timesheetGroupId = value;
    }

    /**
     * Gets the value of the employeeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the value of the employeeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeId(String value) {
        this.employeeId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the timesheetDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimesheetDate() {
        return timesheetDate;
    }

    /**
     * Sets the value of the timesheetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimesheetDate(String value) {
        this.timesheetDate = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the totalHours property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalHours() {
        return totalHours;
    }

    /**
     * Sets the value of the totalHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalHours(String value) {
        this.totalHours = value;
    }

    /**
     * Gets the value of the overTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverTime() {
        return overTime;
    }

    /**
     * Sets the value of the overTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverTime(String value) {
        this.overTime = value;
    }

    /**
     * Gets the value of the projectDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectDetails() {
        return projectDetails;
    }

    /**
     * Sets the value of the projectDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectDetails(String value) {
        this.projectDetails = value;
    }

    /**
     * Gets the value of the projectLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectLocation() {
        return projectLocation;
    }

    /**
     * Sets the value of the projectLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectLocation(String value) {
        this.projectLocation = value;
    }

    /**
     * Gets the value of the isApproved property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsApproved() {
        return isApproved;
    }

    /**
     * Sets the value of the isApproved property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsApproved(String value) {
        this.isApproved = value;
    }

    /**
     * Gets the value of the lastUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the value of the lastUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdated(String value) {
        this.lastUpdated = value;
    }

    /**
     * Gets the value of the submittedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmittedDate() {
        return submittedDate;
    }

    /**
     * Sets the value of the submittedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmittedDate(String value) {
        this.submittedDate = value;
    }

    /**
     * Gets the value of the approvedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovedDate() {
        return approvedDate;
    }

    /**
     * Sets the value of the approvedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovedDate(String value) {
        this.approvedDate = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the approvedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * Sets the value of the approvedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovedBy(String value) {
        this.approvedBy = value;
    }

}