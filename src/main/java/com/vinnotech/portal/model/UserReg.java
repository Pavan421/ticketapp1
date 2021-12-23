package com.vinnotech.portal.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserReg {
	@Id
	private Long empId;
	private String username;
	private String password;
	private String role;
	private boolean status;
	private int priority;
}
