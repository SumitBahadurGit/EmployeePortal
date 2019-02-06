package com.company.consultant.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.dto.EmploymentHistoryDTO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.repository.AddressRepository;
import com.company.consultant.repository.DocumentsRepo;
import com.company.consultant.repository.EducationRepository;
import com.company.consultant.repository.EmploymentRepository;
import com.company.consultant.repository.PersonalInfoRepository;
import com.company.consultant.repository.WorkRepository;

@Service
public class DAO implements DaoIF {

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
		if(personalInfoDTO != null){
			return personalInfoDTO.get();
		} else {
			return null;
		}

	}	
	
	@Override
	@Transactional
	public PersonalInfoDTO save(PersonalInfoDTO personalInfoDTO) {
		return personalInfoRepository.save(personalInfoDTO);
	}

	@Override
	public PersonalInfoDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentsDTO> searchDocumentsById(Long id) {
		return documentsRepo.findDocsByEmpId(id);		
	}

	public void deleteById(Class<?> class1, Long id) {
		
		if(class1.equals(EmploymentHistoryDTO.class)){
			employmentRepository.deleteById(id);			
		}
		
	}

}
