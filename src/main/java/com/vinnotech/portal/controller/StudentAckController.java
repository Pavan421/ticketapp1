package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PutMapping("/{courseId}")
	public void createJobAck(@RequestBody StudentAck studentAck, @PathVariable Long courseId) {

		String methodName = "createJobAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		studentAckService.createAck(studentAck, courseId);
	}

	@GetMapping
	public List<StudentAck> getAllStudentAcks() {

		String methodName = "getAllStudentAcks";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return studentAckService.getAllStudentAcks();
	}

	@GetMapping("/spStudentAckdesc/{studentId}/{offset}/{pageSize}/{field}")
	private Page<StudentAck> getAllStudentAckswithSortAndPagiDesc(@PathVariable Long studentId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {

		String methodName = "getAllStudentAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return studentAckService.getAllStudentAckswithSortAndPagiDesc(studentId, offset, pageSize, field);
	}

	@GetMapping("/spStudentAckdesc/{studentId}/{offset}/{pageSize}")
	private Page<StudentAck> getAllStudentAckswithSortAndPagiDesc(@PathVariable Long studentId,
			@PathVariable int offset, @PathVariable int pageSize) {

		String methodName = "getAllStudentAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return studentAckService.getAllStudentAckswithSortAndPagiDesc(studentId, offset, pageSize, "");
	}

	@GetMapping("/spStudentAckasc/{studentId}/{offset}/{pageSize}/{field}")
	private Page<StudentAck> getAllStudentAckswithSortAndPagiASC(@PathVariable Long studentId, @PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {

		String methodName = "getAllStudentAckswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return studentAckService.getAllStudentAckswithSortAndPagiASC(studentId, offset, pageSize, field);
	}

	@DeleteMapping("/{id}")
	public void deleteStudentAck(@PathVariable Long id) {

		String methodName = "deleteStudentAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		studentAckService.deleteStudentAck(id);
	}
}
