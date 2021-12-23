package com.vinnotech.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinnotech.portal.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	/*
	 * @Query(value = "select * from job where publish =?1 ", nativeQuery = true)
	 * List<Job> findBySkill(boolean publish);
	 */
	@Query(value = "select * from job where publish =?1 and end_date >=?2", nativeQuery = true)
	Page<Job> findByJobsWithPublishDate(boolean publish,String date,Pageable pageable);

}
