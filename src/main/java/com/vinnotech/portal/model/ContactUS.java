package com.vinnotech.portal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
//import java.util.GregorianCalendar;

@Data
@NoArgsConstructor
@Entity
public class ContactUS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "emplyoee_id")
	private Long emlpoyeeid;

	@Column(name = "first_name")
	private String name;

	@Column(name = "mail_id")
	private String email;

	@Column(name = "message")
	private String message;

	@Column(name = "phone_num")
	private Long phonenumber;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;

}
