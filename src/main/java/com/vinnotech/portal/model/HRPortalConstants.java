package com.vinnotech.portal.model;

public class HRPortalConstants {

	public final static String ROLE_ADMIN = "ADMIN_ROLE";
	public final static String ROLE_HR = "HR_ROLE";
	public final static String ROLE_RECRUITER = "RECRUITER_ROLE";
	public final static String ROLE_EMPLOYEE = "EMPLOYEE_ROLE";
	
	// These constants for Accessing controllers by the role based
	// @PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_EMPLOYEE_ONLY)
	// @PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_ONLY)
	// @PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	// @PreAuthorize(HRPortalConstants.ROLE_ADMIN_ONLY)
	public final static String ROLE_ADMIN_HR_RECRUITER_EMPLOYEE_ONLY = "hasAuthority('ADMIN_ROLE') or hasAuthority('HR_ROLE') or hasAuthority('RECRUITER_ROLE') or hasAuthority('EMPLOYEE_ROLE')";
	public final static String ROLE_ADMIN_HR_RECRUITER_ONLY = "hasAuthority('ADMIN_ROLE') or hasAuthority('HR_ROLE') or hasAuthority('RECRUITER_ROLE')";
	public final static String ROLE_ADMIN_HR_ONLY = "hasAuthority('ADMIN_ROLE') or hasAuthority('HR_ROLE')";
	public final static String ROLE_ADMIN_ONLY = "hasAuthority('ADMIN_ROLE')";
	
	public final static String IS_REFRESH_TOKEN = "isRefreshToken";
}
