package com.vinnotech.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinnotech.portal.model.StudentAck;

@Repository
public interface StudentAckRepository extends JpaRepository<StudentAck, Long> {
	@Query(value = "select * from student_ack inner join course_applied on course_applied.student_ack_id=student_ack.student_ack_id where course_applied.course_id=?1", nativeQuery = true)
	Page<StudentAck> findByStudentAckWithCourseId(Long courseId, Pageable pageable);
	
	@Query(value = "select * from student_ack inner join course_applied on course_applied.student_ack_id=student_ack.student_ack_id where course_applied.course_id=?1 order by applied_date", nativeQuery = true)
	Page<StudentAck> findByStudentAckWithCourseIdAndOrder(Long courseId, Pageable pageable);

}
