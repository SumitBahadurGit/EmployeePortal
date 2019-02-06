package com.company.cosultant;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.company.consultant.dto.AddressDTO;
import com.company.consultant.dto.ClientDTO;
import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.dto.EducationDTO;
import com.company.consultant.dto.EmploymentHistoryDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.dto.VendorDTO;
import com.company.consultant.dto.WorkDTO;
import com.company.consultant.repository.AddressRepository;
import com.company.consultant.repository.EducationRepository;
import com.company.consultant.repository.PersonalInfoRepository;
import com.company.consultant.repository.WorkRepository;

@ComponentScan("com.*")
@EntityScan("com*")
@EnableJpaRepositories("com.*")
@EnableAutoConfiguration
@SpringBootApplication
public class CosultantApplication implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	DataSource dataSource;

	@Autowired
	PersonalInfoRepository personalInfoRepository;

	@Autowired
	WorkRepository workRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	EducationRepository educationRepository;

	public static void main(String[] args) {
		SpringApplication.run(CosultantApplication.class, args);
	}

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		PersonalInfoDTO personalInfo = new PersonalInfoDTO();
		personalInfo.setFirstName("Kabir");
		personalInfo.setMiddleName("Kumar");
		personalInfo.setLastName("Shrestha");
		personalInfo.setDob("12/25/1990");
		personalInfo.setCountry("Nepal");
		personalInfo.setVisaStatus("H1B");
		personalInfo.setEmail("sumitbahadur25@gmail.com");
		personalInfo.setPhone("8328360153");
		personalInfo.setSecondPhone("234566789");
		personalInfo.setSecondEmail("bahaxxx2@gmail.com");	
		personalInfo.setSsn("282327789");
		personalInfo.setJoinedDate("01/01/2017");
		personalInfo.setHiredDate("01/01/2018");
		personalInfo.setManagerName("Azeem Maridiya");
		personalInfo.setRecruiterName("Anil Singh");
		
		WorkDTO workDTO = new WorkDTO();
		workDTO.setAreaOfWork("IT");
		workDTO.setWorkExpInYrs("3");
		workDTO.setIsCurrEmployed("N");
		workDTO.setLastEmployer("Capital One");

		EducationDTO educationDTO = new EducationDTO();
		educationDTO.setCollegeDegree("Computer Science");
		educationDTO.setGradYear("2015");
		educationDTO.setHighestDegree("Bachelor's");
		educationDTO.setLastCollCountry("USA");
		educationDTO.setLastCollName("UHD");

		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressLine1("2714 Standord Dr");
		addressDTO.setAddressLine2("");
		addressDTO.setCity("Irving");
		addressDTO.setState("TX");
		addressDTO.setZip("75062");

		AddressDTO clientAddressDTO = new AddressDTO();
		clientAddressDTO.setAddressLine1("9817 East Peakview Ave");
		clientAddressDTO.setAddressLine2("");
		clientAddressDTO.setCity("Broomfield");
		clientAddressDTO.setState("CO");
		clientAddressDTO.setZip("80111");
		
		AddressDTO vendorAddressDTO = new AddressDTO();
		vendorAddressDTO.setAddressLine1("2718 Standord Dr");
		vendorAddressDTO.setAddressLine2("");
		vendorAddressDTO.setCity("Fort Worth");
		vendorAddressDTO.setState("TX");
		vendorAddressDTO.setZip("74015");
		
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setAddressDTO(vendorAddressDTO);
		vendorDTO.setVendorContactEmail("vendor@gmail.com");
		vendorDTO.setVendorContactName("Basu");
		vendorDTO.setVendorContactPhone("1234567890");
		vendorDTO.setVendorHrEmail("vendorHr@gmail.com");
		vendorDTO.setVendorHrName("Malika Sah");
		vendorDTO.setVendorHrPhone("234567897");
		vendorDTO.setVendorName("Wipro Tech");

		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setAddressDTO(clientAddressDTO);
		clientDTO.setClientContactEmail("vendor@gmail.com");
		clientDTO.setClientContactName("Basu");
		clientDTO.setClientContactPhone("1234567890");
		clientDTO.setClientHrEmail("vendorHr@gmail.com");
		clientDTO.setClientHrName("Malika Sah");
		clientDTO.setClientHrPhone("234567897");
		clientDTO.setClientName("Wipro Tech");

		EmploymentHistoryDTO employmentHistoryDTO = new EmploymentHistoryDTO();
		employmentHistoryDTO.setClientDTO(clientDTO);
		employmentHistoryDTO.setVendorDTO(vendorDTO);
		employmentHistoryDTO.setEmpStatus("Active");
		employmentHistoryDTO.setEndDate("01/29/2019");
		employmentHistoryDTO.setHourlyWagePay("$35.0");
		employmentHistoryDTO.setHourlyWageRec("$55.0");
		employmentHistoryDTO.setStartDate("01/01/2019");
		employmentHistoryDTO.setWorkPermitStatus("H1B");
		employmentHistoryDTO.setWorkPermitStart("01/01/2018");
		employmentHistoryDTO.setWorkPermitEnd("01/01/2021");
		employmentHistoryDTO.setTempReason("Not yet teminated");
		employmentHistoryDTO.setTech("JAVA");
		employmentHistoryDTO.setJobDesc("Convert SOAP to REST.");
		employmentHistoryDTO.setJobTitle("Software Engineer");
		employmentHistoryDTO.setPersonalInfoDTO(personalInfo);

					
		Set<EmploymentHistoryDTO> employeeSet = new HashSet<>();
		
		employeeSet.add(employmentHistoryDTO);
		
		DocumentsDTO documentsDTO = new DocumentsDTO();
		documentsDTO.setFileName("Resume.txt");
		documentsDTO.setFileSize("1.01 MB");
		documentsDTO.setFileType("docx");
		documentsDTO.setPersonalInfoDTO(personalInfo);

		DocumentsDTO documentsDTO2 = new DocumentsDTO();
		documentsDTO2.setFileName("SSN.png");
		documentsDTO2.setFileSize("3.01 MB");
		documentsDTO2.setFileType("png");
		documentsDTO2.setPersonalInfoDTO(personalInfo);
				
		Set<DocumentsDTO> documentsDTOs = new HashSet<>();
		documentsDTOs.add(documentsDTO);
		documentsDTOs.add(documentsDTO2);
		personalInfo.setDocumentsDTO(documentsDTOs);
		
		personalInfo.setEmploymentHistoryDTO(employeeSet);


		personalInfo.setWorkDTO(workDTO);
		personalInfo.setEducationDTO(educationDTO);
		personalInfo.setAddressDTO(addressDTO);
	//	personalInfo = personalInfoRepository.save(personalInfo);

		System.out.println("ID: " + personalInfo.getEmployeeId());

		System.out.println("ID: " + personalInfo.getEmployeeId());

	}

}
