package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.model.StudentAck;
import com.vinnotech.portal.service.StudentAckService;

@CrossOrigin
@RestController
@RequestMapping("/api/studentack")
public class StudentAckController {

	private static final String CLASSNAME = "StudentAckController";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private StudentAckService studentAckService;

	@PostMapping("/create/{courseId}")
	public ResponseEntity<String> createJobAck(@RequestBody StudentAck studentAck, @PathVariable("courseId") Long courseId) {
		String methodName = "createJobAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String createdStudentAck = studentAckService.createAck(studentAck, courseId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "creating Student acknowledgement");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(createdStudentAck);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping
	public ResponseEntity<List<StudentAck>> getAllStudentAcks() {
		String methodName = "getAllStudentAcks";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		List<StudentAck> studentAckList = studentAckService.getAllStudentAcks();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Student Acknowledgement");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(studentAckList);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spStudentAckDesc/{studentId}/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<StudentAck>> getAllStudentAckswithSortAndPagiDesc(@PathVariable Long studentId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllStudentAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all Student Acknowledgement with pagination and desc sorting");
		Page<StudentAck> studentAckSwithSort = studentAckService.getAllStudentAckswithSortAndPagiDesc(studentId, offset,
				pageSize, field);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(studentAckSwithSort);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spStudentAckDesc/{studentId}/{offset}/{pageSize}")
	public ResponseEntity<Page<StudentAck>> getAllStudentAckswithSortAndPagiDesc(@PathVariable Long studentId,
			@PathVariable int offset, @PathVariable int pageSize) {
		String methodName = "getAllStudentAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all Student Acknowledgement with pagination and desc sorting");
		Page<StudentAck> studentAckSwithSort = studentAckService.getAllStudentAckswithSortAndPagiDesc(studentId, offset,
				pageSize, "");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(studentAckSwithSort);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spStudentAckAsc/{studentId}/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<StudentAck>> getAllStudentAckswithSortAndPagiASC(@PathVariable Long studentId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllStudentAckswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all Student Acknowledgement with pagination and Asc sorting");
		Page<StudentAck> studentAckSwithSort = studentAckService.getAllStudentAckswithSortAndPagiASC(studentId, offset,
				pageSize, field);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(studentAckSwithSort);

	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudentAck(@PathVariable Long id) {
		String methodName = "deleteStudentAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String deletedStudent = studentAckService.deleteStudentAck(id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Student Acknowledgement deleted");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(deletedStudent);
	}
}
