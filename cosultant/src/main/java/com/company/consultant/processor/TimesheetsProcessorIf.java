package com.company.consultant.processor;

import java.util.List;

import com.company.consultant.dto.TimesheetsDTO;
import com.company.consultant.exceptions.GcsException;
import com.company.consultant.models.TimesheetsObjWrapper;

public interface TimesheetsProcessorIf {

	TimesheetsDTO save(TimesheetsDTO dto);

	Object processAndSave(TimesheetsObjWrapper obj);

	List<TimesheetsObjWrapper> getAllTimesSheetsByEmpId(TimesheetsObjWrapper timesheetsObjWrapper) throws GcsException;

	boolean check(TimesheetsObjWrapper obj);

		

}
