package com.company.consultant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.company.consultant.dto.EmploymentHistoryDTO;

@Component
public interface EmploymentRepository extends JpaRepository<EmploymentHistoryDTO, Long> {

}
