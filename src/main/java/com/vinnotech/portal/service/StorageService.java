package com.vinnotech.portal.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageService {

	@Value("${application.bucket.name}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3Client;
	@Autowired
	private EmployeeRepository empRepository;

	public String uploadFile(String empId, String name, String value, MultipartFile file) {
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
	}

	public byte[] downloadFile(String fileName) {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
		return fileName + " removed ...";
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}
}
