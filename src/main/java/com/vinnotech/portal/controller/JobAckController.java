package com.vinnotech.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.JobsAcknowledgement;
import com.vinnotech.portal.service.JobAckService;

@CrossOrigin
@RestController
@RequestMapping("/api/joback")
public class JobAckController {

	private static final String CLASSNAME = "JobAckController";
	private static final Logger LOGGER = LoggerFactory.getLogger(JobAckController.class);
	@Autowired
	private JobAckService jobAckService;

	@PutMapping("/{jobId}")
	public void createJobAck(@RequestBody JobsAcknowledgement jobAck, @PathVariable Long jobId) {

		String methodName = "createJobAck";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		jobAckService.createAck(jobAck, jobId);
	}

	@GetMapping("/spJobAckdesc/{jobId}/{offset}/{pageSize}/{field}")
	private Page<JobsAcknowledgement> getAllJobAckswithSortAndPagiDesc(@PathVariable Long jobId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllJobAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return jobAckService.getAllJobAckswithSortAndPagiDesc(jobId, offset, pageSize, field);
	}

	@GetMapping("/spJobAckdesc/{jobId}/{offset}/{pageSize}")
	private Page<JobsAcknowledgement> getAllJobAckswithSortAndPagiDesc(@PathVariable Long jobId,
			@PathVariable int offset, @PathVariable int pageSize) {
		String methodName = "getAllJobAckswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return jobAckService.getAllJobAckswithSortAndPagiDesc(jobId, offset, pageSize, "");
	}

	@GetMapping("/spJobAckasc/{jobId}/{offset}/{pageSize}/{field}")
	private Page<JobsAcknowledgement> getAllJobAckswithSortAndPagiASC(@PathVariable Long jobId,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {

		String methodName = "getAllJobAckswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return jobAckService.getAllJobAckswithSortAndPagiASC(jobId, offset, pageSize, field);
	}
}
