package com.company.consultant.processor;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.company.consultant.dto.EmploymentHistoryDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.exceptions.GcsException;
import com.company.consultant.models.EmploymentObj;
import com.company.consultant.models.PaginatedWrapper;
import com.company.consultant.models.PersonalInfo;
import com.company.consultant.models.SearchRequest;

public interface EmployeeProcessorIF {

	public PersonalInfo processAndSave(PersonalInfo personalInfo) throws GcsException;
	public List<PersonalInfo> processAndSearch() throws GcsException;
	PersonalInfo processAndUpdate(PersonalInfo personalInfo) throws GcsException;
	EmploymentHistoryDTO processAndSave(EmploymentObj employmentObj) throws GcsException;
	List<PersonalInfo> processAndSearch(SearchRequest searchRequest) throws GcsException;
	PersonalInfo processAndSearchDocs(SearchRequest searchRequest) throws GcsException;
	public void processAndDelete(SearchRequest obj);
	public List<?> processAndSearch(PaginatedWrapper paginatedWrapper) throws GcsException;

}
