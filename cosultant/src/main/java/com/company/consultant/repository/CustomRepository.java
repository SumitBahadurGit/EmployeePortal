package com.company.consultant.repository;

public interface CustomRepository {

	void refresh(Object obj);
	void flush(Object obj);
	
}
