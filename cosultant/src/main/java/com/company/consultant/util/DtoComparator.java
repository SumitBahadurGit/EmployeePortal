package com.company.consultant.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

public class DtoComparator {

	public static void compareAddressDTO(AddressDTO addressDTO, Address address) {

		if (!StringUtils.isEmpty(address.getAddressLine1())) {
			addressDTO.setAddressLine1(address.getAddressLine1());
		}
		if (!StringUtils.isEmpty(address.getAddressLine2())) {
			addressDTO.setAddressLine2(address.getAddressLine2());
		}
		if (!StringUtils.isEmpty(address.getCity())) {
			addressDTO.setCity(address.getCity());
		}
		if (!StringUtils.isEmpty(address.getState())) {
			addressDTO.setState(address.getState());
		}
		if (!StringUtils.isEmpty(address.getZipCode())) {
			addressDTO.setZip(address.getZipCode());
		}
	}

	public static void compareDocumentDTO(DocumentsDTO documentsDTO, DocumentObj documentObj) {

		if(!StringUtils.isEmpty(documentObj.getFileName())){
			documentsDTO.setFileName(documentObj.getFileName());
		}
		if(!StringUtils.isEmpty(documentObj.getFileSize())){
			documentsDTO.setFileSize(documentObj.getFileSize());
		}
		if(!StringUtils.isEmpty(documentObj.getFileType())){
			documentsDTO.setFileType(documentObj.getFileType());
		}
		if(!StringUtils.isEmpty(documentObj.getDocDesc())){
			documentsDTO.setDescription(documentObj.getDocDesc());
		}
	}
	
	public static PersonalInfoDTO compareDTO(PersonalInfoDTO _personalInfoDTO, PersonalInfo personalInfo) {

		PersonalInfoDTO personalInfoDTO = _personalInfoDTO;
		
		if (!StringUtils.isEmpty(personalInfo.getFirstName())) {
			personalInfoDTO.setFirstName(personalInfo.getFirstName());
		}
		if (!StringUtils.isEmpty(personalInfo.getMiddleName())) {
			personalInfoDTO.setMiddleName(personalInfo.getMiddleName());
		}
		if (!StringUtils.isEmpty(personalInfo.getLastName())) {
			personalInfoDTO.setLastName(personalInfo.getLastName());
		}
		if (!StringUtils.isEmpty(personalInfo.getDob())) {
			personalInfoDTO.setDob(personalInfo.getDob());
		}
		if (!StringUtils.isEmpty(personalInfo.getEmail())) {
			personalInfoDTO.setEmail(personalInfo.getEmail());
		}
		if (!StringUtils.isEmpty(personalInfo.getPhone())) {
			personalInfoDTO.setPhone(String.valueOf(personalInfo.getPhone()));
		}
		if (!StringUtils.isEmpty(personalInfo.getVisaStatus())) {
			personalInfoDTO.setVisaStatus(personalInfo.getVisaStatus());
		}
		if (!StringUtils.isEmpty(personalInfo.getCountry())) {
			personalInfoDTO.setCountry(personalInfo.getCountry());
		}

		if (!StringUtils.isEmpty(personalInfo.getSecondEmail())) {
			personalInfoDTO.setSecondEmail(personalInfo.getSecondEmail());
		}
		if (!StringUtils.isEmpty(personalInfo.getSecondPhone())) {
			personalInfoDTO.setSecondPhone(personalInfo.getSecondPhone());
		}
		if (!StringUtils.isEmpty(personalInfo.getHiredOn())) {
			personalInfoDTO.setHiredDate(personalInfo.getHiredOn());
		}
		if (!StringUtils.isEmpty(personalInfo.getManager())) {
			personalInfoDTO.setManagerName(personalInfo.getManager());
		}
		if (!StringUtils.isEmpty(personalInfo.getRecruiter())) {
			personalInfoDTO.setRecruiterName(personalInfo.getRecruiter());
		}
		if (!StringUtils.isEmpty(personalInfo.getSsn())) {
			personalInfoDTO.setSsn(personalInfo.getSsn());
		}

		WorkDTO workDTO = personalInfoDTO.getWorkDTO();

		if (personalInfo.getWork() != null) {
			if (workDTO == null) {
				workDTO = new WorkDTO();
				personalInfoDTO.setWorkDTO(workDTO);
			}
			if (!StringUtils.isEmpty(personalInfo.getWork().getAreaOfWork())) {
				workDTO.setAreaOfWork(personalInfo.getWork().getAreaOfWork());
			}
			if (!StringUtils.isEmpty(personalInfo.getWork().getIsCurrEmployed())) {
				workDTO.setIsCurrEmployed(personalInfo.getWork().getIsCurrEmployed());
			}
			if (!StringUtils.isEmpty(personalInfo.getWork().getLastEmployer())) {
				workDTO.setLastEmployer(personalInfo.getWork().getLastEmployer());
			}
			if (!StringUtils.isEmpty(personalInfo.getWork().getWorkExpInYear())) {
				workDTO.setWorkExpInYrs(String.valueOf(personalInfo.getWork().getWorkExpInYear()));
			}

		}

		EducationDTO educationDTO = personalInfoDTO.getEducationDTO();

		if (personalInfo.getEducation() != null) {
			if (educationDTO == null) {
				educationDTO = new EducationDTO();
				personalInfoDTO.setEducationDTO(educationDTO);
			}
			Education education = personalInfo.getEducation();

			if (!StringUtils.isEmpty(education.getCollegeDegree())) {
				educationDTO.setCollegeDegree(education.getCollegeDegree());
			}
			if (!StringUtils.isEmpty(education.getGradYear())) {
				educationDTO.setGradYear(String.valueOf(education.getGradYear()));
			}
			if (!StringUtils.isEmpty(education.getHighestDegree())) {
				educationDTO.setHighestDegree(education.getHighestDegree());
			}
			if (!StringUtils.isEmpty(education.getLastColCountry())) {
				educationDTO.setLastCollCountry(education.getLastColCountry());
			}
			if (!StringUtils.isEmpty(education.getLastColName())) {
				educationDTO.setLastCollName(education.getLastColName());
			}
		}


		if (personalInfo.getAddress() != null) {
			
			AddressDTO newAddressDTO = personalInfoDTO.getAddressDTO();

			if (newAddressDTO == null) {
				newAddressDTO = new AddressDTO();
				personalInfoDTO.setAddressDTO(newAddressDTO);
			}

			Address address = personalInfo.getAddress();
			
			if (!StringUtils.isEmpty(address.getAddressLine1())) {
				newAddressDTO.setAddressLine1(address.getAddressLine1());
			}
			if (!StringUtils.isEmpty(address.getAddressLine2())) {
				newAddressDTO.setAddressLine2(address.getAddressLine2());
			}
			if (!StringUtils.isEmpty(address.getCity())) {
				newAddressDTO.setCity(address.getCity());
			}
			if (!StringUtils.isEmpty(address.getState())) {
				newAddressDTO.setState(address.getState());
			}
			if (!StringUtils.isEmpty(address.getZipCode())) {
				newAddressDTO.setZip(String.valueOf(address.getZipCode()));
			}
		}
		
		if(personalInfo.getDocumnetObj() != null){
			
			Set<DocumentsDTO> documentsDTOSet = personalInfoDTO.getDocumentsDTO();
			
			if(documentsDTOSet == null){
				documentsDTOSet = new HashSet<>();
				personalInfoDTO.setDocumentsDTO(documentsDTOSet);
			}
			
			for(DocumentObj documentObj : personalInfo.getDocumnetObj()){
				
				DocumentsDTO documentDTO = null;
				Iterator<DocumentsDTO> iter = documentsDTOSet.iterator();
				
				while(iter.hasNext()){
					DocumentsDTO temp = iter.next();
					if(temp.getDocId() != null
							&& String.valueOf(temp.getDocId()).equals(documentObj.getDocId())){
						documentDTO = temp;
						break;
					}
				}
				if(documentDTO == null){
					documentDTO = new DocumentsDTO();
				}
				
				compareDocumentDTO(documentDTO, documentObj);
				
				if(documentDTO != null){
					documentDTO.setPersonalInfoDTO(personalInfoDTO);
					documentsDTOSet.add(documentDTO);
				}				
			}
		}
		
		if (personalInfo.getEmploymentObj() != null) {
			
			Set<EmploymentHistoryDTO> employmentHistoryDTOSet = personalInfoDTO.getEmploymentHistoryDTO();


			if (employmentHistoryDTOSet == null) {
				employmentHistoryDTOSet = new HashSet<>();
				personalInfoDTO.setEmploymentHistoryDTO(employmentHistoryDTOSet);
			}
			
			for (EmploymentObj employmentObj : personalInfo.getEmploymentObj()) {

				EmploymentHistoryDTO employmentHistoryDTO = null;
				Iterator<EmploymentHistoryDTO> iter = employmentHistoryDTOSet.iterator();

				while (iter.hasNext()) {
					EmploymentHistoryDTO temp = iter.next();
					if (temp.getEmpId() != null
							&& String.valueOf(temp.getEmpId()).equals(employmentObj.getEmploymentId())) {
						employmentHistoryDTO = temp;
						break;
					}
				}

				if (employmentHistoryDTO == null) {
					employmentHistoryDTO = new EmploymentHistoryDTO();
					employmentHistoryDTO.setPersonalInfoDTO(personalInfoDTO);
					employmentHistoryDTOSet.add(employmentHistoryDTO);
				}

				if (!StringUtils.isEmpty(employmentObj.getEmpAuthEnd())) {
					employmentHistoryDTO.setWorkPermitEnd(employmentObj.getEmpAuthEnd());
				}
				if (!StringUtils.isEmpty(employmentObj.getEmpAuthStart())) {
					employmentHistoryDTO.setWorkPermitStart(employmentObj.getEmpAuthStart());
				}
				if (!StringUtils.isEmpty(employmentObj.getEmpAuthStatus())) {
					employmentHistoryDTO.setWorkPermitStatus((employmentObj.getEmpAuthStatus()));
				}
				if (!StringUtils.isEmpty(employmentObj.getEndDate())) {
					employmentHistoryDTO.setEndDate(employmentObj.getEndDate());
				}
				if (!StringUtils.isEmpty(employmentObj.getHourlyWagePay())) {
					employmentHistoryDTO.setHourlyWagePay(employmentObj.getHourlyWagePay());
				}
				if (!StringUtils.isEmpty(employmentObj.getHourlyWageRecv())) {
					employmentHistoryDTO.setHourlyWageRec(employmentObj.getHourlyWageRecv());
				}

				if (!StringUtils.isEmpty(employmentObj.getJobTitle())) {
					employmentHistoryDTO.setJobTitle(employmentObj.getJobTitle());
				}
				if (!StringUtils.isEmpty(employmentObj.getJobDesc())) {
					employmentHistoryDTO.setJobDesc(employmentObj.getJobDesc());
				}
				if (!StringUtils.isEmpty(employmentObj.getTechnology())) {
					employmentHistoryDTO.setTech(employmentObj.getTechnology());
				}

				if (!StringUtils.isEmpty(employmentObj.getStartDate())) {
					employmentHistoryDTO.setStartDate(employmentObj.getStartDate());
				}
				if (!StringUtils.isEmpty(employmentObj.getTermntnReason())) {
					employmentHistoryDTO.setTempReason(employmentObj.getTermntnReason());
				}
				if (!StringUtils.isEmpty(employmentObj.getStatus())) {
					employmentHistoryDTO.setEmpStatus(employmentObj.getStatus());
				}

				Vendor vendor = employmentObj.getVendor();
				Client client = employmentObj.getClient();
				ClientDTO clientDTO = employmentHistoryDTO.getClientDTO();
				AddressDTO clientAddressDTO = null;
				VendorDTO vendorDTO = employmentHistoryDTO.getVendorDTO();
				AddressDTO vendorAddressDTO = null;
				
				if (client != null) {


					if (clientDTO == null) {
						clientDTO = new ClientDTO();
						employmentHistoryDTO.setClientDTO(clientDTO);
					}

					if (client.getClientAddress() != null) {

						if (clientDTO.getAddressDTO() != null) {
							clientAddressDTO = clientDTO.getAddressDTO();

						} else {
							clientAddressDTO = new AddressDTO();
							clientDTO.setAddressDTO(clientAddressDTO);
						}
						compareAddressDTO(clientAddressDTO, client.getClientAddress());
					}

					if (!StringUtils.isEmpty(client.getClientContactEmail())) {
						clientDTO.setClientContactEmail(client.getClientContactEmail());
					}
					if (!StringUtils.isEmpty(client.getClientContactName())) {
						clientDTO.setClientContactName(client.getClientContactName());
					}
					if (!StringUtils.isEmpty(client.getClientContactPhone())) {
						clientDTO.setClientContactPhone(client.getClientContactPhone());
					}
					if (!StringUtils.isEmpty(client.getClientHrEmail())) {
						clientDTO.setClientHrEmail(client.getClientHrEmail());
					}
					if (!StringUtils.isEmpty(client.getClientHrName())) {
						clientDTO.setClientHrName(client.getClientHrName());
					}
					if (!StringUtils.isEmpty(client.getClientHrName())) {
						clientDTO.setClientHrName(client.getClientHrName());
					}
					if (!StringUtils.isEmpty(client.getClientName())) {
						clientDTO.setClientName(client.getClientName());
					}
				}

				if (vendor != null) {



					if (vendorDTO == null) {
						vendorDTO = new VendorDTO();
						employmentHistoryDTO.setVendorDTO(vendorDTO);
					}

					if (vendor.getVendorAddress() != null) {

						if (vendorDTO.getAddressDTO() != null) {
							vendorAddressDTO = vendorDTO.getAddressDTO();

						} else {
							vendorAddressDTO = new AddressDTO();
							vendorDTO.setAddressDTO(vendorAddressDTO);
						}

						compareAddressDTO(vendorAddressDTO, vendor.getVendorAddress());
					}

					if (!StringUtils.isEmpty(vendor.getVendorContactEmail())) {
						vendorDTO.setVendorContactEmail(vendor.getVendorContactEmail());
					}
					if (!StringUtils.isEmpty(vendor.getVendorContactName())) {
						vendorDTO.setVendorContactName(vendor.getVendorContactName());
					}
					if (!StringUtils.isEmpty(vendor.getVendorContactPhone())) {
						vendorDTO.setVendorContactPhone(vendor.getVendorContactPhone());
					}
					if (!StringUtils.isEmpty(vendor.getVendorHrEmail())) {
						vendorDTO.setVendorHrEmail(vendor.getVendorHrEmail());
					}
					if (!StringUtils.isEmpty(vendor.getVendorHrName())) {
						vendorDTO.setVendorHrName(vendor.getVendorHrName());
					}
					if (!StringUtils.isEmpty(vendor.getVendorHrName())) {
						vendorDTO.setVendorHrName(vendor.getVendorHrName());
					}
					if (!StringUtils.isEmpty(vendor.getVendorName())) {
						vendorDTO.setVendorName(vendor.getVendorName());
					}
				}

			}
		}
		return personalInfoDTO;

	}

}
