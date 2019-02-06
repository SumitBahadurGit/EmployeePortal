package com.company.consultant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.consultant.dto.PersonalInfoDTO;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfoDTO, Long> {

	@Query("SELECT u FROM PersonalInfoDTO u WHERE UPPER(u.firstName) LIKE UPPER(CONCAT('%',:name,'%'))")
	List<PersonalInfoDTO> findByQuery(@Param("name") String name);


	@Query("SELECT new PersonalInfoDTO(u.employeeId, u.firstName, u.lastName) FROM PersonalInfoDTO u where u.employeeId is not null")
	List<PersonalInfoDTO> findByNameAndId();

	
	
}
