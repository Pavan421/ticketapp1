package com.vinnotech.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePwd {	
	private String currentPwd;
	private String newPwd;
	private String confirmPwd;
}
