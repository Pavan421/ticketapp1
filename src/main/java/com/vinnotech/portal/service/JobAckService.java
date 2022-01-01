package com.vinnotech.portal.service;

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
import com.vinnotech.portal.model.Job;
import com.vinnotech.portal.model.JobsAcknowledgement;
import com.vinnotech.portal.repository.JobAckRepository;
import com.vinnotech.portal.repository.JobRepository;

@Service
public class JobAckService {

	private static final String CLASSNAME = "JobAckService";
	private static final Logger LOGGER = LoggerFactory.getLogger(JobAckService.class);
	@Autowired
	private JobAckRepository jobAckRepository;
	@Autowired
	private JobRepository jobRepository;

	public String createAck(JobsAcknowledgement jobAck, Long jobId) {
		String methodName = "createAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
     try {
		Job job = jobRepository.findById(jobId).get();
		int count = job.getCount();
		job.setCount(count + 1);
		job.getJobsAcks().add(jobAck);
		jobRepository.save(job);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return "created Job Acknowledgement sucsessfully";
	   }catch (Exception e) {
		LOGGER.error(CLASSNAME + "got error while creating job Acknowledgement " + methodName + e.getMessage());
		throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
	 }
	}

	public Page<JobsAcknowledgement> getAllJobAckswithSortAndPagiDesc(Long jobId, int offset, int pageSize,
			String field) {
		String methodName = "getAllJobAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		Page<JobsAcknowledgement> jobAcks = null;
		if (!StringUtils.isEmpty(field)) {
			jobAcks = jobAckRepository.findByJobAckWithJobId(jobId,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		} else {
			jobAcks = jobAckRepository.findByJobAckWithJobIdAndOrder(jobId, PageRequest.of(offset, pageSize));

		}
		return jobAcks;
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting all job Ack  " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		 }
	}

	public Page<JobsAcknowledgement> getAllJobAckswithSortAndPagiASC(Long jobId, int offset, int pageSize,
			String field) {
		String methodName = "getAllJobAckswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		Page<JobsAcknowledgement> jobAcks = jobAckRepository.findByJobAckWithJobId(jobId,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return jobAcks;
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting all job Ack  " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		 }
	}
}
