package com.company.consultant.processor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.dto.TimesheetsDTO;
import com.company.consultant.exceptions.ErrorCodes;
import com.company.consultant.exceptions.GcsException;
import com.company.consultant.models.Timesheets;
import com.company.consultant.models.TimesheetsObjWrapper;
import com.company.consultant.util.DateUtils;
import com.company.consultant.util.DtoComparator;
import com.company.consultant.util.DtoConverter;

import javafx.util.Pair;

@Component
public class TimesheetsProcessor extends BaseProcessor implements TimesheetsProcessorIf {
	
	@Override
	public List<TimesheetsObjWrapper> getAllTimesSheetsByEmpId(TimesheetsObjWrapper t) throws GcsException{
		Long eid = null;
		Long groupId = null;
		
		if(t.getEmployeeId() != null){
			eid = Long.valueOf(t.getEmployeeId());			
		}

		if(t.getTimesheetsGroupId() != null){
			groupId = Long.valueOf(t.getTimesheetsGroupId());			
		}

		if(eid == null ){
			throw new GcsException("Employee Id is required to fetch timesheets", ErrorCodes.MISSING_ATTRIBUTE);
		}
		Date startDate = !StringUtils.isEmpty(t.getStartDate()) ? DateUtils.getDate(t.getStartDate()) : null;
		Date endDate = !StringUtils.isEmpty(t.getEndDate()) ? DateUtils.getDate(t.getEndDate()) : null;
		boolean includeDetails = "Y".equalsIgnoreCase(t.getIncludeTimesheets());
		String projectLocation = t.getProjectLocation();
	
		
		TimesheetsDTO timesheetsDTO = new TimesheetsDTO();
		if(eid != null){
			timesheetsDTO.setEmployeeId(eid);
		}
		if(groupId != null){
			timesheetsDTO.setTimesheetGroupId(groupId);			
		}
		if(!StringUtils.isEmpty(projectLocation)){
			timesheetsDTO.setProjectLocation(projectLocation);
		}
		
		List<TimesheetsDTO> timesheetsDTOs = (List<TimesheetsDTO>) dao.findByEntity(timesheetsDTO);
//		List<TimesheetsDTO> timesheetsDTOs = dao.getAllTimesheets(eid, startDate, endDate, projectLocation);
		Collections.sort(timesheetsDTOs);
		List<Timesheets> timesheets =  DtoConverter.convertFromDTO(timesheetsDTOs);

		List<TimesheetsObjWrapper> objWrappers = new ArrayList<>();
		
		timesheets = timesheets.stream()
				.filter(ts ->  !StringUtils.isEmpty(ts.getSubmittedDate()) && !StringUtils.isEmpty(ts.getProjectLocation()))
				.collect(Collectors.toList());
		
		Map<String, List<Timesheets>> map = timesheets
				.stream()
				.collect(
						Collectors.groupingBy(Timesheets::getTimesheetGroupId));
	

			 map.keySet()
			.forEach( key -> {
				TimesheetsObjWrapper obj = new TimesheetsObjWrapper();
				if(includeDetails){
					obj.getTimesheets().addAll(map.get(key));					
				}
				obj.setStartDate((map.get(key)).get(0).getTimesheetDate().toString());
				obj.setEndDate((map.get(key)).get((map.get(key)).size() - 1).getTimesheetDate().toString());
				obj.setSubmittedDate(key);
				obj.setEmployeeId(t.getEmployeeId());
				obj.setTimesheetsGroupId(Long.valueOf(map.get(key).get(0).getTimesheetGroupId()));
				double totalHours = obj.getTimesheets().stream()
						.filter(f -> f.getTotalHours() != 0)
						.mapToDouble(f -> f.getTotalHours()).sum();
				double totalOvertime = obj.getTimesheets().stream()
						.filter(f -> f.getOverTime() != 0)
						.mapToDouble(f -> f.getOverTime()).sum();
				obj.setTotalHours(totalHours);
				obj.setOverTime(totalOvertime);
				objWrappers.add(obj);
			});

		
		return objWrappers;
	}
	
	@Override
	public TimesheetsDTO save(TimesheetsDTO dto){
		return (TimesheetsDTO) dao.save(dto);
	}

	@Override
	public Object processAndSave(TimesheetsObjWrapper obj) {
		TimesheetsObjWrapper resp = new TimesheetsObjWrapper();
		List<TimesheetsDTO> dtos = DtoConverter.convertToDTO(obj.getTimesheets());
		
		if(dtos != null && dtos.size() > 0){
			if(StringUtils.isEmpty(dtos.get(0).getTimesheetGroupId())){
				// This is a non-primary id used to group timesheets		
				Long id = dao.getNextSeq();
				dtos.forEach(d -> d.setTimesheetGroupId(id));
				
			}
			dtos = dao.saveAll(dtos);		
			Collections.sort(dtos);
			resp.getTimesheets().addAll(DtoConverter.convertFromDTO(dtos));		
			resp.setStartDate(resp.getTimesheets().get(0).getTimesheetDate().toString());
			resp.setEndDate(resp.getTimesheets().get(resp.getTimesheets().size() - 1).getTimesheetDate().toString());
			resp.setTimesheetsGroupId(Long.valueOf(resp.getTimesheets().get(0).getTimesheetGroupId()));
		}
		
		return resp;

	}

	@Override
	public boolean check(TimesheetsObjWrapper obj) {
		Long eid = Long.valueOf(obj.getEmployeeId());
		Date startDate = !StringUtils.isEmpty(obj.getStartDate()) ? DateUtils.getDate(obj.getStartDate()) : null;
		String projectLocation = obj.getProjectLocation();
		return dao.checkIfExists(eid, startDate, projectLocation);
	}

	@Override
	public Object processAndSave(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
