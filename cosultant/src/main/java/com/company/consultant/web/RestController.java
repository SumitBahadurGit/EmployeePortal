package com.company.consultant.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.consultant.dto.DocumentsDTO;
import com.company.consultant.models.DocumentObj;
import com.company.consultant.models.EmploymentObj;
import com.company.consultant.models.PaginatedWrapper;
import com.company.consultant.models.PersonalInfo;
import com.company.consultant.models.SearchRequest;
import com.company.consultant.models.SearchType;


@CrossOrigin(origins = "*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/employee")
public class RestController extends RestControllerImpl {

	@PostMapping("/add")
	@ResponseBody
	public PersonalInfo addEmployee(@RequestBody PersonalInfo personalInfo) throws Exception {

		personalInfo = (PersonalInfo) manager.manageSave(personalInfo);
		System.out.println("Saved " + personalInfo);
		return getEmployeeById(personalInfo.getEmployeeId());

	}

	@PostMapping("/update")
	@ResponseBody
	public PersonalInfo updateEmployee(@RequestBody PersonalInfo personalInfo) throws Exception {
		System.out.println("Updating " + personalInfo);

		return (PersonalInfo) manager.manageUpdate(personalInfo);

	}

	@PostMapping("/employment/add")
	@ResponseBody
	public PersonalInfo addEmployment(@RequestBody EmploymentObj employmentObj) throws Exception {
		manager.manageSave(employmentObj);
		System.out.println("Saved " + employmentObj);
		return getEmployeeById(employmentObj.getEmployeeId());
	}

	@RequestMapping("/{employeeId}/retreive")
	@ResponseBody
	public PersonalInfo getEmployeeById(@PathVariable("employeeId") String employeeId) throws Exception {
		System.out.println("EmployeeId: " + employeeId);

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setEmployeeId(employeeId);

		List<PersonalInfo> list = (List<PersonalInfo>) manager.manageSearch(searchRequest);
		if (list == null) {
			throw new Exception("No records found");
		}
		System.out.println("Returned: " + list.size() + " records.");
		return list.get(0);
	}

	@DeleteMapping("/{employmentId}/delete")
	@ResponseBody
	public void deleteEmployemntById(@PathVariable("employmentId") String employmentId) throws Exception {
		System.out.println(" EmploymentId: " + employmentId);

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setEmploymentId(employmentId);

		manager.manageDelete(searchRequest);

	}

	@RequestMapping("/retreive")
	@ResponseBody
	public List<PersonalInfo> getEmployeeByName(@RequestParam("name") String name) throws Exception {

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setFirstName(name);
		List<PersonalInfo> list = (List<PersonalInfo>) manager.manageSearch(searchRequest);
		if (list == null) {
			throw new Exception("No records found");
		}
		System.out.println("Returned: " + list.size() + " records.");
		return list;
	}

	@RequestMapping("/retreiveAll")
	@ResponseBody
	public List<PersonalInfo> getAllEmployeesName() throws Exception {

		List<PersonalInfo> list = (List<PersonalInfo>) manager.manageSearch(null);
		if (list == null) {
			throw new Exception("No records found");
		}
		System.out.println("Returned: " + list.size() + " records.");
		return list;
	}
	
	@PostMapping("/retreiveAllEmp")
	@ResponseBody
	public PaginatedWrapper getAllEmployeesList(
			@RequestBody PaginatedWrapper paginatedWrapper) throws Exception {

		int currPage = paginatedWrapper.getCurrPage();
		int limit = paginatedWrapper.getLimit();

				
		List<PaginatedWrapper> list = (List<PaginatedWrapper>) manager.manageSearch(paginatedWrapper);		
		if (list == null) {
			throw new Exception("No records found");
		}
		PaginatedWrapper result = list.get(0);
		
		int totalRecords = result.getTotalRecords();
		int totalPages = (int) Math.round(totalRecords/limit + 0.5);
		int itemsInCurrPage = Integer.valueOf(result.getPersonalInfo().size());
		result.setTotalPages(totalPages);
		result.setItemsInCurrPage(itemsInCurrPage);
		int start = ((currPage - 1 ) * limit ) + 1;
		int end = start + limit - 1 > totalRecords ? totalRecords : start + limit - 1 ;
		
		paginatedWrapper.setStart(start);
		paginatedWrapper.setEnd(end);
		
		result.setSearchRequest(null);
		System.out.println("Returned: " + list.size() + " records.");
		return result;
	}

	@RequestMapping("/retreive/docs")
	@ResponseBody
	public PersonalInfo getAllDocuments() throws Exception {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setSearchType(SearchType.DOCUMENT.toString());
		List<PersonalInfo> list = (List<PersonalInfo>) manager.manageSearch(searchRequest);
		if (list == null) {
			throw new Exception("No records found");
		}
		System.out.println("Returned: " + list.size() + " records.");
		return list.get(0);
	}
	
	@RequestMapping("{employeeId}/retreive/docs")
	@ResponseBody
	public PersonalInfo getAllDocuments(@PathVariable("employeeId") String employeeId) throws Exception {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setEmployeeId(employeeId);
		searchRequest.setSearchType(SearchType.DOCUMENT.toString());
		List<PersonalInfo> list = (List<PersonalInfo>) manager.manageSearch(searchRequest);
		if (list == null) {
			throw new Exception("No records found");
		}
		System.out.println("Returned: " + list.size() + " records.");
		return list.get(0);
	}

	@PostMapping("/{id}/upload")
	@ResponseBody
	public List<DocumentObj> uploadFile(@PathVariable("id") String employeeId,
			@RequestParam("file") MultipartFile[] files) throws Exception {

		List<DocumentObj> documentObjs = (List<DocumentObj>) manager.manageUpload(files);
		List<DocumentObj> response = null;

		if (documentObjs != null && documentObjs.size() > 0) {
			PersonalInfo personalInfo = new PersonalInfo();
			personalInfo.setEmployeeId(employeeId);
			personalInfo.getDocumnetObj().addAll(documentObjs);
			response = ((PersonalInfo) manager.manageUpdate(personalInfo)).getDocumnetObj();
		}
		return response;
	}
	
	@PostMapping("/{id}/updateDoc")
	@ResponseBody
	public List<DocumentObj> updateFile(@PathVariable("id") String employeeId,
			@RequestBody List<DocumentObj> documentObjs) throws Exception {

		return (List<DocumentObj>) manager.manageUpdates(documentObjs);
	}
	
	@PostMapping("/deleteDocs")
	@ResponseBody
	public boolean updateFile(@RequestBody List<DocumentObj> documentObjs) throws Exception {
		if(documentObjs != null && documentObjs.size() > 0){
			List<Long> ids = documentObjs.stream().map(id -> id.getDocId()).collect(Collectors.toList());
			manager.manageDelete(ids, DocumentsDTO.class);
		}
		return true;
	}

}
