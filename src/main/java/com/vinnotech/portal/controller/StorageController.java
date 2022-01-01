package com.vinnotech.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.StorageService;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class StorageController {
    
	private static final String CLASSNAME = "StorageController";
	private static final Logger LOGGER = LoggerFactory.getLogger(StorageController.class);
	@Autowired
	private StorageService service;	
	
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_EMPLOYEE_ONLY)
	@PostMapping("/upload/{empId}")
	public ResponseEntity<String> uploadFile(@PathVariable("empId") String empId, @RequestParam(name = "name") String name,
			@RequestParam(name = "value") String value, @RequestParam(value = "file") MultipartFile file) {
		String methodName = "uploadFile";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String uploadFile = service.uploadFile(empId, name, value, file);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "File update sucessful");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(uploadFile);
	}
	
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_EMPLOYEE_ONLY)
	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("fileName") String fileName) {
		byte[] data = service.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().contentLength(data.length).header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + fileName + "\"").body(resource);
	}
	/**
	 * This is for deleting the 
	 * 
	 * @param fileName
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_ONLY)
	@DeleteMapping("/delete/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable("fileName") String fileName) {
		String methodName = "uploadFile";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String deleteFile = service.deleteFile(fileName);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "File deleted sucessful");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(deleteFile);
	}
}
