package com.company.consultant.processor;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.company.consultant.exceptions.GcsException;
import com.company.consultant.models.DocumentObj;

public interface FileProcessorIF {
	
	public File download(String fileName);
	public boolean delete(String fileName);
	List<DocumentObj> processAndUpload(MultipartFile[] files) throws GcsException;
	DocumentObj processAndUpdate(DocumentObj documentObjs) throws GcsException;
	void processAndDelete(List<Long> ids);
	public List<DocumentObj> processAndUpdates(List<DocumentObj> documentObjs) throws GcsException;
}
