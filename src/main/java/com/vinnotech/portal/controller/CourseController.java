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

import com.vinnotech.portal.model.Course;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.CourseService;

@CrossOrigin
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private static final String CLASSNAME = "CourseController";
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	/**
	 * This method is used create the course by HR in DashBoard
	 * 
	 * @param course
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<String> createCourse(@RequestBody Course course) {
		String methodName = "createCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String createdCourse = courseService.createCourse(course);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "creating Course");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(createdCourse);
	}

	/**
	 * Updating course with course Id
	 * 
	 * @param course
	 * @param courseId
	 * @return
	 */
	/*
	 * @PutMapping("/update/{courseId}") public ResponseEntity<String>
	 * updateCourse(@RequestBody Course course, @PathVariable Long courseId) {
	 * String methodName = "updateCourse"; LOGGER.info(CLASSNAME +
	 * ": Entering into the " + methodName + " method"); String updatedCourse =
	 * courseService.updateCourse(course, courseId); HttpHeaders header = new
	 * HttpHeaders(); header.add("desc", "updating  Course"); LOGGER.info(CLASSNAME
	 * + ": Existing from  " + methodName + " method"); return
	 * ResponseEntity.status(HttpStatus.OK).headers(header).body(updatedCourse); }
	 */

	/**
	 * Getting course by courseId
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable Long id) {
		String methodName = "getCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Course course = courseService.getCourse(id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting Course");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(course);
	}

	/**
	 * Getting all courses without paging and sorting
	 * 
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses() {
		String methodName = "getAllCourse ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		List<Course> courseList = courseService.getAllCourses();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(courseList);
	}

	/**
	 * Getting all courses with pagination and sorting with descending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spdesc/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<Course>> getAllCourseswithSortAndPagiDesc(@PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllCourseswithSortAndPagiDesc ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Course> pageCoursesDesc = courseService.getAllCourseswithSortAndPagiDesc(offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageCoursesDesc);
	}

	/**
	 * Getting all courses with pagination and sorting with Ascending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spase/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<Course>> getAllCourseswithSortAndPagiASC(@PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize, @PathVariable("field") String field) {
		String methodName = "getAllCourseswithSortAndPagiASC ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Course> pageCoursesAse = courseService.getAllCourseswithSortAndPagiASC(offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageCoursesAse);
	}

	/**
	 * Getting all Published Courses in Home Page with Pagination and sorting
	 * descending Order
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/sppublishdesc/{publish}/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<Course>> getAllCourseswithSortAndPagiDesc(@PathVariable("publish") boolean publish,
			@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize,
			@PathVariable("field") String field) {
		String methodName = "getAllJobswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Course> pageCouresespubDese = courseService.getAllCourseswithSortAndPagiDesc(publish, offset, pageSize,
				field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses with Publish and Dese");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageCouresespubDese);
	}

	/**
	 * Getting all Published Courses in Home Page with Pagination and sorting
	 * Ascending Order
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/sppublishasc/{publish}/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<Course>> getAllCourseswithSortAndPagiASC(@PathVariable("publish") boolean publish,
			@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize,
			@PathVariable("field") String field) {
		String methodName = "getAllCourseswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Course> pageCouresespubAse = courseService.getAllCourseswithSortAndPagiASC(publish, offset, pageSize,
				field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses with Publish and Ase");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageCouresespubAse);
	}

	/**
	 * Deleting the job by using couseId
	 * 
	 * @param courseId
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@DeleteMapping("/delete/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Long courseId) {
		String methodName = "deleteCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String deletedCouese = courseService.deleteCourse(courseId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(deletedCouese);
	}

	/**
	 * search all courses by search term
	 * 
	 * @param publish
	 * @param serachParam
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/searchcourseByParam/{publish}/{serachParam}/{offset}/{pageSize}")
	public ResponseEntity<Page<Course>> getCoursesBySearchTerm(@PathVariable("publish") boolean publish,
			@PathVariable("serachParam") String serachParam, @PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize) {
		String methodName = "getAllCourseswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Course> pageCouresespubAse = courseService.searchAllCourse(publish, serachParam, offset, pageSize);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses by search term");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageCouresespubAse);
	}
}
