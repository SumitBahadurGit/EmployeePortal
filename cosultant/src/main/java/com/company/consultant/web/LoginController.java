package com.company.consultant.web;

import javax.websocket.server.PathParam;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.consultant.models.Login;

@CrossOrigin(origins = "*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/login")
public class LoginController extends RestControllerImpl {
	
	@RequestMapping("/generateId")
	public Long getNewId(@RequestParam("email") String email, @RequestParam("name") String name) throws Exception {

	Login login = new Login();
	login = (Login) manager.manageSave(login);
	Long newId = Long.valueOf(login.getLoginId());
	if(!StringUtils.isEmpty(email)){
		String subj = createNewSubject(email, newId);
		sendEmail(email, subj);
	}
	return newId;	
	}
	
	
	@PostMapping("/check")
	public Login check(@RequestBody Login login){
		
		return  (Login) manager.check(login);
	}
}
