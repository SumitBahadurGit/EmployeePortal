package com.company.consultant.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomRepositoryImpl implements CustomRepository{

	@Autowired 
	EntityManager em;
	
	@Override
	@Transactional
	public void refresh(Object obj) {
		em.refresh(obj);
	}

	@Override
	@Transactional
	public void flush(Object obj) {
		em.flush();
	}

}
