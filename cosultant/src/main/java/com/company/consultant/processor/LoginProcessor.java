package com.company.consultant.processor;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.company.consultant.dto.LogInDTO;
import com.company.consultant.models.Login;
import com.company.consultant.util.DtoConverter;

@Component
public class LoginProcessor extends BaseProcessor{

	@Override
	public Object processAndSave(Object obj) {
		Login login = (Login) obj;
		String userName;
		String pw;
		Long eid = dao.getNextSeq();
		LogInDTO logInDTO = new LogInDTO();
		DtoConverter.copyProperties(login, logInDTO);
				
		if(StringUtils.isEmpty(logInDTO.getUserName())){			
			logInDTO.setUserName(eid.toString());
//			logInDTO.setEid(eid);
		}	
		
		if(StringUtils.isEmpty(logInDTO.getPw())){			
			logInDTO.setPw(eid.toString());
		}
				
		logInDTO.setStatus("ACTIVE");		
		logInDTO = (LogInDTO) dao.save(logInDTO);
		Login resp = new Login();
		DtoConverter.copyProperties(logInDTO, resp);
		return resp;
	}

	public Login check(Login login) {
		LogInDTO logInDTO = new LogInDTO();
		DtoConverter.copyProperties(login, logInDTO);

		List<LogInDTO> list = (List<LogInDTO>) dao.findByEntity(logInDTO);
		if(list != null && list.size() == 1){
			DtoConverter.copyProperties(list.get(0), login);
			login.setPw(null);
			return login;
		} else {
			return null;
		}
		
		
	}

}
