package com.company.consultant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.consultant.dto.LogInDTO;

@Repository
public interface LoginRepository extends CustomRepository, JpaRepository<LogInDTO, Long>{


}
