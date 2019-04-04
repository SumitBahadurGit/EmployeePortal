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

import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.exceptions.ErrorCodes;
import com.company.consultant.exceptions.GcsException;
import com.company.consultant.models.DocumentObj;
import com.company.consultant.util.DtoConverter;

import javassist.expr.NewArray;

@Component
public class FileProcessor extends BaseProcessor implements FileProcessorIF {

	final private String base = System.getProperty("user.dir");
	final private String uploadPath = base + "static\\files\\";
	
	@Override
	public List<DocumentObj> processAndUpload(MultipartFile[] files) throws GcsException{
		return upload(files);   
	}
	
	@Override
	public void processAndDelete(List<Long> ids){
		if(ids != null && ids.size() > 0){
			ids.forEach(id -> {
				dao.deleteById(DocumentsDTO.class, id);
			});
		}
	}
	
	@Override
	public List<DocumentObj> processAndUpdates (List<DocumentObj> list) throws GcsException {
		List<DocumentObj> returnValues = new ArrayList<>();
		
		if(list != null){
			for(DocumentObj documentObj : list){
				returnValues.add(processAndUpdate(documentObj));
			}

		}

		return returnValues;	
	}
	
	
	@Override
	public DocumentObj processAndUpdate(DocumentObj documentObj) throws GcsException{
		DocumentObj returnValue = null;
		if(documentObj != null){
			DocumentsDTO documentDTO = (DocumentsDTO) dao.findById(new DocumentsDTO(), documentObj.getDocId());
			DtoConverter.copyProperties(documentObj, documentDTO);
			
			documentDTO = (DocumentsDTO) dao.save(documentDTO);

			if(documentDTO != null){
				DocumentObj _temp = new DocumentObj();
				DtoConverter.copyProperties(documentDTO, _temp);	
				if(documentObj != null){
					returnValue = _temp;
				}
			}
		}
		if(returnValue == null){
			throw new GcsException("Error updating documents.", ErrorCodes.DOCS_UPDATE_ERROR);
		}
		return returnValue;	
	}
	
	private List<DocumentObj> upload(MultipartFile[] files) throws GcsException {

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
			        File destination = new File(path.toAbsolutePath().toString());
			        if(!destination.exists()){
			        	destination.mkdirs();
			        }
			        
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e) {
					throw new GcsException("Error uploading files: " , ErrorCodes.UPLOAD_ERROR);
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


	@Override
	public Object processAndSave(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
