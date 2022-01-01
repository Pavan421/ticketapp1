package com.vinnotech.portal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.Course;
import com.vinnotech.portal.repository.CourseRepository;

@Service
public class CourseService {

	private static final String CLASSNAME = "ContactusService";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactusService.class);

	@Autowired
	CourseRepository courseRepository;

	/**
	 * Creating course
	 * 
	 * @param course
	 * @return
	 */
	public String createCourse(Course course) {
		String methodName = "createCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			courseRepository.save(course);
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return "course created sucsessfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating Course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * Updating Course
	 * 
	 * @param course
	 * @param courseId
	 * @return
	 */
	public String updateCourse(Course course, Long courseId) {
		String methodName = "updateCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			course.setCourseCode(courseId);
			courseRepository.save(course);
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return "Updated Course sucsessfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while updating Course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * getting Course by courseId
	 * 
	 * @param id
	 * @return
	 */
	public Course getCourse(Long id) {
		String methodName = "getCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Optional<Course> optcourse = courseRepository.findById(id);
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return optcourse.get();
		} catch (Exception e) {

			LOGGER.error(CLASSNAME + "got error while getting course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage() + ":", "");
		}
	}

	/**
	 * getting all courses
	 * 
	 * @return
	 */
	public List<Course> getAllCourses() {
		String methodName = "getAllCourses";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			return courseRepository.findAll();
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Job desending " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * Deleting course By Id
	 * 
	 * @param id
	 * @return
	 */
	public String deleteCourse(Long id) {
		String methodName = "deleteCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			courseRepository.deleteById(id);
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return "Course sucsessfully deleted";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while deleting Corurse " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * Getting all Courses with Pagination and sorting Descending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	public Page<Course> getAllCourseswithSortAndPagiDesc(int offset, int pageSize, String field) {
		String methodName = "getAllCourseswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Course> pageCoursesDesc = courseRepository
					.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return pageCoursesDesc;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Course desending" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * Getting all Courses with Pagination and sorting Ascending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	public Page<Course> getAllCourseswithSortAndPagiASC(int offset, int pageSize, String field) {

		String methodName = "getAllCourseswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Course> pageCoursesAse = courseRepository
					.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return pageCoursesAse;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All course Asending" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * Getting all published courses in Home Page with desending order
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	public Page<Course> getAllCourseswithSortAndPagiDesc(boolean publish, int offset, int pageSize, String field) {
		String methodName = "getAllCourseswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sm.format(new Date());
			Page<Course> allcoursesWithPubDesc = courseRepository.findByCoursesWithPublishDate(publish, currentDate,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
			return allcoursesWithPubDesc;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All course desending order with Pulish" + methodName
					+ e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * Getting all published courses in Home Page with Asending order
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	public Page<Course> getAllCourseswithSortAndPagiASC(boolean publish, int offset, int pageSize, String field) {
		String methodName = "getAllCourseswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(new Date());
			String currentDate = sm.format(new Date());
			Page<Course> allcoursesWithPubAse = courseRepository.findByCoursesWithPublishDate(publish, currentDate,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
			return allcoursesWithPubAse;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All course Asending order with Pulish" + methodName
					+ e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * get the all courses based on search param
	 * 
	 * @param publish
	 * @param searchParam
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public Page<Course> searchAllCourse(boolean publish, String searchParam, int offset, int pageSize) {
		String methodName = "searchAllCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(new Date());
			String currentDate = sm.format(new Date());
			Page<Course> allcoursesWithPubAse = courseRepository.searchByCourse(publish, currentDate, searchParam,
					PageRequest.of(offset, pageSize));
			return allcoursesWithPubAse;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All course in search" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
}
