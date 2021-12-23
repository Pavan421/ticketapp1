package com.vinnotech.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinnotech.portal.model.JobsAcknowledgement;

public interface JobAckRepository extends JpaRepository<JobsAcknowledgement, Long> {
	@Query(value = "select * from jobs_acknowledgement inner join job_applied on job_applied.job_ack_id=jobs_acknowledgement.job_ack_id where job_applied.job_id=?1", nativeQuery = true)
	Page<JobsAcknowledgement> findByJobAckWithJobId(Long jobId, Pageable pageable);
	
	@Query(value = "select * from jobs_acknowledgement inner join job_applied on job_applied.job_ack_id=jobs_acknowledgement.job_ack_id where job_applied.job_id=?1 order by applied_date", nativeQuery = true)
	Page<JobsAcknowledgement> findByJobAckWithJobIdAndOrder(Long jobId, Pageable pageable);
}