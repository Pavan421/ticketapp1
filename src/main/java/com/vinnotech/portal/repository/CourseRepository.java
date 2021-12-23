package com.vinnotech.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinnotech.portal.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query(value = "select * from course where publish =?1 and reg_end_date >=?2", nativeQuery = true)
	Page<Course> findByCoursesWithPublishDate(boolean publish,String date,Pageable pageable);
}