package com.company.consultant.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="LOG_IN")
public class LogInDTO implements Cloneable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
	@Column(name = "LOGIN_ID")
	public Long loginId;

	@Column(name = "USERNAME_")
	public String userName;

	@Column(name = "OLD_PASSWORD_")
	public String oldPw;

	@Column(name = "PASSWORD_")
	public String pw;

	@Column(name = "EMPLOYEE_ID")
	public Long eid;
	
	@Column(name = "CUR_STATUS")
	public String status;

	@Column(name = "ATTEMPTS")
	public int attempts;
	
	@Column (name = "USER_ROLE")
	public String userRole;



	public LogInDTO(Long loginId, String userName, String oldPw, String pw, Long eid, String status, int attempts,
			String userRole) {
		super();
		this.loginId = loginId;
		this.userName = userName;
		this.oldPw = oldPw;
		this.pw = pw;
		this.eid = eid;
		this.status = status;
		this.attempts = attempts;
		this.userRole = userRole;
	}

	public LogInDTO() {
		super();
	}

	/**
	 * @return the loginId
	 */
	public Long getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the oldPw
	 */
	public String getOldPw() {
		return oldPw;
	}

	/**
	 * @param oldPw the oldPw to set
	 */
	public void setOldPw(String oldPw) {
		this.oldPw = oldPw;
	}

	/**
	 * @return the pw
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * @param pw the pw to set
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}

	/**
	 * @return the eid
	 */
	public Long getEid() {
		return eid;
	}

	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the attempts
	 */
	public int getAttempts() {
		return attempts;
	}

	/**
	 * @param attempts the attempts to set
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	/**
	 * @param eid the eid to set
	 */
	public void setEid(Long eid) {
		this.eid = eid;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LogInDTO [loginId=" + loginId + ", userName=" + userName + ", oldPw=" + oldPw + ", pw=" + pw + ", eid="
				+ eid + ", status=" + status + ", attempts=" + attempts + ", userRole=" + userRole + "]";
	}
	
	
}
