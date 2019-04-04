package com.company.consultant.web;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	public Login getNewId(@RequestParam("email") String email,
			@RequestParam("name") String name,
			@RequestParam(value = "userRole", required=false) String userRole) throws Exception {

	Login login = new Login();
	if(StringUtils.isEmpty(userRole)){
		login.setUserRole("EMPLOYEE");
	} else {
		login.setUserRole(userRole);
	}
	
	login = (Login) manager.manageSave(login);
	Long newId = Long.valueOf(login.getUserName());
	if(!StringUtils.isEmpty(email)){
		String subj = createNewSubject(email, newId);
		sendEmail(email, subj);
	}
	login.setPw(null);
	return login;	
	}
	
	@PostMapping("/check")
	public Login check(@RequestBody Login login){
		
		return  (Login) manager.check(login);
	}
}
