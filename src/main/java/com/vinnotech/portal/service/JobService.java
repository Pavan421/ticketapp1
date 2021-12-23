package com.vinnotech.portal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.vinnotech.portal.exception.ValidateException;
import com.vinnotech.portal.model.Job;
import com.vinnotech.portal.repository.JobRepository;

@Service
public class JobService {

	private static final String CLASSNAME = " JobService";
	private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);
	@Autowired
	private JobRepository jobRepository;

	/**
	 * Creating the Job
	 * 
	 * @param job
	 * @return
	 */
	public String createJobNotification(Job job) {
		String methodName = "createJobNotification";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			jobRepository.save(job);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "created Job sucsessfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating job " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());

		}
	}

	/**
	 * Getting the job based on jobId
	 * 
	 * @param id
	 * @return
	 */
	public Job getJobNotification(Long id) {
		String methodName = "getJobNotification";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		Optional<Job> job = jobRepository.findById(id);
		try {
			if (job.isPresent()) {
				LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
				return job.get();
			}
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting job " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
		throw new ValidateException("Record not found with id : " + id);
	}

	/**
	 * deleting the job based on jobId
	 * 
	 * @param id
	 * @return
	 */
	public String deleteJobNotification(Long id) {
		String methodName = "deleteJobNotification";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			jobRepository.deleteById(id);
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return "Job Deleted successfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting job " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * Getting the All Jobs in descending order with pagination and sorting
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	public Page<Job> getAllJobDesc(int offset, int pageSize, String field) {
		String methodName = "getAllJobDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Job> getAllJobsDesc = jobRepository
					.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return getAllJobsDesc;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Job desending " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * getting all Jobs in Ascending order with pagination and sorting
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	public Page<Job> getAllJobAse(int offset, int pageSize, String field) {
		String methodName = "getAllJobAse";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Job> getAllJobsAse = jobRepository
					.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return getAllJobsAse;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Job Asending" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * getting all published jobs in descending order with pagination and sorting
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	public Page<Job> getAllJobswithSortAndPagiDesc(boolean publish, int offset, int pageSize, String field) {
		String methodName = "getAllJobswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sm.format(new Date());
			Page<Job> Jobs = jobRepository.findByJobsWithPublishDate(publish, currentDate,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return Jobs;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Job with publish and desending" + methodName
					+ e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * getting all published jobs in Ascending order with pagination and sorting
	 * 
	 * @param publish
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	public Page<Job> getAllJobswithSortAndPagiASC(boolean publish, int offset, int pageSize, String field) {
		String methodName = "getAllJobswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sm.format(new Date());
			Page<Job> Jobs = jobRepository.findByJobsWithPublishDate(publish, currentDate,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return Jobs;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting All Job with publish and asending" + methodName
					+ e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

}
