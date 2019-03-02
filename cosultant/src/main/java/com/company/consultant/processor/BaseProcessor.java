package com.company.consultant.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.consultant.dao.DAO;
import com.company.consultant.models.Login;

@Component
public abstract class BaseProcessor implements BaseProcesorIF{

	@Autowired
	DAO dao;
	
	@Autowired
	FileProcessorIF fileProcessor;

	public abstract Object processAndSave (Object obj);
}
