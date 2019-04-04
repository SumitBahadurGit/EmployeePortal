package com.company.cosultant;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Random;
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

import com.company.consultant.dao.DAO;
import com.company.consultant.dao.DaoIF;
import com.company.consultant.dto.AddressDTO;
import com.company.consultant.dto.ClientDTO;
import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.dto.EducationDTO;
import com.company.consultant.dto.EmploymentHistoryDTO;
import com.company.consultant.dto.LogInDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.dto.TimesheetsDTO;
import com.company.consultant.dto.VendorDTO;
import com.company.consultant.dto.WorkDTO;
import com.company.consultant.repository.AddressRepository;
import com.company.consultant.repository.EducationRepository;
import com.company.consultant.repository.LoginRepository;
import com.company.consultant.repository.PersonalInfoRepository;
import com.company.consultant.repository.TimeshseetsRepository;
import com.company.consultant.repository.WorkRepository;
import com.company.consultant.util.DateUtils;

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
	
	@Autowired
	TimeshseetsRepository timeshseetsRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	DAO dao;

	public static void main(String[] args) {
		SpringApplication.run(CosultantApplication.class, args);
	}

	public int getRandom(int limit){
		return (int) (((Math.random() * 100)) % limit + 1);

	}
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		String capitals [][] = {
		        {"Alabama", "Montgomery"},
		        {"Alaska", "Juneau"},
		        {"Arizona", "Phoenix"},
		        {"Arkansas", "Little Rock"},
		        {"California", "Sacramento"},
		        {"Colorado", "Denver"},
		        {"Connecticut", "Hartford"},
		        {"Delaware", "Dover"},
		        {"Florida", "Tallahasse"},
		        {"Georgia", "Atlanta"},
		        {"Hawaii", "Honolulu"},
		        {"Idaho", "Boise"},
		        {"Illinois", "Springfield"},
		        {"Indiana", "Indianapolis"},
		        {"Iowa", "Des Moines"},
		        {"Kansas", "Topeka"},
		        {"Kentucky", "Frankfort"},
		        {"Louisiana", "Baton Rouge"},
		        {"Maine", "Augusta"},
		        {"Maryland", "Annapolis"},
		        {"Massachusettes", "Boston"},
		        {"Michigan", "Lansing"},
		        {"Minnesota", "Saint Paul"},
		        {"Mississippi", "Jackson"},
		        {"Missouri", "Jefferson City"},
		        {"Montana", "Helena"},
		        {"Nebraska", "Lincoln"},
		        {"Nevada", "Carson City"},
		        {"New Hampshire", "Concord"},
		        {"New Jersey", "Trenton"},
		        {"New York", "Albany"},
		        {"New Mexico", "Santa Fe"},
		        {"North Carolina", "Raleigh"},
		        {"North Dakota", "Bismark"},
		        {"Ohio", "Columbus"},
		        {"Oklahoma", "Oklahoma City"},
		        {"Oregon", "Salem"},
		        {"Pennslyvania", "Harrisburg"},
		        {"Rhode Island", "Providence"},
		        {"South Carolina", "Columbia"},
		        {"South Dakota", "Pierre"},
		        {"Tennessee", "Nashville"},
		        {"Texas", "Austin"},
		        {"Utah", "Salt Lake City"},
		        {"Vermont", "Montpelier"},
		        {"Virginia", "Richmond"},
		        {"Washington", "Olympia"},
		        {"West Virginia", "Charleston"},
		        {"Wisconsin", "Madison"},
		        {"Wyoming", "Cheyenne"}
		       };

		for(int index = 0; index < 0
				; index++){
			PersonalInfoDTO personalInfo = new PersonalInfoDTO();
			String A = String.valueOf((char) (getRandom(26) + 65));
			String B = String.valueOf((char) (getRandom(26) + 65));
			String C = String.valueOf((char) (getRandom(26) + 65));
			String D = String.valueOf((char) (getRandom(26) + 65));
			String E = String.valueOf((char) (getRandom(26) + 65));
			String F = String.valueOf((char) (getRandom(26) + 65));
			String G = String.valueOf((char) (getRandom(26) + 65));
			String H = String.valueOf((char) (getRandom(26) + 65));
			String I = String.valueOf((char) (getRandom(26) + 65));
			String J = String.valueOf((char) (getRandom(26) + 65));
			String K = String.valueOf((char) (getRandom(26) + 65));
			String L = String.valueOf((char) (getRandom(26) + 65));
			String M = String.valueOf((char) (getRandom(26) + 65));
			String N = String.valueOf((char) (getRandom(26) + 65));
			String O = String.valueOf((char) (getRandom(26) + 65));
			String P = String.valueOf((char) (getRandom(26) + 65));
			String Q = String.valueOf((char) (getRandom(26) + 65));
			String R = String.valueOf((char) (getRandom(26) + 65));
			String S = String.valueOf((char) (getRandom(26) + 65));
			String T = String.valueOf((char) (getRandom(26) + 65));
			String U = String.valueOf((char) (getRandom(26) + 65));
			String V = String.valueOf((char) (getRandom(26) + 65));
			String W = String.valueOf((char) (getRandom(26) + 65));
			String X = String.valueOf((char) (getRandom(26) + 65));
			String Y = String.valueOf((char) (getRandom(26) + 65));
			String Z = String.valueOf((char) (getRandom(26) + 65));

			String a = String.valueOf((char) (getRandom(26) + 97));
			String b = String.valueOf((char) (getRandom(26) + 97));
			String c = String.valueOf((char) (getRandom(26) + 97));
			String d = String.valueOf((char) (getRandom(26) + 97));
			String e = String.valueOf((char) (getRandom(26) + 97));
			String f = String.valueOf((char) (getRandom(26) + 97));
			String g = String.valueOf((char) (getRandom(26) + 97));
			String h = String.valueOf((char) (getRandom(26) + 97));
			String j = String.valueOf((char) (getRandom(26) + 97));
			String k = String.valueOf((char) (getRandom(26) + 97));
			String l = String.valueOf((char) (getRandom(26) + 97));
			String m = String.valueOf((char) (getRandom(26) + 97));
			String n = String.valueOf((char) (getRandom(26) + 97));
			String o = String.valueOf((char) (getRandom(26) + 97));
			String p = String.valueOf((char) (getRandom(26) + 97));
			String q = String.valueOf((char) (getRandom(26) + 97));
			String i = String.valueOf((char) (getRandom(26) + 97));
			String r = String.valueOf((char) (getRandom(26) + 97));
			String s = String.valueOf((char) (getRandom(26) + 97));
			String t = String.valueOf((char) (getRandom(26) + 97));
			String u = String.valueOf((char) (getRandom(26) + 97));
			String v = String.valueOf((char) (getRandom(26) + 97));
			String w = String.valueOf((char) (getRandom(26) + 97));
			String x = String.valueOf((char) (getRandom(26) + 97));
			String y = String.valueOf((char) (getRandom(26) + 97));
			String z = String.valueOf((char) (getRandom(26) + 97));

			
			personalInfo.setFirstName(RandomNames.getRandomFirstName());
			personalInfo.setMiddleName(RandomNames.getRandomMiddleName());
			personalInfo.setLastName(RandomNames.getRandomLastName());
			personalInfo.setDob(String.valueOf(getRandom(12)) + "/" + String.valueOf(getRandom(30)) + "/" + String.valueOf(getRandom(15) + 1980));
			personalInfo.setCountry("Nepal");

			
			personalInfo.setEmail(E+m+a+i+l+"25@gmail.com");
			personalInfo.setPhone("83283601"+ String.valueOf((getRandom(10))));
			personalInfo.setSecondPhone("23456678"+ String.valueOf((10)));
			personalInfo.setSecondEmail(S+e+c+o+n+d+"@gmail.com");	
			personalInfo.setSsn(String.valueOf((getRandom(10))) +"23277"+ String.valueOf((10)));
			personalInfo.setJoinedDate(String.valueOf(getRandom(12)) + "/" + String.valueOf(getRandom(30)) + "/" + String.valueOf((getRandom(15)%3) + 2016));
			personalInfo.setHiredDate(String.valueOf(getRandom(12)) + "/" + String.valueOf(getRandom(30)) + "/" + String.valueOf((getRandom(15)%3) + 2016));
			personalInfo.setManagerName(RandomNames.getRandomManagerName());
			personalInfo.setRecruiterName(RandomNames.getRandomRecruiterName());
			
			WorkDTO workDTO = new WorkDTO();
			workDTO.setAreaOfWork("IT");
			workDTO.setWorkExpInYrs(String.valueOf(getRandom(6)));
			workDTO.setIsCurrEmployed((getRandom(50) % 2 == 0) ? "Y" : "N" );
			workDTO.setLastEmployer(C+a+p+i+t+a+l+" "+O+n+e);

			EducationDTO educationDTO = new EducationDTO();
			educationDTO.setCollegeDegree("Computer Science");
			educationDTO.setGradYear(String.valueOf(getRandom(8) + 2010));
			educationDTO.setHighestDegree("Bachelor's");
			educationDTO.setLastCollCountry("USA");
			educationDTO.setLastCollName(C+o+l+e+g+e + " " + N+a+m+e + " " + "University");

			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setAddressLine1(String.valueOf(getRandom(200) + 2714) + " " + H+t+r+q+f+f+u + " " + l+n);
			addressDTO.setAddressLine2("");
			int temp = getRandom(49);
			addressDTO.setCity(capitals[temp][1]);
			addressDTO.setState(capitals[temp][0].toString());
			addressDTO.setZip(String.valueOf(getRandom(9)) + String.valueOf(getRandom(9)) + String.valueOf(getRandom(9)) + String.valueOf(getRandom(9)) + String.valueOf(getRandom(9)));

			Long id = dao.getNextSeq();
			
			LogInDTO logInDTO = new LogInDTO();
			logInDTO.setEid(id);
			logInDTO.setUserName(String.valueOf(id));
			logInDTO.setPw(String.valueOf(id));
			logInDTO.setStatus("ACTIVE");
			if(index < 10){
				logInDTO.setUserRole("ADMIN");				
			} else if (index < 20 ){
				logInDTO.setUserRole("MANAGER");
			} else if (index < 30){
				logInDTO.setUserRole("RECRUITER");
			} else {
				logInDTO.setUserRole("EMPLOYEE");
			}

			loginRepository.save(logInDTO);
			
			if(logInDTO.getUserRole() == "EMPLOYEE"){
				VendorDTO vendorDTO = RandomNames.getVendor(getRandom(18));
				ClientDTO clientDTO = RandomNames.getClient(getRandom(18));
				
				EmploymentHistoryDTO employmentHistoryDTO = new EmploymentHistoryDTO();
				employmentHistoryDTO.setClientDTO(clientDTO);
				employmentHistoryDTO.setVendorDTO(vendorDTO);
				employmentHistoryDTO.setEndDate(String.valueOf(getRandom(12)) + "/" + String.valueOf(getRandom(30)) + "/" + String.valueOf(getRandom(5) + 2016));
				employmentHistoryDTO.setHourlyWagePay(String.valueOf(getRandom(20)+35));
				employmentHistoryDTO.setHourlyWageRec(String.valueOf(getRandom(45)+35));
				employmentHistoryDTO.setStartDate(String.valueOf(getRandom(12)) + "/" + String.valueOf(getRandom(30)) + "/" + String.valueOf(getRandom(2) + 2016));			
				employmentHistoryDTO.setWorkPermitStart(String.valueOf(getRandom(12)) + "/" + String.valueOf(getRandom(30)) + "/" + String.valueOf(getRandom(5) + 2015));
				employmentHistoryDTO.setWorkPermitEnd(String.valueOf(getRandom(12)) + "/" + String.valueOf(getRandom(30)) + "/" + String.valueOf(getRandom(5) + 2019));
				employmentHistoryDTO.setTempReason("Not yet teminated");
				employmentHistoryDTO.setJobDesc("Convert SOAP to REST.");
				employmentHistoryDTO.setJobTitle("Software Engineer");


								
				if(index % 2 == 0){
					employmentHistoryDTO.setEmpStatus("Active");
					employmentHistoryDTO.setTech("JAVA");
					employmentHistoryDTO.setWorkPermitStatus("H1B");


				} else if (index % 3 == 0){
					employmentHistoryDTO.setEmpStatus("Pending");
					employmentHistoryDTO.setTech("Angular");
					employmentHistoryDTO.setWorkPermitStatus("GC");

				}else if(index % 5 == 0){
					employmentHistoryDTO.setEmpStatus("Marketting");
					employmentHistoryDTO.setTech("Java,FrontEnd");
					employmentHistoryDTO.setWorkPermitStatus("OPT");


				} else {
					employmentHistoryDTO.setEmpStatus("Terminated");
					employmentHistoryDTO.setTech("Dev Ops");
					employmentHistoryDTO.setWorkPermitStatus("TPS");

				}
				
				employmentHistoryDTO.setPersonalInfoDTO(personalInfo);
				Set<EmploymentHistoryDTO> employeeSet = new HashSet<>();			
				employeeSet.add(employmentHistoryDTO);
				
		/*		DocumentsDTO documentsDTO = new DocumentsDTO();
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
				
				personalInfo.setEmploymentHistoryDTO(employeeSet);*/

				personalInfo.setEmploymentHistoryDTO(employeeSet);
				
	/*			System.out.println(personalInfo);
				System.out.println(addressDTO);
				System.out.println(employmentHistoryDTO);
				System.out.println(clientAddressDTO);
				System.out.println(vendorAddressDTO);
				System.out.println(clientDTO);
				System.err.println(vendorDTO);*/
			

			}
						
			if(index % 2 == 0){
				personalInfo.setVisaStatus("H1B");


			} else if (index % 3 == 0){
				personalInfo.setVisaStatus("GC");

			}else if(index % 5 == 0){
				personalInfo.setVisaStatus("OPT");


			} else {
				personalInfo.setVisaStatus("TPS");

			}
			//loginRepository.flush();
			
			personalInfo.setWorkDTO(workDTO);
			personalInfo.setEducationDTO(educationDTO);
			personalInfo.setAddressDTO(addressDTO);
			personalInfo.setEmployeeId(id);			
			personalInfo = personalInfoRepository.save(personalInfo);			

			System.out.println("ID - " + id);
			
			
			TimesheetsDTO timesheetsDTO = new TimesheetsDTO();
			timesheetsDTO.setApprovedDate(new Date(System.currentTimeMillis()));
			timesheetsDTO.setDesc("NONE");
			timesheetsDTO.setTimeSheetDate(new Date(System.currentTimeMillis()));
			timesheetsDTO.setEndTime("5");
			timesheetsDTO.setStartTime("9");
			timesheetsDTO.setIsApproved("N");
			timesheetsDTO.setOverTime(Double.valueOf(5));
			timesheetsDTO.setProjectDetails("None");
			timesheetsDTO.setProjectLocation("Captital One");
			timesheetsDTO.setSubmittedDate(new Date(System.currentTimeMillis()));
			timesheetsDTO.setTotalHours(Double.valueOf(45));
			timesheetsDTO.setStatus("SUBMITTED");
			timesheetsDTO.setEmployeeId(personalInfo.getEmployeeId());
		//	timeshseetsRepository.save(timesheetsDTO);

			System.out.println("P-ID: " + personalInfo.getEmployeeId());

		//	System.out.println("T-ID: " + timesheetsDTO.getTimeSheetId());

		}

	}

}
