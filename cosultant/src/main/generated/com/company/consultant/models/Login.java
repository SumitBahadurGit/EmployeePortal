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
 * <p>Java class for login complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="login"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="oldPw" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pw" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="eid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="attempts" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="userRole" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "login", propOrder = {
    "loginId",
    "userName",
    "oldPw",
    "pw",
    "eid",
    "status",
    "attempts",
    "userRole"
})
public class Login {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long loginId;
    @XmlElement(required = true, nillable = true)
    protected String userName;
    @XmlElement(required = true, nillable = true)
    protected String oldPw;
    @XmlElement(required = true, nillable = true)
    protected String pw;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long eid;
    @XmlElement(required = true, nillable = true)
    protected String status;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer attempts;
    @XmlElement(required = true, nillable = true)
    protected String userRole;

    /**
     * Gets the value of the loginId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLoginId(Long value) {
        this.loginId = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the oldPw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldPw() {
        return oldPw;
    }

    /**
     * Sets the value of the oldPw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldPw(String value) {
        this.oldPw = value;
    }

    /**
     * Gets the value of the pw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPw() {
        return pw;
    }

    /**
     * Sets the value of the pw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPw(String value) {
        this.pw = value;
    }

    /**
     * Gets the value of the eid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEid() {
        return eid;
    }

    /**
     * Sets the value of the eid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEid(Long value) {
        this.eid = value;
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
     * Gets the value of the attempts property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAttempts() {
        return attempts;
    }

    /**
     * Sets the value of the attempts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAttempts(Integer value) {
        this.attempts = value;
    }

    /**
     * Gets the value of the userRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets the value of the userRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserRole(String value) {
        this.userRole = value;
    }

}
