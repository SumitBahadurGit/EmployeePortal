package com.company.consultant.processor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.company.consultant.models.DocumentObj;

@Component
public class FileProcessor extends BaseProcessor implements FileProcessorIF {

	final private String uploadPath = "C:\\Users\\sumit_2qmnpye\\Downloads\\cosultant\\cosultant\\src\\main\\resources\\static\\files\\";
	
	@Override
	public List<DocumentObj> processAndUpload(MultipartFile[] files) throws Exception{
		return upload(files);   
	}
	
	
	private List<DocumentObj> upload(MultipartFile[] files) throws Exception {

		List<DocumentObj> documentObjs = new ArrayList<>();

		if(files != null){
			for(MultipartFile file : files){
		        try {
			        
		        	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		        	String fileSize = "n/a";
		        	if(file.getSize() != 0){
			        	fileSize = String.valueOf(Math.round((file.getSize()/1024) * 100)/100) + "Kb";
			        }
		        	String[] temp = fileName.split("\\."); 
		        	String fileType = "n/a";
		        	if(temp != null && temp.length > 0){
				        fileType = temp[temp.length - 1];			        			        		
		        	}
			        
			        DocumentObj documentObj = new DocumentObj();
			        documentObj.setFileName(fileName);
			        documentObj.setFileSize(fileSize);
			        documentObj.setFileType(fileType);
			        documentObjs.add(documentObj);
			        
			        System.out.println("Processing file: " + fileName);
			        Path path = Paths.get(getUploadPath()+fileName);
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e) {
					throw new Exception("Error uploading files: " + e.getMessage());
				}

			}
		}

		return documentObjs != null && documentObjs.size() > 0 ? documentObjs : null;
	}

	@Override
	public File download(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUploadPath() {
		return uploadPath;
	}
	
	

}
