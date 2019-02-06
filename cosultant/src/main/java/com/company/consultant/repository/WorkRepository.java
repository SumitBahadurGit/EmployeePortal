package com.company.consultant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.consultant.dto.WorkDTO;

@Repository
public interface WorkRepository extends JpaRepository<WorkDTO, Long> {

}
