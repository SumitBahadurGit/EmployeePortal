package com.company.consultant.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.company.consultant.models.EmploymentObj;
import com.company.consultant.models.PersonalInfo;
import com.company.consultant.models.SearchRequest;
import com.company.consultant.models.SearchType;
import com.company.consultant.processor.EmployeeProcessorIF;
import com.company.consultant.processor.FileProcessorIF;

@Component
public class Manager {

	@Autowired
	EmployeeProcessorIF employeeProcessor;
	
	@Autowired
	FileProcessorIF fileProcessor;
	
	
	public Object manageSave(Object obj) {

		if(obj instanceof PersonalInfo){
			return employeeProcessor.processAndSave((PersonalInfo) obj);
		} else if (obj instanceof EmploymentObj){
			 return employeeProcessor.processAndSave((EmploymentObj) obj);
		}
		return obj;

	}

	public Object manageUpdate(Object obj) throws Exception {
		if(obj instanceof PersonalInfo){
			return employeeProcessor.processAndUpdate((PersonalInfo) obj);
		} 
		return null;
	}

	public List<?> manageUpload(Object[] obj) throws Exception {
		
		if(obj instanceof MultipartFile[]){
			return fileProcessor.processAndUpload((MultipartFile[])obj);
			
		}
		return null;

	}

	public List<?> manageSearch(Object obj) throws Exception {
		if (obj == null) {
			return employeeProcessor.processAndSearch();
		} else if (obj instanceof SearchRequest) {
			if (SearchType.DOCUMENT.toString()
					.equals
					(((SearchRequest) obj).getSearchType())) {
				return new ArrayList<>(Arrays.asList(employeeProcessor.processAndSearchDocs((SearchRequest)obj)));
			} else {
				return employeeProcessor.processAndSearch((SearchRequest) obj);
			}
		}
		return null;

	}
	
	public void manageDelete(Object obj) throws Exception{
		if (obj instanceof SearchRequest) {
			employeeProcessor.processAndDelete((SearchRequest) obj);
		}
	}
	
	
}
