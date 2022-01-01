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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Job {
	@Id
	@Column(name = "job_id")
	private Long ref;
	private String title;
	@Lob
	@Column(length = 1024)
	private String description;
	private String primarySkill;
	private String secondarySkill;
	private String experienceLevel;
	private String experience;
	private String contractType;
	private String contractDuration;
	private Double salary;
	// @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Paris")
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date postedOn;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedDate;
	private String location;
	private boolean publish;
	private String empId;
	private int count;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "job_applied", joinColumns = @JoinColumn(name = "job_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "job_ack_id", nullable = false, updatable = false))
	@JsonIgnore
	private List<JobsAcknowledgement> jobsAcks = new ArrayList<>();

}
