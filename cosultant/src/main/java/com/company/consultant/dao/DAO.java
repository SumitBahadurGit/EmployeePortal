package com.company.consultant.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.dto.EmploymentHistoryDTO;
import com.company.consultant.dto.LogInDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.dto.SettingsDTO;
import com.company.consultant.dto.TimesheetsDTO;
import com.company.consultant.repository.AddressRepository;
import com.company.consultant.repository.DocumentsRepo;
import com.company.consultant.repository.EducationRepository;
import com.company.consultant.repository.EmploymentRepository;
import com.company.consultant.repository.LoginRepository;
import com.company.consultant.repository.PersonalInfoRepository;
import com.company.consultant.repository.SettingsRepository;
import com.company.consultant.repository.TimeshseetsRepository;
import com.company.consultant.repository.WorkRepository;


@Component
public class DAO implements DaoIF {

	@Autowired
	EntityManager em;
	
	@Autowired
	PersonalInfoRepository personalInfoRepository;

	@Autowired
	WorkRepository workRepository;

	@Autowired
	EducationRepository educationRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	DocumentsRepo documentsRepo;
	
	@Autowired
	EmploymentRepository employmentRepository;
	
	@Autowired
	TimeshseetsRepository timeshseetsRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	SettingsRepository settingsRepository;
	
	@Override
	public List<PersonalInfoDTO> getFullNameAndId() {
		return personalInfoRepository.findByNameAndId();
	}
	
	@Override
	public List<PersonalInfoDTO> search (String query) {
		List<PersonalInfoDTO> personalInfoDTO = personalInfoRepository.findByQuery(query);
		return personalInfoDTO;
		
	}

	@Override
	public PersonalInfoDTO getById(Long id) {
	
		return personalInfoRepository.getOne(id);
			
	}	
	
	@Override
	public void deleteNullObjects(PersonalInfoDTO personalInfoDTO){
		if(personalInfoDTO.getEmploymentHistoryDTO() == null
				|| personalInfoDTO.getEmploymentHistoryDTO().size() == 0){

		}
	}

	@Override
	public PersonalInfoDTO searchById (Long id) {
	
		Optional<PersonalInfoDTO> personalInfoDTO = personalInfoRepository.findById(id);
		if(personalInfoDTO.isPresent()){
			return personalInfoDTO.get();
		} else {
			return null;
		}
	}	
		
	@Override
	public PersonalInfoDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentsDTO> getAllDocs(){
		return documentsRepo.findAll();
		
	}
	
	@Override
	public List<DocumentsDTO> searchDocumentsById(Long id) {
		return documentsRepo.findDocsByEmpId(id);		
	}

	@Override
	public void deleteById(Class<?> class1, Long id) {
		
		if(class1.equals(EmploymentHistoryDTO.class)){
			employmentRepository.deleteById(id);			
		} else 	if(class1.equals(DocumentsDTO.class)){
			documentsRepo.deleteById(id);
		}	
		
	}
	
	@Override
	public Long getNextSeq(){
		Query query = em.createNativeQuery("select EMPLOYEE_SEQ.nextVal from dual");
		BigDecimal bd =  (BigDecimal) query.getSingleResult();
		return bd.longValue();
	}
	
	@Override
	public List<PersonalInfoDTO> findAllEmployees(String sortBy,String filterBy, String filterByValue, int start, int end) throws CloneNotSupportedException {
		Pageable pageable = null;
		if(!StringUtils.isEmpty(sortBy)){
		 pageable = PageRequest.of(start, end);			
		} else {
		 pageable = PageRequest.of(start, end, Sort.by(sortBy));			
		}
		
		List<EmploymentHistoryDTO> empList = employmentRepository.findAllActive(filterByValue, pageable);
		List<PersonalInfoDTO> result =  null;
		 
		 for(EmploymentHistoryDTO emp : empList){
			 PersonalInfoDTO personalInfoDTO = (PersonalInfoDTO) emp.personalInfoDTO.clone();
			 if(personalInfoDTO != null){
				 if(result == null){
					 result = new ArrayList<>();
				 }
				 Set<EmploymentHistoryDTO> set = new HashSet<>();
				 set.add(emp);
				 personalInfoDTO.setEmploymentHistoryDTO(set);
				 result.add(personalInfoDTO);
			 }
		 }
		 
		 return result;
	}

	@Override
	public Long findAllEmployeesCount(String filterBy, String filterByValue){
		return employmentRepository.findAllActiveCount(filterByValue);

	}

	@Override
	public Object findById(Object obj, Long id ){
		
		if(obj instanceof TimesheetsDTO){
			Optional<TimesheetsDTO> timesheetsDTOs = null;
			timesheetsDTOs = timeshseetsRepository.findById(id);
			if(timesheetsDTOs != null){
				return timesheetsDTOs.get();
			}
		} else if (obj instanceof PersonalInfoDTO){
			Optional<PersonalInfoDTO> personalInfoDTO = null;
			personalInfoDTO = personalInfoRepository.findById(id);
			if(personalInfoDTO != null){
				return personalInfoDTO.get();
			}
		} else if (obj instanceof DocumentsDTO){
			Optional<DocumentsDTO> documentsDTO = null;
			documentsDTO = documentsRepo.findById(id);
			if(documentsDTO != null){
				return documentsDTO.get();
			}
		}else if (obj instanceof LogInDTO){
			Optional<LogInDTO> loginDTO = null;
			loginDTO = loginRepository.findById(id);
			if(loginDTO != null){
				return loginDTO.get();
			}
		}
		
		return null;
	}
	

	@Override
	public<T> List<T> findAll(List<T> obj){
		if(obj.get(0) instanceof TimesheetsDTO){
			List<TimesheetsDTO> dtos = (List<TimesheetsDTO>) obj;
			List<Long> ids = dtos.stream().map(t -> t.getTimeSheetId()).collect(Collectors.toList());
			return (List<T>) timeshseetsRepository.findAllById(ids);
		}
		return null;
	}
	
	@Override
	public <T> List<T> saveAll(List<T> obj){
		if(obj.get(0) instanceof TimesheetsDTO){
			List<TimesheetsDTO> timesheetsDTO = (List<TimesheetsDTO>) obj;
			timesheetsDTO = timeshseetsRepository.saveAll(timesheetsDTO);
			timesheetsDTO.forEach(d -> timeshseetsRepository.refresh(d));
			return (List<T>) timesheetsDTO;
		} else if(obj.get(0) instanceof PersonalInfoDTO){
			List<PersonalInfoDTO> personalInfoDTO =  (List<PersonalInfoDTO>) obj;
			personalInfoDTO = personalInfoRepository.saveAll(personalInfoDTO);
			return (List<T>) personalInfoDTO;
		}
		return null;
	}

	@Override
	public Object save(Object obj ){
		
		if(obj instanceof TimesheetsDTO){
			TimesheetsDTO timesheetsDTO = (TimesheetsDTO) obj;
			timesheetsDTO = timeshseetsRepository.save(timesheetsDTO);
			return timesheetsDTO;
		} else if(obj instanceof PersonalInfoDTO){
			PersonalInfoDTO personalInfoDTO = (PersonalInfoDTO) obj;
			personalInfoDTO = personalInfoRepository.save(personalInfoDTO);
			return personalInfoDTO;
		} else if(obj instanceof LogInDTO){
			LogInDTO logInDTO = (LogInDTO) obj;
			logInDTO = loginRepository.save(logInDTO);
			loginRepository.refresh(logInDTO);
			return logInDTO;
		} else if(obj instanceof DocumentsDTO){
			DocumentsDTO documentsDTO = (DocumentsDTO) obj;
			documentsDTO = documentsRepo.save(documentsDTO);
			loginRepository.refresh(documentsDTO);
			return documentsDTO;
		} else if(obj instanceof SettingsDTO){
			SettingsDTO settingsDTO = (SettingsDTO) obj;
			settingsDTO = settingsRepository.save(settingsDTO);
			settingsRepository.refresh(settingsDTO);
			return settingsDTO;
			
		}
		
		return null;
	}

	@Override
	public boolean checkIfExists(Long eid, Date date, String projectLocation){
		Long count = timeshseetsRepository.checkIfExists(eid, date, projectLocation);
		return count.intValue() > 0 ? true : false;
	}
	
	@Override
	public List<TimesheetsDTO> getAllTimesheets(Long eid, Date startDate, Date endDate, String projectLocation) {
		if(startDate == null && endDate != null){
			return timeshseetsRepository.getAllTimesheetsByEndDate(eid,endDate,projectLocation);
		
		} else if(endDate == null && startDate != null){
			return timeshseetsRepository.getAllTimesheetsByStartDate(eid, startDate,projectLocation);
			
		} else if(startDate != null && endDate != null){
			return timeshseetsRepository.getAllTimesheets(eid, startDate, endDate,projectLocation);						
		} else if(!StringUtils.isEmpty(projectLocation)){
			return timeshseetsRepository.getAllTimesheets(eid,projectLocation);			
		} else {
			return timeshseetsRepository.getAllTimesheets(eid);			
		}
	}

	public List<?> findByEntity (Object entity) {

		if(entity instanceof LogInDTO){	
			ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
			LogInDTO logInDTO = (LogInDTO) entity;
			Example criteria = Example.of(logInDTO, matcher);
			return loginRepository.findAll(criteria);
		} else if(entity instanceof DocumentsDTO){
			ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
			DocumentsDTO documentsDTO = (DocumentsDTO) entity;
			Example criteria = Example.of(documentsDTO, matcher);
			return documentsRepo.findAll(criteria);
		} else if (entity instanceof SettingsDTO){
			ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
			SettingsDTO settingsDTO = (SettingsDTO) entity;
			Example criteria = Example.of(settingsDTO, matcher);
			return settingsRepository.findAll(criteria);
		} else if (entity instanceof PersonalInfoDTO){
			ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
			PersonalInfoDTO personalInfoDTO = (PersonalInfoDTO) entity;
			Example criteria = Example.of(personalInfoDTO, matcher);
			return personalInfoRepository.findAll(criteria);
		} else if(entity instanceof TimesheetsDTO){
			ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
			TimesheetsDTO timesheetsDTO = (TimesheetsDTO) entity;
			Example criteria = Example.of(timesheetsDTO, matcher);
			return timeshseetsRepository.findAll(criteria);
		}
		return null;
		
	}
	
}
