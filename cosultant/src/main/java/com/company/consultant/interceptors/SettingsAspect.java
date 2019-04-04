package com.company.consultant.interceptors;

import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.consultant.dao.DAO;
import com.company.consultant.dto.PersonalInfoDTO;
import com.company.consultant.mail.MailService;
import com.company.consultant.models.Email;
import com.company.consultant.models.Settings;
import com.company.consultant.models.TimesheetsObjWrapper;
import com.company.consultant.processor.SettingProcessor;
import com.company.consultant.processor.TimesheetsProcessor;
import com.company.consultant.util.DateUtils;

@Aspect
@Component
public class SettingsAspect {


	@Autowired
	DAO dao;
	
	@Autowired
	TimesheetsProcessor timesheetsProcessor;
	
	@Autowired
	SettingProcessor settingProcessor;
	
	@Autowired
	MailService mailService;
	
	@Pointcut("execution(* com.company.consultant.web.TimeSheetController.getTimeSheets(..))")
	public void methods(){}
		
	@AfterReturning(value = "methods()", returning = "obj")
	public void applySettings(com.company.consultant.models.TimesheetsObjWrapper obj) throws Throwable {

		TimesheetsObjWrapper timesheetsObjWrapper = (TimesheetsObjWrapper) obj;
		String status = timesheetsObjWrapper.getTimesheets().get(0).getStatus();
		Long employeeId = Long.valueOf(timesheetsObjWrapper.getTimesheets().get(0).getEmployeeId());
		PersonalInfoDTO personalInfoDTO = dao.searchById(employeeId);
		if (personalInfoDTO != null) {
			final String managerName = personalInfoDTO.getManagerName().trim();
			PersonalInfoDTO manager = new PersonalInfoDTO();
			Long managerId = null;
			String[] managerNameSplit = managerName.split(" ");
			if (managerNameSplit != null) {
				if (managerNameSplit.length == 2) {
					manager.setFirstName(managerName.split(" ")[0]);
					manager.setLastName(managerName.split(" ")[1]);

				} else if (managerNameSplit.length == 3) {
					manager.setFirstName(managerName.split(" ")[0]);
					manager.setMiddleName(managerName.split(" ")[1]);
					manager.setLastName(managerName.split(" ")[2]);
				}
			}
			if (manager.getFirstName() != null) {
				List<PersonalInfoDTO> list = (List<PersonalInfoDTO>) dao.findByEntity(manager);
				if (list != null) {
					managerId = list.get(0).getEmployeeId();
				}
			}
			if (managerId != null) {
				Settings settings = new Settings();
				settings.setEmployeeId(managerId);
				settings = settingProcessor.search(settings);
				if (settings != null) {
					if (status != null && status.equals("Submitted")) {
						if (settings.getAutoApprove().equals("true")) {
							timesheetsObjWrapper.getTimesheets().forEach(ts -> {
								ts.setApprovedBy(managerName);
								ts.setIsApproved("Y");
								ts.setApprovedDate(DateUtils.getDateString());
								ts.setStatus("Approved");
							});
							timesheetsProcessor.processAndSave(timesheetsObjWrapper);
						}

					}
					if (settings.getEmailOnAction() != null) {
						Email email = new Email();
						email.setTo(settings.getEmailOnAction());
						email.setSubject("TIMESHEETS");
						String message = "The status of timesheets from "
								+ timesheetsObjWrapper.getStartDate().toString() + " to "
								+ timesheetsObjWrapper.getEndDate() + " is "
								+ timesheetsObjWrapper.getTimesheets().get(0).getStatus();
						email.setMessage(message);

						mailService.sendEmail(settings.getEmailOnAction(), "Timesheets Update", message);
					}
				}

			}

		}

		System.out.println("*********************************");
	}
	
}
