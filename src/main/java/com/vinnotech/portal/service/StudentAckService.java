package com.vinnotech.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.Course;
import com.vinnotech.portal.model.StudentAck;
import com.vinnotech.portal.repository.CourseRepository;
import com.vinnotech.portal.repository.StudentAckRepository;

@Service
public class StudentAckService {

	private static final String CLASSNAME = "StudentAckService ";
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentAckService.class);
	@Autowired
	private StudentAckRepository studentAckRepository;
	@Autowired
	private CourseRepository courseRepository;

	public String createAck(StudentAck studentAck, Long courseId) {

		String methodName = "createAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
    try {
		Course course = courseRepository.findById(courseId).get();
		int count = course.getCount();
		course.setCount(count + 1);
		course.getStudentAcks().add(studentAck);
		courseRepository.save(course);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return "created Student Acknowledgement sucsessfully";
	    }catch (Exception e) {
		LOGGER.error(CLASSNAME + "got error while creating Student Acknowledgement " + methodName + e.getMessage());
		throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
	    }
	}

	public List<StudentAck> getAllStudentAcks() {

		String methodName = "getAllStudentAcks";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
      try {
    	  LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return studentAckRepository.findAll();
          } catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Student Acknowledgement " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		   }
	}

	public Page<StudentAck> getAllStudentAckswithSortAndPagiDesc(Long studentId, int offset, int pageSize,
			String field) {

		String methodName = "getAllStudentAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
       try {
		Page<StudentAck> studentAcks = null;
		if (!StringUtils.isEmpty(field)) {
			studentAcks = studentAckRepository.findByStudentAckWithCourseId(studentId,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		} else {
			studentAcks = studentAckRepository.findByStudentAckWithCourseIdAndOrder(studentId,
					PageRequest.of(offset, pageSize));

		}
		return studentAcks;
       }catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Student Acknowledgement pagination sort in desc " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public Page<StudentAck> getAllStudentAckswithSortAndPagiASC(Long studentId, int offset, int pageSize,
			String field) {
		String methodName = "getAllStudentAckswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try
		{
		Page<StudentAck> studentAcks = studentAckRepository.findByStudentAckWithCourseId(studentId,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return studentAcks;
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Student Acknowledgement pagination sort in Asc " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public String deleteStudentAck(Long id) {
		String methodName = "deleteStudentAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		studentAckRepository.deleteById(id);
		return "Student Acknowledgement deleted sucsessfully";
		    }catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating Student Acknowledgement " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		  }
	}

}
