package com.company.consultant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.consultant.dto.DocumentsDTO;

@Repository
public interface DocumentsRepo extends JpaRepository<DocumentsDTO, Long> {


	@Query("SELECT u FROM DocumentsDTO u WHERE u.personalInfoDTO.employeeId = ?1")
	public List<DocumentsDTO> findDocsByEmpId(Long id);
	
}
