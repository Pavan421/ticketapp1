package com.vinnotech.portal.service;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vinnotech.portal.config.JwtUtil;
import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.ChangePwd;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.model.UserReg;
import com.vinnotech.portal.repository.EmployeeRepository;
import com.vinnotech.portal.repository.UserRepository;

@Service
public class UserService {

	private static final String CLASSNAME = "UserService";
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public String forgotPassword(String uname) {
		String methodName = "forgotPassword";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			UserReg newUser = userRepository.findByUsername(uname);
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(6);
			for (int i = 0; i < 6; i++) {
				sb.append(rnd.nextInt(10));
			}
			String generatedPwd = uname + sb.toString();
			newUser.setPassword(bcryptEncoder.encode(generatedPwd));
			UserReg u = userRepository.save(newUser);
			Long userid = u.getEmpId();
			Employee emp = empRepository.getById(userid);
			if (!StringUtils.isEmpty(u.getUsername())) {
				String html = "<!doctype html>\n" + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n"
						+ "      xmlns:th=\"http://www.thymeleaf.org\">\n" + "<head>\n"
						+ "    <meta charset=\"UTF-8\">\n" + "    <meta name=\"viewport\"\n"
						+ "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
						+ "    <title>Email</title>\n" + "</head>\n" + "<body>\n" + "<div>Hi  <b>" + emp.getFirstName()
						+ "</b></div>\n"
						+ "<div><b><h4>Your password got reseted Successfully please find the below details</h4></b></div>\n"
						+ "<div>Your username : <b>" + u.getUsername() + "</b></div>\n" + "\n"
						+ "<div>Your password : <b>" + generatedPwd + "</b></div>\n" + "<div>  <b>best regards</b></div>\n"
						+ "<div> <b> admin</b></div>\n" + "</body>\n" + "</html>\n";
				emailService.sendMail(emp.getOfficialEmailId(), "cloudcare", html);
			}
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "Password reseted successfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while reseting Password " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public String changePassword(ChangePwd changePwd) {
		String methodName = "changePassword";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			String currentPwd = changePwd.getCurrentPwd();
			String newPwd = changePwd.getNewPwd();
			String confirmPwd = changePwd.getConfirmPwd();
			System.out.println(newPwd == confirmPwd);
			System.out.println(newPwd.length());
			if (newPwd != null && confirmPwd != null && newPwd.equals(confirmPwd) && newPwd.length() >= 8) {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
						.getRequest();
				String username = jwtUtil.getUserNameFromToken(request);
				UserReg newUser = userRepository.findByUsername(username);
				if (bcryptEncoder.matches(currentPwd, newUser.getPassword())) {
					newUser.setPassword(bcryptEncoder.encode(newPwd));
					UserReg u = userRepository.save(newUser);
					Long userid = u.getEmpId();
					Employee emp = empRepository.getById(userid);
					if (!StringUtils.isEmpty(u.getUsername())) {
						String html = "<!doctype html>\n" + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n"
								+ "      xmlns:th=\"http://www.thymeleaf.org\">\n" + "<head>\n"
								+ "    <meta charset=\"UTF-8\">\n" + "    <meta name=\"viewport\"\n"
								+ "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n"
								+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
								+ "    <title>Email</title>\n" + "</head>\n" + "<body>\n" + "<div>Hi  <b>"
								+ emp.getFirstName() + "</b></div>\n"
								+ "<div><b><h4>Your password got Changed Successfully</h4></b></div>\n"
								+ "<div>  <b>best regards</b></div>\n" + "<div> <b> admin</b></div>\n" + "</body>\n"
								+ "</html>\n";
						emailService.sendMail(emp.getOfficialEmailId(), "cloudcare", html);
					}

					return "Password Changed Successfully";
				} else {
					throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), "Your current password is incorrect :", "without current password you con't change Password");
				}
			} else {
				throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), "Password must be minimum 8 charecters and should match new password and confirm password :", "");
			}

		} catch (Exception e) {
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
}
