package com.company.consultant.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DOCUMENTS")
public class DocumentsDTO implements Cloneable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "GEN_SEQ")
	@Column(name = "DOC_ID")
	public Long docId;
	
	@Column(name = "FILE_NAME")
	public String fileName;

	@Column(name = "FILE_TYPE")
	public String fileType;
	
	@Column(name = "FILE_SIZE")
	public String fileSize;
	
	@Column(name = "LAST_UPDATED")
	public Timestamp lastUpdated;
	
	@Column(name = "DOC_DESC")
	public String docDesc;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
	public PersonalInfoDTO personalInfoDTO;

	public DocumentsDTO() {
		super();
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getDescription() {
		return docDesc;
	}

	public void setDescription(String description) {
		this.docDesc = description;
	}

	public PersonalInfoDTO getPersonalInfoDTO() {
		return personalInfoDTO;
	}

	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
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
		return "DocumentsDTO [docId=" + docId + ", fileName=" + fileName + ", fileType=" + fileType + ", fileSize="
				+ fileSize + ", lastUpdated=" + lastUpdated + ", description=" + docDesc + ", personalInfoDTO="
				+ personalInfoDTO + "]";
	}
    
    
}
