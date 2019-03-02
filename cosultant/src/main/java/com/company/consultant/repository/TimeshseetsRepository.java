package com.company.consultant.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.consultant.dto.TimesheetsDTO;

@Repository
public interface TimeshseetsRepository extends CustomRepository, JpaRepository<TimesheetsDTO, Long>{

	@Query("SELECT T FROM TimesheetsDTO T WHERE T.employeeId = ?1 AND T.timeSheetDate >= ?2 AND T.timeSheetDate <= ?3 AND T.projectLocation = ?4")
	List<TimesheetsDTO> getAllTimesheets(Long eid, Date start, Date end, String projectLocation);
	
	@Query("SELECT T FROM TimesheetsDTO T WHERE T.employeeId = ?1 AND T.timeSheetDate >= ?2 AND T.projectLocation = ?3")
	List<TimesheetsDTO> getAllTimesheetsByStartDate(Long eid, Date start, String projectLocation);

	@Query("SELECT T FROM TimesheetsDTO T WHERE T.employeeId = ?1 AND  T.timeSheetDate <= ?2 AND T.projectLocation = ?3")
	List<TimesheetsDTO> getAllTimesheetsByEndDate(Long eid, Date end, String projectLocation);

	@Query("SELECT T FROM TimesheetsDTO T WHERE T.employeeId = ?1 AND T.projectLocation = ?2")
	List<TimesheetsDTO> getAllTimesheets(Long eid, String projectLocation);
	
	@Query("SELECT COUNT(T) FROM TimesheetsDTO T WHERE T.employeeId = ?1 AND  T.timeSheetDate = ?2 AND T.projectLocation = ?3")
	Long checkIfExists(Long eid, Date date, String projectLocation);

}
