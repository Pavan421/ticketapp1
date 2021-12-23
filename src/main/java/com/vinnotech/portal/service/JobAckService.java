package com.vinnotech.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	public void createAck(JobsAcknowledgement jobAck, Long jobId) {

		Job job = jobRepository.findById(jobId).get();
		int count = job.getCount();
		job.setCount(count + 1);
//		job.getJobsAcks().add(jobAck);
		jobRepository.save(job);
	}

	public Page<JobsAcknowledgement> getAllJobAckswithSortAndPagiDesc(Long jobId, int offset, int pageSize,
			String field) {
		Page<JobsAcknowledgement> jobAcks = null;
		if (!StringUtils.isEmpty(field)) {
			jobAcks = jobAckRepository.findByJobAckWithJobId(jobId,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
		} else {
			jobAcks = jobAckRepository.findByJobAckWithJobIdAndOrder(jobId, PageRequest.of(offset, pageSize));

		}
		return jobAcks;
	}

	public Page<JobsAcknowledgement> getAllJobAckswithSortAndPagiASC(Long jobId, int offset, int pageSize,
			String field) {
		Page<JobsAcknowledgement> jobAcks = jobAckRepository.findByJobAckWithJobId(jobId,
				PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
		return jobAcks;
	}
}
