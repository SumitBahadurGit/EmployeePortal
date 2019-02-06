package com.company.consultant.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.consultant.dao.DAO;

@Component
public abstract class BaseProcessor implements BaseProcesorIF{

	@Autowired
	DAO dao;
	
	@Autowired
	FileProcessorIF fileProcessor;

}
