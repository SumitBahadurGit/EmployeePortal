package com.company.consultant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.consultant.dao.DaoIF;

@Component
public class DaoUtils {

	@Autowired
	DaoIF dao;
	
	public  Long getNextSeq() throws Exception{
		Long id = dao.getNextSeq();
		if (id == null){
			throw new Exception("Error generating new id.");			
		} else {
			return id;
		}
		
	}
}
