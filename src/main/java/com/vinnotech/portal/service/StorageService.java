package com.vinnotech.portal.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class StorageService {

	private static final String CLASSNAME = "StorageService ";
	private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);
	@Value("${application.bucket.name}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3Client;
	@Autowired
	private EmployeeRepository empRepository;

	public String uploadFile(String empId, String name, String value, MultipartFile file) {
		String methodName = "uploadFile";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		File fileObj = convertMultiPartFileToFile(file);
		String fileName = empId + "_" + name + "." + file.getOriginalFilename().split(".")[1];
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		fileObj.delete();
		Optional<Employee> employee = empRepository.findById(Long.parseLong(empId));
		if (employee.isPresent() && !employee.get().isEmployeeDeleted()) {
			Employee emp = employee.get();
			if (name.equals("pancard")) {
				emp.setPanCardPath(fileName);
				emp.setPanNumber(value);
			} else if (name.equals("passport")) {
				emp.setPassportNumber(value);
				emp.setPassportPath(fileName);
			} else if (name.equals("personalnumber")) {
				emp.setPersonalNumberPath(fileName);
				emp.setPersonalNumber(value);
			} else if (name.equals("resume")) {
				emp.setResumePath(fileName);
			} else if (name.equals("photo")) {
				emp.setPhotoPath(fileName);
			}
			empRepository.save(emp);
		}
		return "File uploaded : " + fileName;
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while uploading the file " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public byte[] downloadFile(String fileName) {
		String methodName = "uploadFile";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while downloading the file " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public String deleteFile(String fileName) {
		String methodName = "deleteFile";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		s3Client.deleteObject(bucketName, fileName);
		return fileName + " removed ...";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while deleting the file " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		String methodName = "convertMultiPartFileToFile";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
			return convertedFile;
		}  catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while downloading the file " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}	
	}
}
