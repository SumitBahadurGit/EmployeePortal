package com.company.consultant.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.Doc;

import org.springframework.util.StringUtils;

import com.company.consultant.dto.AddressDTO;
import com.company.consultant.dto.ClientDTO;
import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.dto.EducationDTO;
import com.company.consultant.dto.EmploymentHistoryDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.dto.VendorDTO;
import com.company.consultant.dto.WorkDTO;
import com.company.consultant.models.Address;
import com.company.consultant.models.DocumentObj;
import com.company.consultant.models.EmploymentObj;
import com.company.consultant.models.EmploymentObj.Client;
import com.company.consultant.models.EmploymentObj.Vendor;
import com.company.consultant.models.PersonalInfo;
import com.company.consultant.models.PersonalInfo.Education;
import com.company.consultant.models.PersonalInfo.Work;



public class DtoConverter {

	public static List<PersonalInfo> convertFromDTOList(List<PersonalInfoDTO> list){
		
		List<PersonalInfo> result =list.stream().map(x ->  covertFromDTO(x)).collect(Collectors.toList());
		return result;		
	}
	
	public static PersonalInfo covertFromDTO (PersonalInfoDTO personalInfoDTO){
		
		PersonalInfo personalInfo = new PersonalInfo();
		personalInfo.setFirstName(personalInfoDTO.getFirstName());
		personalInfo.setMiddleName(personalInfoDTO.getMiddleName());
		personalInfo.setLastName(personalInfoDTO.getLastName());
		personalInfo.setDob(personalInfoDTO.getDob());
		personalInfo.setEmail(personalInfoDTO.getEmail());
		personalInfo.setPhone(personalInfoDTO.getPhone());
		personalInfo.setVisaStatus(personalInfoDTO.getVisaStatus());
		personalInfo.setEmployeeId(String.valueOf(personalInfoDTO.getEmployeeId()));
		personalInfo.setCountry(personalInfoDTO.getCountry());
		personalInfo.setSecondEmail(personalInfoDTO.getSecondEmail());
		personalInfo.setSecondPhone(personalInfoDTO.getSecondPhone());
		personalInfo.setJoinedOn(personalInfoDTO.getJoinedDate());
		personalInfo.setHiredOn(personalInfoDTO.getHiredDate());
		personalInfo.setManager(personalInfoDTO.getManagerName());
		personalInfo.setRecruiter(personalInfoDTO.getRecruiterName());
		personalInfo.setSsn(personalInfoDTO.getSsn());
		if(personalInfoDTO.getLastUpdated() != null){
			personalInfo.setLastUpdated(personalInfoDTO.getLastUpdated().toString());			
		}
		
		if(personalInfoDTO.getWorkDTO() != null){
			personalInfo.setWork(new Work());
			personalInfo.getWork().setAreaOfWork(personalInfoDTO.getWorkDTO().getAreaOfWork());
			personalInfo.getWork().setIsCurrEmployed(personalInfoDTO.getWorkDTO().getIsCurrEmployed());
			personalInfo.getWork().setLastEmployer(personalInfoDTO.getWorkDTO().getLastEmployer());
			personalInfo.getWork().setWorkExpInYear(personalInfoDTO.getWorkDTO().getWorkExpInYrs());			
			personalInfo.getWork().setWorkId(String.valueOf(personalInfoDTO.getWorkDTO().getWork_id()));
		}

		if(personalInfoDTO.getEducationDTO() != null){
			personalInfo.setEducation(new Education());
			personalInfo.getEducation().setCollegeDegree(personalInfoDTO.getEducationDTO().getCollegeDegree());
			personalInfo.getEducation().setGradYear(personalInfoDTO.getEducationDTO().getGradYear());
			personalInfo.getEducation().setHighestDegree(personalInfoDTO.getEducationDTO().getHighestDegree());
			personalInfo.getEducation().setLastColCountry(personalInfoDTO.getEducationDTO().getLastCollCountry());
			personalInfo.getEducation().setLastColName(personalInfo.getEducation().getLastColName());			
			personalInfo.getEducation().setEducationId(String.valueOf(personalInfoDTO.getEducationDTO().getEducation_id()));
		}

		if(personalInfoDTO.getAddressDTO() != null){
			
			personalInfo.setAddress(new Address());
			personalInfo.getAddress().setAddressLine1(personalInfoDTO.getAddressDTO().getAddressLine1());
			personalInfo.getAddress().setAddressLine2(personalInfoDTO.getAddressDTO().getAddressLine2());
			personalInfo.getAddress().setCity(personalInfoDTO.getAddressDTO().getCity());
			personalInfo.getAddress().setState(personalInfoDTO.getAddressDTO().getState());
			personalInfo.getAddress().setZipCode(personalInfoDTO.getAddressDTO().getZip());			
			personalInfo.getAddress().setAddressId(String.valueOf(personalInfoDTO.getAddressDTO().getAddressId()));
		}
		
		if(personalInfoDTO.getDocumentsDTO() != null){

			for(DocumentsDTO documentsDTO : personalInfoDTO.getDocumentsDTO()){
				DocumentObj documentObj = convertFromDTO(documentsDTO);
				if(documentObj != null){
					documentObj.setEmployeeId(documentsDTO.getPersonalInfoDTO().getEmployeeId().toString());
					personalInfo.getDocumnetObj().add(documentObj);
				}
			}
		}
		
		if(personalInfoDTO.getEmploymentHistoryDTO() != null){
			for(EmploymentHistoryDTO employmentHistoryDTO : personalInfoDTO.getEmploymentHistoryDTO()){
				
				EmploymentObj employmentObj = new EmploymentObj();

				personalInfo.getEmploymentObj().add(employmentObj);
				
				employmentObj.setEmpAuthEnd(employmentHistoryDTO.getWorkPermitEnd());
				employmentObj.setEmpAuthStart(employmentHistoryDTO.getWorkPermitStart());
				employmentObj.setEmpAuthStatus(employmentHistoryDTO.getEmpStatus());

				employmentObj.setEmploymentId(String.valueOf(employmentHistoryDTO.getEmpId()));
				employmentObj.setEmployeeId(String.valueOf(employmentHistoryDTO.getPersonalInfoDTO().getEmployeeId()));
				employmentObj.setEndDate(employmentHistoryDTO.getEndDate());
				employmentObj.setHourlyWagePay(employmentHistoryDTO.getHourlyWagePay());
				employmentObj.setHourlyWageRecv(employmentHistoryDTO.getHourlyWageRec());
				
				employmentObj.setJobTitle(employmentHistoryDTO.getJobTitle());
				employmentObj.setJobDesc(employmentHistoryDTO.getJobDesc());
				employmentObj.setStartDate(employmentHistoryDTO.getStartDate());
				employmentObj.setTechnology(employmentHistoryDTO.getTech());
				employmentObj.setTermntnReason(employmentHistoryDTO.getTempReason());
				employmentObj.setStatus(employmentHistoryDTO.getEmpStatus());
				
				VendorDTO vendorDTO = employmentHistoryDTO.getVendorDTO();
				ClientDTO clientDTO = employmentHistoryDTO.getClientDTO();
				
				if(clientDTO != null){
					Client clientInfo = new Client();				
					if(clientDTO.getAddressDTO() != null){
						clientInfo.setClientAddress(convertFromDTO(clientDTO.getAddressDTO()));
					}
					clientInfo.setClientContactEmail(clientDTO.getClientContactEmail());
					clientInfo.setClientContactName(clientDTO.getClientContactName());
					clientInfo.setClientContactPhone(clientDTO.getClientContactPhone());
					clientInfo.setClientHrEmail(clientDTO.getClientHrEmail());
					clientInfo.setClientHrName(clientDTO.getClientHrName());
					clientInfo.setClientHrPhone(clientDTO.getClientHrPhone());
					clientInfo.setClientId(String.valueOf(clientDTO.getClientId()));
					clientInfo.setClientName(clientDTO.getClientName());
					
					employmentObj.setClient(clientInfo);
				}
				
				if(vendorDTO != null){
					Vendor vendorInfo = new Vendor();				
					if(vendorDTO.getAddressDTO() != null){
						vendorInfo.setVendorAddress(convertFromDTO(vendorDTO.getAddressDTO()));
					}
					vendorInfo.setVendorContactEmail(vendorDTO.getVendorContactEmail());
					vendorInfo.setVendorContactName(vendorDTO.getVendorContactName());
					vendorInfo.setVendorContactPhone(vendorDTO.getVendorContactPhone());
					vendorInfo.setVendorHrEmail(vendorDTO.getVendorHrEmail());
					vendorInfo.setVendorHrName(vendorDTO.getVendorHrName());
					vendorInfo.setVendorHrPhone(vendorDTO.getVendorHrPhone());
					vendorInfo.setVendorId(String.valueOf(vendorDTO.getVendorId()));
					vendorInfo.setVendorName(vendorDTO.getVendorName());
					employmentObj.setVendor(vendorInfo);
				}	
			}

		}
		
		return personalInfo;				
	}
	
	public static Address convertFromDTO(AddressDTO addressDTO){
		Address address = new Address();
		address.setAddressLine1(addressDTO.getAddressLine1());
		address.setAddressLine2(addressDTO.getAddressLine2());
		address.setCity(addressDTO.getCity());
		address.setState(addressDTO.getState());
		address.setZipCode(addressDTO.getZip());
		return address;
	}
	
	public static DocumentObj convertFromDTO(DocumentsDTO documentsDTO){
		DocumentObj documentObj = new DocumentObj();
		if(documentsDTO.getDocId() != null){
			documentObj.setDocId(documentsDTO.getDocId().toString());
		}

		documentObj.setFileName(documentsDTO.getFileName());
		documentObj.setFileSize(documentsDTO.getFileSize());
		documentObj.setFileType(documentsDTO.getFileType());
		documentObj.setDocDesc(documentsDTO.getDescription());
		if(documentsDTO.getLastUpdated() != null){
			documentObj.setLastUpdated(documentsDTO.getLastUpdated().toString());			
		}
		return documentObj;
	}
	
	public static DocumentsDTO convertToDTO(DocumentObj documentObj){
		DocumentsDTO documentsDTO = new DocumentsDTO();
		documentsDTO.setFileName(documentObj.getFileName());
		documentsDTO.setFileSize(documentObj.getFileSize());
		documentsDTO.setFileType(documentObj.getFileType());
		documentsDTO.setDescription(documentObj.getDocDesc());
		return documentsDTO;
	}
	
	public static AddressDTO convertToDTO(Address address){
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressLine1(address.getAddressLine1());
		addressDTO.setAddressLine2(address.getAddressLine2());
		addressDTO.setCity(address.getCity());
		addressDTO.setState(address.getState());
		addressDTO.setZip(address.getZipCode());
		return addressDTO;
	}
	
	public static EmploymentHistoryDTO convertToDTO(EmploymentObj employmentObj){
		
		EmploymentHistoryDTO employmentHistoryDTO = new EmploymentHistoryDTO();				
		
		employmentHistoryDTO.setWorkPermitEnd(employmentObj.getEmpAuthEnd());
		employmentHistoryDTO.setWorkPermitStart(employmentObj.getEmpAuthStart());
		employmentHistoryDTO.setWorkPermitStatus((employmentObj.getEmpAuthStatus()));
		employmentHistoryDTO.setEndDate(employmentObj.getEndDate());
		employmentHistoryDTO.setHourlyWagePay(employmentObj.getHourlyWagePay());
		employmentHistoryDTO.setHourlyWageRec(employmentObj.getHourlyWageRecv());

		
		employmentHistoryDTO.setJobTitle(employmentObj.getJobTitle());
		employmentHistoryDTO.setJobDesc(employmentObj.getJobDesc());
		employmentHistoryDTO.setTech(employmentObj.getTechnology());
		
		employmentHistoryDTO.setStartDate(employmentObj.getStartDate());
		employmentHistoryDTO.setTempReason(employmentObj.getTermntnReason());
		employmentHistoryDTO.setEmpStatus(employmentObj.getStatus());
		
		Vendor vendor = employmentObj.getVendor();
		Client client = employmentObj.getClient();
		
		if(client != null){
			ClientDTO clientDTO = new ClientDTO();				
			if(client.getClientAddress() != null){
				clientDTO.setAddressDTO(convertToDTO(client.getClientAddress()));
			}
			clientDTO.setClientContactEmail(client.getClientContactEmail());
			clientDTO.setClientContactName(client.getClientContactName());
			clientDTO.setClientContactPhone(client.getClientContactPhone());
			clientDTO.setClientHrEmail(client.getClientHrEmail());
			clientDTO.setClientHrName(client.getClientHrName());
			clientDTO.setClientHrName(client.getClientHrName());
			clientDTO.setClientName(client.getClientName());
			
			employmentHistoryDTO.setClientDTO(clientDTO);
		}
		
		if(vendor != null){
			VendorDTO vendorDTO = new VendorDTO();				
			if(vendor.getVendorAddress() != null){
				vendorDTO.setAddressDTO(convertToDTO(vendor.getVendorAddress()));
			}
			vendorDTO.setVendorContactEmail(vendor.getVendorContactEmail());
			vendorDTO.setVendorContactName(vendor.getVendorContactName());
			vendorDTO.setVendorContactPhone(vendor.getVendorContactPhone());
			vendorDTO.setVendorHrEmail(vendor.getVendorHrEmail());
			vendorDTO.setVendorHrName(vendor.getVendorHrName());
			vendorDTO.setVendorHrName(vendor.getVendorHrName());
			vendorDTO.setVendorName(vendor.getVendorName());
			employmentHistoryDTO.setVendorDTO(vendorDTO);
		}
		
		return employmentHistoryDTO;
	}
	
	public static PersonalInfoDTO convertToDTO(PersonalInfo personalInfo) {

		PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();
		personalInfoDTO.setFirstName(personalInfo.getFirstName());
		personalInfoDTO.setMiddleName(personalInfo.getMiddleName());
		personalInfoDTO.setLastName(personalInfo.getLastName());
		personalInfoDTO.setDob(personalInfo.getDob());
		personalInfoDTO.setEmail(personalInfo.getEmail());
		personalInfoDTO.setPhone(String.valueOf(personalInfo.getPhone()));
		personalInfoDTO.setVisaStatus(personalInfo.getVisaStatus());
		personalInfoDTO.setCountry(personalInfo.getCountry());

		personalInfoDTO.setSecondEmail(personalInfo.getSecondEmail());
		personalInfoDTO.setSecondPhone(personalInfo.getSecondPhone());

		if (StringUtils.isEmpty(personalInfo.getJoinedOn())) {
			personalInfo.setJoinedOn(DateUtils.getCurrentDate());
		}
		personalInfoDTO.setJoinedDate(personalInfo.getJoinedOn());
		personalInfoDTO.setHiredDate(personalInfo.getHiredOn());
		personalInfoDTO.setManagerName(personalInfo.getManager());
		personalInfoDTO.setRecruiterName(personalInfo.getRecruiter());
		personalInfoDTO.setSsn(personalInfo.getSsn());

		Work work = personalInfo.getWork();
		WorkDTO workDTO = new WorkDTO();
		workDTO.setAreaOfWork(work.getAreaOfWork());
		workDTO.setIsCurrEmployed(work.getIsCurrEmployed());
		workDTO.setLastEmployer(work.getLastEmployer());
		workDTO.setWorkExpInYrs(String.valueOf(work.getWorkExpInYear()));

		Education education = personalInfo.getEducation();
		EducationDTO educationDTO = new EducationDTO();
		educationDTO.setCollegeDegree(education.getCollegeDegree());
		educationDTO.setGradYear(String.valueOf(education.getGradYear()));
		educationDTO.setHighestDegree(education.getHighestDegree());
		educationDTO.setLastCollCountry(education.getLastColCountry());
		educationDTO.setLastCollName(education.getLastColName());

		Address address = personalInfo.getAddress();
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressLine1(address.getAddressLine1());
		addressDTO.setAddressLine2(address.getAddressLine2());
		addressDTO.setCity(address.getCity());
		addressDTO.setState(address.getState());
		addressDTO.setZip(String.valueOf(address.getZipCode()));
		Set<EmploymentHistoryDTO> employmentHistoryDTOSet = null;
		Set<DocumentsDTO> documentsDTOSet = null;

		if (personalInfo.getDocumnetObj() != null) {

			for (DocumentObj documentObj : personalInfo.getDocumnetObj()) {
				if (personalInfoDTO.getDocumentsDTO() == null) {
					documentsDTOSet = new HashSet<>();
					personalInfoDTO.setDocumentsDTO(documentsDTOSet);
				}
				DocumentsDTO documentsDTO = convertToDTO(documentObj);
				if (documentsDTO != null) {
					documentsDTO.setPersonalInfoDTO(personalInfoDTO);
					documentsDTOSet.add(documentsDTO);
				}
			}
		}
		
		if(documentsDTOSet != null){
			personalInfoDTO.setDocumentsDTO(documentsDTOSet);
		}

		if (personalInfo.getEmploymentObj() != null) {
			for (EmploymentObj employmentObj : personalInfo.getEmploymentObj()) {

				EmploymentHistoryDTO employmentHistoryDTO = new EmploymentHistoryDTO();
				EmploymentHistoryDTO employmentHistoryDTO2 = new EmploymentHistoryDTO();

				if (personalInfoDTO.getEmploymentHistoryDTO() == null) {
					employmentHistoryDTOSet = new HashSet<>();
					personalInfoDTO.setEmploymentHistoryDTO(employmentHistoryDTOSet);
				}

				employmentHistoryDTO.setPersonalInfoDTO(personalInfoDTO);
				employmentHistoryDTOSet.add(employmentHistoryDTO);

				employmentHistoryDTO.setWorkPermitEnd(employmentObj.getEmpAuthEnd());
				employmentHistoryDTO.setWorkPermitStart(employmentObj.getEmpAuthStart());
				employmentHistoryDTO.setWorkPermitStatus((employmentObj.getEmpAuthStatus()));
				employmentHistoryDTO.setEndDate(employmentObj.getEndDate());
				employmentHistoryDTO.setHourlyWagePay(employmentObj.getHourlyWagePay());
				employmentHistoryDTO.setHourlyWageRec(employmentObj.getHourlyWageRecv());

				employmentHistoryDTO.setJobTitle(employmentObj.getJobTitle());
				employmentHistoryDTO.setJobDesc(employmentObj.getJobDesc());
				employmentHistoryDTO.setTech(employmentObj.getTechnology());

				employmentHistoryDTO.setStartDate(employmentObj.getStartDate());
				employmentHistoryDTO.setTempReason(employmentObj.getTermntnReason());
				employmentHistoryDTO.setEmpStatus(employmentObj.getStatus());

				Vendor vendor = employmentObj.getVendor();
				Client client = employmentObj.getClient();

				if (client != null) {
					ClientDTO clientDTO = new ClientDTO();
					if (client.getClientAddress() != null) {
						clientDTO.setAddressDTO(convertToDTO(client.getClientAddress()));
					}
					clientDTO.setClientContactEmail(client.getClientContactEmail());
					clientDTO.setClientContactName(client.getClientContactName());
					clientDTO.setClientContactPhone(client.getClientContactPhone());
					clientDTO.setClientHrEmail(client.getClientHrEmail());
					clientDTO.setClientHrName(client.getClientHrName());
					clientDTO.setClientHrPhone(client.getClientHrPhone());
					clientDTO.setClientName(client.getClientName());

					employmentHistoryDTO.setClientDTO(clientDTO);
				}

				if (vendor != null) {
					VendorDTO vendorDTO = new VendorDTO();
					if (vendor.getVendorAddress() != null) {
						vendorDTO.setAddressDTO(convertToDTO(vendor.getVendorAddress()));
					}
					vendorDTO.setVendorContactEmail(vendor.getVendorContactEmail());
					vendorDTO.setVendorContactName(vendor.getVendorContactName());
					vendorDTO.setVendorContactPhone(vendor.getVendorContactPhone());
					vendorDTO.setVendorHrEmail(vendor.getVendorHrEmail());
					vendorDTO.setVendorHrName(vendor.getVendorHrName());
					vendorDTO.setVendorHrPhone(vendor.getVendorHrPhone());
					vendorDTO.setVendorName(vendor.getVendorName());
					employmentHistoryDTO.setVendorDTO(vendorDTO);
				}
			}

		}

		personalInfoDTO.setWorkDTO(workDTO);
		personalInfoDTO.setEducationDTO(educationDTO);
		personalInfoDTO.setAddressDTO(addressDTO);
		if (employmentHistoryDTOSet != null) {
			personalInfoDTO.setEmploymentHistoryDTO(employmentHistoryDTOSet);
		}

		return personalInfoDTO;

	}

}
