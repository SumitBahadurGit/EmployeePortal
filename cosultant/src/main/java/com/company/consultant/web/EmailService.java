package com.company.consultant.web;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.consultant.models.Email;

@CrossOrigin(origins = "*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/email")
public class EmailService extends RestControllerImpl{

	@PostMapping("/send")
	public void sendEmail(@RequestBody Email emailObj) throws Exception{
		
		if(emailObj != null){
			String to = emailObj.getTo();
			String subject = emailObj.getSubject();
			String cc = emailObj.getCc();
			String body = emailObj.getMessage();
			
			mailService.sendEmail(to, subject, body);
			
		}
				
	}
}
