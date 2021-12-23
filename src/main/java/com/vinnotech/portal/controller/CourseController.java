package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.Course;
import com.vinnotech.portal.service.CourseService;

@CrossOrigin
@RestController
@RequestMapping("/api/course")
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
	@PostMapping("/save")
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
	 * updating course with course Id
	 * 
	 * @param course
	 * @param courseId
	 * @return
	 */
	@PutMapping("/update/{courseId}")
	public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable Long courseId) {
		String methodName = "updateCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String updatedCourse = courseService.updateCourse(course, courseId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "updating  Course");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(updatedCourse);
	}

	/**
	 * getting course by courseId
	 * 
	 * @param id
	 * @return
	 */
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
	 * getting all courses without paging and sorting
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Course>> getAllCourse() {
		String methodName = "getAllCourse ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		List<Course> courseList = courseService.getAllCourses();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(courseList);
	}

	/**
	 * deleting the job by using couseId
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
		String methodName = "deleteCourse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String deletedCouese = courseService.deleteCourse(id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(deletedCouese);
	}

	/**
	 * getting all courses with pagination and sorting with desending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@GetMapping("/spdesc/{offset}/{pageSize}/{field}")
	private ResponseEntity<Page<Course>> getAllJobswithSortAndPagiDesc(@PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllJobswithSortAndPagiDesc ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Course> pageCoursesDesc = courseService.getAllCourseswithSortAndPagiDesc(offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageCoursesDesc);
	}

	/**
	 * getting all courses with pagination and sorting with Ascending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@GetMapping("/spase/{offset}/{pageSize}/{field}")
	private ResponseEntity<Page<Course>> getAllCourseswithSortAndPagiASC(@PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllCourseswithSortAndPagiASC ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Course> pageCoursesAse = courseService.getAllCourseswithSortAndPagiASC(offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageCoursesAse);
	}

	/**
	 * getting all Published Courses in Home Page with Pagination and sorting
	 * descending Order
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@GetMapping("/sppublishdesc/{publish}/{offset}/{pageSize}/{field}")
	private ResponseEntity<Page<Course>> getAllCourseswithSortAndPagiDesc(@PathVariable boolean publish,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
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
	 * getting all Published Courses in Home Page with Pagination and sorting
	 * Ascending Order
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@GetMapping("/sppublishasc/{publish}/{offset}/{pageSize}/{field}")
	private ResponseEntity<Page<Course>> getAllCourseswithSortAndPagiASC(@PathVariable boolean publish,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllCourseswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Course> pageCouresespubAse = courseService.getAllCourseswithSortAndPagiASC(publish, offset, pageSize,
				field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses with Publish and Ase");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageCouresespubAse);
	}
}
