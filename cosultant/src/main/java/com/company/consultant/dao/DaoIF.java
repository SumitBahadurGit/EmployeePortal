package com.company.consultant.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.dto.TimesheetsDTO;
import com.company.consultant.models.PersonalInfo;


public interface DaoIF {
	
	public List<PersonalInfoDTO> search (String query);

	public PersonalInfoDTO searchById(Long id);
	
	List<PersonalInfoDTO> getFullNameAndId();

	PersonalInfoDTO findById(Long id);

	PersonalInfoDTO getById(Long id);
	
	List<DocumentsDTO> searchDocumentsById(Long id);

	void deleteNullObjects(PersonalInfoDTO personalInfoDTO);

	Long getNextSeq();

	List<PersonalInfoDTO> findAllEmployees(String sortBy, String filterBy, String filterByValue, int start,
			int end) throws CloneNotSupportedException;

	Long findAllEmployeesCount(String filterBy, String filterByValue) throws CloneNotSupportedException;

	Object save(Object obj);

	Object findById(Object obj, Long id);

	<T> List<T> findAll(List<T> obj);

	<T> List<T> saveAll(List<T> obj);

	boolean checkIfExists(Long eid, Date date, String projectLocation);

	List<TimesheetsDTO> getAllTimesheets(Long eid, Date startDate, Date endDate, String projectLocation);

	void deleteById(Class<?> class1, Long id);

	List<DocumentsDTO> getAllDocs();
}
