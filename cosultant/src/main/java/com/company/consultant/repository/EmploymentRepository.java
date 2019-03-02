package com.company.consultant.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.company.consultant.dto.EmploymentHistoryDTO;

@Component
public interface EmploymentRepository extends JpaRepository<EmploymentHistoryDTO, Long> {

	@Query("SELECT count(E) FROM EmploymentHistoryDTO E WHERE UPPER(E.empStatus) = :filterByValue")
	public Long findAllActiveCount(@Param("filterByValue")String filterByValue);
	
	@Query("SELECT E FROM EmploymentHistoryDTO E WHERE UPPER(E.empStatus) = :filterByValue")
	public List<EmploymentHistoryDTO> findAllActive(@Param("filterByValue")String filterByValue, Pageable pageable);
}
