package com.company.consultant.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.exceptions.GcsException;
import com.company.consultant.models.DocumentObj;
import com.company.consultant.models.EmploymentObj;
import com.company.consultant.models.Login;
import com.company.consultant.models.PaginatedWrapper;
import com.company.consultant.models.PersonalInfo;
import com.company.consultant.models.SearchRequest;
import com.company.consultant.models.SearchType;
import com.company.consultant.models.Settings;
import com.company.consultant.models.TimesheetsObjWrapper;
import com.company.consultant.processor.EmployeeProcessorIF;
import com.company.consultant.processor.FileProcessorIF;
import com.company.consultant.processor.LoginProcessIf;
import com.company.consultant.processor.LoginProcessor;
import com.company.consultant.processor.SettingProcessor;
import com.company.consultant.processor.TimesheetsProcessorIf;

@Component
public class Manager {

	@Autowired
	EmployeeProcessorIF employeeProcessor;
	
	@Autowired
	FileProcessorIF fileProcessor;
	
	@Autowired
	TimesheetsProcessorIf timesheetsProcessorIf;
	
	@Autowired 
	LoginProcessor loginProcessor;
	
	@Autowired
	SettingProcessor settingProcessor;
	
	public Object manageSave(Object obj) throws GcsException {

		if(obj instanceof PersonalInfo){
			return employeeProcessor.processAndSave((PersonalInfo) obj);
		} else if (obj instanceof EmploymentObj){
			 return employeeProcessor.processAndSave((EmploymentObj) obj);
		} else if (obj instanceof TimesheetsObjWrapper){
			return timesheetsProcessorIf.processAndSave((TimesheetsObjWrapper)obj);
		} else if(obj instanceof Login) {
			return loginProcessor.processAndSave((Login)obj);
		}
		return obj;

	}

	public Object manageUpdate(Object obj) throws GcsException{
		if(obj instanceof PersonalInfo){
			return employeeProcessor.processAndUpdate((PersonalInfo) obj);
		} else if(obj instanceof DocumentObj){
			return fileProcessor.processAndUpdate((DocumentObj) obj);			
		} else if (obj instanceof Settings){
			return settingProcessor.processAndSave(obj);
		}
		return null;
	}

	public List<?> manageUpdates (List<DocumentObj> documentObjs) throws GcsException {

		if(documentObjs instanceof List<?>){
			if(documentObjs.size() > 0){
				if(documentObjs.get(0) instanceof DocumentObj){
					return (List<DocumentObj>)fileProcessor.processAndUpdates(documentObjs);
				}
			}
		}
		
		return null;
	}
	
	public List<?> manageUpload(Object[] obj) throws GcsException {
		
		if(obj instanceof MultipartFile[]){
			return fileProcessor.processAndUpload((MultipartFile[])obj);
			
		}
		return null;

	}

	public List<?> manageSearch(Object obj) throws GcsException {
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
		} else if (obj instanceof PaginatedWrapper){
			return employeeProcessor.processAndSearch((PaginatedWrapper) obj);
		} else if (obj instanceof TimesheetsObjWrapper){
			return timesheetsProcessorIf.getAllTimesSheetsByEmpId((TimesheetsObjWrapper)obj);
		} else if(obj instanceof Settings){
			return new ArrayList<>(Arrays.asList(settingProcessor.search((Settings)obj)));
		}
		return null;

	}
	
	public void manageDelete(Object obj) throws GcsException{
		if (obj instanceof SearchRequest) {
			employeeProcessor.processAndDelete((SearchRequest) obj);
		}
	}

	public void manageDelete(List<Long> ids, Class className) throws GcsException{
		if (className.equals(DocumentsDTO.class)) {
			fileProcessor.processAndDelete(ids);
		}
	}
	public Object check(Object obj) {
		if (obj instanceof TimesheetsObjWrapper){
			return timesheetsProcessorIf.check((TimesheetsObjWrapper)obj);
		} if(obj instanceof Login){
			return loginProcessor.check((Login)obj);
		}
		return false;
	}
	
	
}
