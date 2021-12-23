package com.vinnotech.portal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
public class Course {
	@Id
	@Column(name = "course_id")
	private Long courseCode;
	private String courseName;
	@Lob
	@Column(length = 1024)
	private String courseContent;
	// Regular/fasttrack/Personal
	private CourseTypeEnum type;
	// online/offline/classroom
	private CourseModeEnum mode;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date regEndDate;
	private String timings;
	private Long fee;
	private String trainer;
	private String duration;
	private boolean publish;
	private String empId;
	private int count;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "course_applied", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = {
			@JoinColumn(name = "student_ack_id") })
	@JsonIgnore
	private List<StudentAck> studentAcks = new ArrayList<>();

}
