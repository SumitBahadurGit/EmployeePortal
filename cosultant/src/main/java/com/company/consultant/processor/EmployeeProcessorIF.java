package com.company.consultant.processor;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.company.consultant.dto.EmploymentHistoryDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.models.EmploymentObj;
import com.company.consultant.models.PersonalInfo;
import com.company.consultant.models.SearchRequest;

public interface EmployeeProcessorIF {

	public PersonalInfo processAndSave(PersonalInfo personalInfo);
	public List<PersonalInfo> processAndSearch() throws Exception;
	PersonalInfo processAndUpdate(PersonalInfo personalInfo) throws Exception;
	EmploymentHistoryDTO processAndSave(EmploymentObj employmentObj);
	List<PersonalInfo> processAndSearch(SearchRequest searchRequest) throws Exception;
	PersonalInfo processAndSearchDocs(SearchRequest searchRequest) throws Exception;
	public void processAndDelete(SearchRequest obj);

}
