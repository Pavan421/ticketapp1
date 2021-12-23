package com.vinnotech.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	public void createAck(StudentAck studentAck, Long courseId) {

		String methodName = "createAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);

		Course course = courseRepository.findById(courseId).get();
		int count = course.getCount();
		course.setCount(count + 1);
		course.getStudentAcks().add(studentAck);
		courseRepository.save(course);
	}

	public List<StudentAck> getAllStudentAcks() {

		String methodName = "getAllStudentAcks";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);

		return studentAckRepository.findAll();
	}

	public Page<StudentAck> getAllStudentAckswithSortAndPagiDesc(Long studentId, int offset, int pageSize,
			String field) {

		String methodName = "getAllStudentAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);

		Page<StudentAck> studentAcks = null;
		if (!StringUtils.isEmpty(field)) {
			studentAcks = studentAckRepository.findByStudentAckWithCourseId(studentId,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		} else {
			studentAcks = studentAckRepository.findByStudentAckWithCourseIdAndOrder(studentId,
					PageRequest.of(offset, pageSize));

		}
		return studentAcks;
	}

	public Page<StudentAck> getAllStudentAckswithSortAndPagiASC(Long studentId, int offset, int pageSize,
			String field) {

		String methodName = "getAllStudentAckswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		Page<StudentAck> studentAcks = studentAckRepository.findByStudentAckWithCourseId(studentId,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return studentAcks;
	}

	public void deleteStudentAck(Long id) {

		String methodName = "deleteStudentAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		studentAckRepository.deleteById(id);
	}

}
