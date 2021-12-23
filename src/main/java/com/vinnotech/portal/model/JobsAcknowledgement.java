package com.vinnotech.portal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class JobsAcknowledgement {
	@Id
	@Column(name = "job_ack_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applicaionId;
	private String firstName;
	private String lastName;
	private String emailId;
	private long phoneNo;
	private String location;
	private String country;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date appliedDate;
	private String resumePath;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "jobsAcks")
	@JsonIgnore
	private List<Job> jobs = new ArrayList<>();

}
