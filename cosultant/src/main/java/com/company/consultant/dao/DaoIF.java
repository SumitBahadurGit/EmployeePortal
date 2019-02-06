package com.company.consultant.dao;

import java.util.List;
import java.util.Optional;

import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.models.PersonalInfo;

public interface DaoIF {

	public PersonalInfoDTO save(PersonalInfoDTO personalInfoDTO);
	
	public List<PersonalInfoDTO> search (String query);

	public PersonalInfoDTO searchById(Long id);
	
	List<PersonalInfoDTO> getFullNameAndId();

	PersonalInfoDTO findById(Long id);

	PersonalInfoDTO getById(Long id);
	
	List<DocumentsDTO> searchDocumentsById(Long id);

	void deleteNullObjects(PersonalInfoDTO personalInfoDTO);
}
