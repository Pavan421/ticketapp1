package com.vinnotech.portal.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.model.Job;
import com.vinnotech.portal.service.JobService;

@CrossOrigin
@RestController
@RequestMapping("/api/jobs")
public class JobController {

	private static final String CLASSNAME = "JobController";
	private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

	/**
	 * This method is used to add new Job By HR from DashBoard
	 * 
	 * @param job
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@PutMapping("/create")
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		String methodName = "createJob";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String createdJob = jobService.createJobNotification(job);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "creating job");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(createdJob);
	}

	/**
	 * This method is used to get Job details based on jobId
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable("jobId") Long jobId) {
		String methodName = "getJob";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Job job = jobService.getJobNotification(jobId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "got job by jobId");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(job);
	}

	/**
	 * This method is used get all jobs posted by HR in Dashboard with pagination
	 * and sorting with desending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spdesc/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<Job>> getAllJobDesc(@PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize, @PathVariable("field") String field) {
		String methodName = "getAllJobDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all jobs with pagination and desc sorting");
		Page<Job> pJobdesc = jobService.getAllJobDesc(offset, pageSize, field);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pJobdesc);
	}

	/**
	 * This method is used get all jobs posted by HR in Dashboard with pagination
	 * and sorting with Asending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/spase/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<Job>> getAllJobAse(@PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize, @PathVariable("field") String field) {
		String methodName = "getAllJobAse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Job> pJobasc = jobService.getAllJobAse(offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all jobs with pagination and asc sorting");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pJobasc);
	}

	/**
	 * This method is used to get all published jobs in the Home Page with
	 * pagination and sorting with desending order
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/sppublishdesc/{publish}/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<Job>> getAllJobswithSortAndPagiDesc(@PathVariable("publish") boolean publish,
			@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize,
			@PathVariable("field") String field) {
		String methodName = "getAllJobswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Job> pJobdescwithPubDesc = jobService.getAllJobswithSortAndPagiDesc(publish, offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all jobs with pagination, desc sorting plus publish");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pJobdescwithPubDesc);
	}

	/**
	 * This method is used to get all published jobs in the Home Page with
	 * pagination and sorting with asending order
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@GetMapping("/sppublishasc/{publish}/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<Job>> getAllJobswithSortAndPagiASC(@PathVariable("publish") boolean publish,
			@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize,
			@PathVariable("field") String field) {
		String methodName = "getAllJobswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Job> pJobdescwithPubasc = jobService.getAllJobswithSortAndPagiASC(publish, offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all jobs with pagination, asc sorting plus publish");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pJobdescwithPubasc);
	}

	/**
	 * This method is used to delete the posted job By admin from DashBoard
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable("id") Long id) {
		String methodName = "deleteJob";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String deletedJob = jobService.deleteJobNotification(id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Deleting  job");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(deletedJob);
	}

	/**
	 * getting all jobs by search descending order and pagination
	 * 
	 * @param publish
	 * @param searchparam
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/searchJobsByParam/{publish}/{searchparam}/{offset}/{pageSize}")
	public ResponseEntity<Page<Job>> searchJobsByQueryParam(@PathVariable("publish") boolean publish,
			@PathVariable("searchparam") String searchparam, @PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize) {
		String methodName = "searchJobsByQueryParam";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Job> searchJobsPage = jobService.searchJobsByParam(publish, searchparam, offset, pageSize);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all jobs with by searching");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(searchJobsPage);
	}
}
