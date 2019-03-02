//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.03.01 at 01:11:47 PM CST 
//


package com.company.consultant.models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for personalInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="personalInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="employee_id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dob" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="visaStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="secondEmail" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="secondPhone" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="joinedOn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="hiredOn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="manager" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="recruiter" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ssn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="lastUpdated" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="address" type="{}address"/&gt;
 *         &lt;element name="timesheetsObjWrapper" type="{}timesheetsObjWrapper"/&gt;
 *         &lt;element name="documnetObj" type="{}documentObj" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="employmentObj" type="{}employmentObj" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="education"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="educationId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="highestDegree" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="gradYear" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="collegeDegree" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="lastColName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="lastColCountry" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="work"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="workId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="workExpInYear" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="isCurrEmployed" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="lastEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="areaOfWork" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personalInfo", propOrder = {
    "employeeId",
    "firstName",
    "middleName",
    "lastName",
    "dob",
    "country",
    "visaStatus",
    "email",
    "phone",
    "secondEmail",
    "secondPhone",
    "joinedOn",
    "hiredOn",
    "manager",
    "recruiter",
    "ssn",
    "lastUpdated",
    "address",
    "timesheetsObjWrapper",
    "documnetObj",
    "employmentObj",
    "education",
    "work"
})
public class PersonalInfo {

    @XmlElement(name = "employee_id", required = true)
    protected String employeeId;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String middleName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String dob;
    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true)
    protected String visaStatus;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String phone;
    @XmlElement(required = true)
    protected String secondEmail;
    @XmlElement(required = true)
    protected String secondPhone;
    @XmlElement(required = true)
    protected String joinedOn;
    @XmlElement(required = true)
    protected String hiredOn;
    @XmlElement(required = true)
    protected String manager;
    @XmlElement(required = true)
    protected String recruiter;
    @XmlElement(required = true)
    protected String ssn;
    @XmlElement(required = true)
    protected String lastUpdated;
    @XmlElement(required = true)
    protected Address address;
    @XmlElement(required = true)
    protected TimesheetsObjWrapper timesheetsObjWrapper;
    protected List<DocumentObj> documnetObj;
    protected List<EmploymentObj> employmentObj;
    @XmlElement(required = true)
    protected PersonalInfo.Education education;
    @XmlElement(required = true)
    protected PersonalInfo.Work work;

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
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the dob property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDob(String value) {
        this.dob = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the visaStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisaStatus() {
        return visaStatus;
    }

    /**
     * Sets the value of the visaStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisaStatus(String value) {
        this.visaStatus = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the secondEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondEmail() {
        return secondEmail;
    }

    /**
     * Sets the value of the secondEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondEmail(String value) {
        this.secondEmail = value;
    }

    /**
     * Gets the value of the secondPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondPhone() {
        return secondPhone;
    }

    /**
     * Sets the value of the secondPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondPhone(String value) {
        this.secondPhone = value;
    }

    /**
     * Gets the value of the joinedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJoinedOn() {
        return joinedOn;
    }

    /**
     * Sets the value of the joinedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJoinedOn(String value) {
        this.joinedOn = value;
    }

    /**
     * Gets the value of the hiredOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHiredOn() {
        return hiredOn;
    }

    /**
     * Sets the value of the hiredOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHiredOn(String value) {
        this.hiredOn = value;
    }

    /**
     * Gets the value of the manager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManager() {
        return manager;
    }

    /**
     * Sets the value of the manager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManager(String value) {
        this.manager = value;
    }

    /**
     * Gets the value of the recruiter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecruiter() {
        return recruiter;
    }

    /**
     * Sets the value of the recruiter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecruiter(String value) {
        this.recruiter = value;
    }

    /**
     * Gets the value of the ssn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsn(String value) {
        this.ssn = value;
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
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the timesheetsObjWrapper property.
     * 
     * @return
     *     possible object is
     *     {@link TimesheetsObjWrapper }
     *     
     */
    public TimesheetsObjWrapper getTimesheetsObjWrapper() {
        return timesheetsObjWrapper;
    }

    /**
     * Sets the value of the timesheetsObjWrapper property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimesheetsObjWrapper }
     *     
     */
    public void setTimesheetsObjWrapper(TimesheetsObjWrapper value) {
        this.timesheetsObjWrapper = value;
    }

    /**
     * Gets the value of the documnetObj property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documnetObj property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumnetObj().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentObj }
     * 
     * 
     */
    public List<DocumentObj> getDocumnetObj() {
        if (documnetObj == null) {
            documnetObj = new ArrayList<DocumentObj>();
        }
        return this.documnetObj;
    }

    /**
     * Gets the value of the employmentObj property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employmentObj property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmploymentObj().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmploymentObj }
     * 
     * 
     */
    public List<EmploymentObj> getEmploymentObj() {
        if (employmentObj == null) {
            employmentObj = new ArrayList<EmploymentObj>();
        }
        return this.employmentObj;
    }

    /**
     * Gets the value of the education property.
     * 
     * @return
     *     possible object is
     *     {@link PersonalInfo.Education }
     *     
     */
    public PersonalInfo.Education getEducation() {
        return education;
    }

    /**
     * Sets the value of the education property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalInfo.Education }
     *     
     */
    public void setEducation(PersonalInfo.Education value) {
        this.education = value;
    }

    /**
     * Gets the value of the work property.
     * 
     * @return
     *     possible object is
     *     {@link PersonalInfo.Work }
     *     
     */
    public PersonalInfo.Work getWork() {
        return work;
    }

    /**
     * Sets the value of the work property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalInfo.Work }
     *     
     */
    public void setWork(PersonalInfo.Work value) {
        this.work = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="educationId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="highestDegree" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="gradYear" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="collegeDegree" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="lastColName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="lastColCountry" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "educationId",
        "highestDegree",
        "gradYear",
        "collegeDegree",
        "lastColName",
        "lastColCountry"
    })
    public static class Education {

        @XmlElement(required = true)
        protected String educationId;
        @XmlElement(required = true)
        protected String highestDegree;
        @XmlElement(required = true)
        protected String gradYear;
        @XmlElement(required = true)
        protected String collegeDegree;
        @XmlElement(required = true)
        protected String lastColName;
        @XmlElement(required = true)
        protected String lastColCountry;

        /**
         * Gets the value of the educationId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEducationId() {
            return educationId;
        }

        /**
         * Sets the value of the educationId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEducationId(String value) {
            this.educationId = value;
        }

        /**
         * Gets the value of the highestDegree property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHighestDegree() {
            return highestDegree;
        }

        /**
         * Sets the value of the highestDegree property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHighestDegree(String value) {
            this.highestDegree = value;
        }

        /**
         * Gets the value of the gradYear property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGradYear() {
            return gradYear;
        }

        /**
         * Sets the value of the gradYear property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGradYear(String value) {
            this.gradYear = value;
        }

        /**
         * Gets the value of the collegeDegree property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCollegeDegree() {
            return collegeDegree;
        }

        /**
         * Sets the value of the collegeDegree property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCollegeDegree(String value) {
            this.collegeDegree = value;
        }

        /**
         * Gets the value of the lastColName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastColName() {
            return lastColName;
        }

        /**
         * Sets the value of the lastColName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastColName(String value) {
            this.lastColName = value;
        }

        /**
         * Gets the value of the lastColCountry property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastColCountry() {
            return lastColCountry;
        }

        /**
         * Sets the value of the lastColCountry property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastColCountry(String value) {
            this.lastColCountry = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="workId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="workExpInYear" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="isCurrEmployed" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="lastEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="areaOfWork" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "workId",
        "workExpInYear",
        "isCurrEmployed",
        "lastEmployer",
        "areaOfWork"
    })
    public static class Work {

        @XmlElement(required = true)
        protected String workId;
        @XmlElement(required = true)
        protected String workExpInYear;
        @XmlElement(required = true)
        protected String isCurrEmployed;
        @XmlElement(required = true)
        protected String lastEmployer;
        @XmlElement(required = true)
        protected String areaOfWork;

        /**
         * Gets the value of the workId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWorkId() {
            return workId;
        }

        /**
         * Sets the value of the workId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWorkId(String value) {
            this.workId = value;
        }

        /**
         * Gets the value of the workExpInYear property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWorkExpInYear() {
            return workExpInYear;
        }

        /**
         * Sets the value of the workExpInYear property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWorkExpInYear(String value) {
            this.workExpInYear = value;
        }

        /**
         * Gets the value of the isCurrEmployed property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIsCurrEmployed() {
            return isCurrEmployed;
        }

        /**
         * Sets the value of the isCurrEmployed property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIsCurrEmployed(String value) {
            this.isCurrEmployed = value;
        }

        /**
         * Gets the value of the lastEmployer property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastEmployer() {
            return lastEmployer;
        }

        /**
         * Sets the value of the lastEmployer property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastEmployer(String value) {
            this.lastEmployer = value;
        }

        /**
         * Gets the value of the areaOfWork property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAreaOfWork() {
            return areaOfWork;
        }

        /**
         * Sets the value of the areaOfWork property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAreaOfWork(String value) {
            this.areaOfWork = value;
        }

    }

}
