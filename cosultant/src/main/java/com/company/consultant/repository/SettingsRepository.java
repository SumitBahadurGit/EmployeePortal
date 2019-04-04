package com.company.consultant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.consultant.dto.SettingsDTO;

@Repository
public interface SettingsRepository extends CustomRepository, JpaRepository<SettingsDTO, Long>{

	
}
