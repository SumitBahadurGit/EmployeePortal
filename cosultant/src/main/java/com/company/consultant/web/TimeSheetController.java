package com.company.consultant.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.consultant.models.TimesheetsObjWrapper;

@CrossOrigin(origins = "*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/timesheets")
public class TimeSheetController extends RestControllerImpl{

	@PostMapping("/save")
	@ResponseBody
	public TimesheetsObjWrapper getTimeSheets(@RequestBody TimesheetsObjWrapper objWrapper){
		
		return (TimesheetsObjWrapper) manager.manageSave(objWrapper);		
	}
	
	@RequestMapping("/{employeeId}/getAll")
	public List<TimesheetsObjWrapper> getTimeSheets(@PathVariable("employeeId") String employeeId,
			@RequestBody TimesheetsObjWrapper objWrapper) throws Exception{
		 List<TimesheetsObjWrapper> response = (List<TimesheetsObjWrapper>) manager.manageSearch(objWrapper);
		return response;
	}
	
	@RequestMapping("/{employeeId}/check")
	public boolean check(@PathVariable("employeeId") String employeeId,
			@RequestBody TimesheetsObjWrapper objWrapper) throws Exception{
		
		 return (boolean) manager.check(objWrapper);

	}
	
}
