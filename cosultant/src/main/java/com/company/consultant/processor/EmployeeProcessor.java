package com.company.consultant.processor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.company.consultant.dao.DAO;
import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.dto.EmploymentHistoryDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.models.DocumentObj;
import com.company.consultant.models.EmploymentObj;
import com.company.consultant.models.PersonalInfo;
import com.company.consultant.models.SearchRequest;
import com.company.consultant.repository.PersonalInfoRepository;
import com.company.consultant.util.DtoComparator;
import com.company.consultant.util.DtoConverter;


@Component
public class EmployeeProcessor extends BaseProcessor implements EmployeeProcessorIF {

		
	@Override
	public PersonalInfo processAndSave(PersonalInfo personalInfo) {
		 PersonalInfoDTO personalInfoDTO = DtoConverter.convertToDTO(personalInfo);
		 return DtoConverter.covertFromDTO(dao.save(personalInfoDTO));
	}
		
	@Override
	public EmploymentHistoryDTO processAndSave(EmploymentObj employmentObj) {
		 EmploymentHistoryDTO employmentHistoryDTO = DtoConverter.convertToDTO(employmentObj);
		 PersonalInfoDTO personalInfoDTO = dao.searchById(Long.valueOf(employmentObj.getEmployeeId()));
			if(personalInfoDTO != null){
				personalInfoDTO.getEmploymentHistoryDTO().add(employmentHistoryDTO);
				employmentHistoryDTO.setPersonalInfoDTO(personalInfoDTO);
				 dao.save(personalInfoDTO);
			}	
		return employmentHistoryDTO;
	}
	
	@Override
	public PersonalInfo processAndUpdate(PersonalInfo personalInfo) throws Exception {
		if(StringUtils.isEmpty(personalInfo.getEmployeeId())){
			throw new Exception("Identifier not present.");
		}
		Long id = Long.valueOf(personalInfo.getEmployeeId());
		PersonalInfoDTO personalInfoDTO = dao.searchById(id);
		if(personalInfoDTO == null){
			throw new Exception("No records found for the idenifier : " + id);
		}
		personalInfoDTO = DtoComparator.compareDTO(personalInfoDTO, personalInfo);

		dao.save(personalInfoDTO);
		personalInfoDTO = dao.searchById(id);
		return DtoConverter.covertFromDTO(personalInfoDTO);
	}
	
	@Override
	public PersonalInfo processAndSearchDocs(SearchRequest searchRequest) throws Exception{
		
		Long employeeId = Long.valueOf(searchRequest.getEmployeeId());
		List<DocumentsDTO> documentsDTOs = dao.searchDocumentsById(employeeId);
		if(documentsDTOs != null && documentsDTOs.size() > 0){
			List<DocumentObj> documentObjs = documentsDTOs.stream()
					.map(x -> DtoConverter.convertFromDTO(x))
					.collect(Collectors.toList());
			PersonalInfo personalInfo = new PersonalInfo();
			personalInfo.setEmployeeId(employeeId.toString());
			personalInfo.getDocumnetObj().addAll(documentObjs);
			return personalInfo;
		} else {
			throw new Exception("No records found.");
		}
	}
	
	@Override
	public List<PersonalInfo> processAndSearch() throws Exception {
		
		List<PersonalInfoDTO> allNames = dao.getFullNameAndId();
		if(allNames == null || allNames.size() == 0){
			throw new Exception("No records found");
		}
		
		return DtoConverter.convertFromDTOList(allNames);
	}
	
	@Override
	public List<PersonalInfo> processAndSearch(SearchRequest searchRequest) throws Exception {
		
		String name = searchRequest.getFirstName();
	    String employeeId = searchRequest.getEmployeeId();
		List<PersonalInfo> result = null;				
		
		if(!StringUtils.isEmpty(name)){
			List<PersonalInfoDTO> personalInfoDTOs = dao.search(name);
			if(personalInfoDTOs != null && personalInfoDTOs.size() > 0){
				 result = DtoConverter.convertFromDTOList(personalInfoDTOs);				
			}
		} else if (!StringUtils.isEmpty(employeeId)){
			Long id = new Long(employeeId);
			PersonalInfoDTO personalInfoDTO = dao.searchById(id);
			PersonalInfo personalInfo = null;
			try{				
				if(personalInfoDTO != null){
					personalInfo = DtoConverter.covertFromDTO(personalInfoDTO);
					if(personalInfo != null){
						result = new ArrayList<>();
						result.add(personalInfo);
					}
				}
					
			} catch(Exception ex){
				System.out.println("Error: " + ex.getMessage());
				throw new Exception("No records found.");
			}									
		}		
		return result;
	}

	@Override
	public void processAndDelete(SearchRequest searchRequest) {
		String name = searchRequest.getFirstName();
	    String employeeId = searchRequest.getEmploymentId();
	    if(!StringUtils.isEmpty(employeeId)){
	    	dao.deleteById(EmploymentHistoryDTO.class
	    	,Long.valueOf(employeeId));
	    }
		
	}
	
}