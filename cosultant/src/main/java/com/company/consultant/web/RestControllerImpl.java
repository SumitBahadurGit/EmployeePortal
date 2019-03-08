package com.company.consultant.web;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.consultant.mail.MailService;
import com.company.consultant.manager.Manager;
import com.company.consultant.processor.EmployeeProcessorIF;
import com.company.consultant.util.DaoUtils;

@Component
public class RestControllerImpl {

	@Autowired
	EmployeeProcessorIF employeeProcessorIF;

	@Autowired
	Manager manager;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	DaoUtils daoUtils;
	
	public Long generateNewId() throws Exception{
		return daoUtils.getNextSeq();
	}
	
	public void sendEmail(String email , String body) throws Exception{
		
		mailService.sendEmail(email, body);
	}
	
	public String createNewSubject(String name, Long id){
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("Green Consulting Solutions");
		sb.append("\n");
		sb.append("\n");
		sb.append("Dear " + name);
		sb.append(",");
		sb.append("\n");
		sb.append("\n");
		sb.append("Please use the following details for loggin into the GCS portal");
		sb.append("\n");
		sb.append("ID: " + id);
		sb.append("\n");
		sb.append("\n");
		sb.append("Password: " + id);
		sb.append("\n");
		sb.append("\n");
		sb.append("http://onlineemp.com/");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("** This is an auto-generated email. Please do not reply. **");
		sb.append("\n");
		return sb.toString();

	}
}
