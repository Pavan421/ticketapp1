package com.vinnotech.portal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {
	// General Information
	@Id
	@Column(name = "emp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	private String shortName;
	private String designation;
	private String officialEmailId;
	private String personalEmailId;
	private int mobileNumber;
	private String shortDesc;
	// Area of Work//Technologies
	private String accRole;
	private String skills;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date joinDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	// Governmant provided documents
	private String panNumber;
	private String personalNumber;
	private String passportNumber;
	private String pfAccountNumber;
	private String UAN;

	// Attachments
	private String panCardPath;
	private String personalNumberPath;
	private String passportPath;
	private String hikeLetterPath;
	private String promotionLatterPath;
	private String resumePath;
	private String photoPath;
	private boolean isEmployeeDeleted;

	@OneToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
	private BankDetails bankDetails;

	// Company Details
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_pcid", referencedColumnName = "emp_id")
	private List<PreviousCompany> previousCompany;

	@OneToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
	private Address permanentAddress;

	@OneToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
	private Address currentAddress;

	@OneToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
	private EmergencyContact emergencyContact;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "employees_projects", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	@JsonIgnore
	private List<Project> projects = new ArrayList<>();

}