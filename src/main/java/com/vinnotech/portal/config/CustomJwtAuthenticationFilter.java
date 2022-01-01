package com.vinnotech.portal.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vinnotech.portal.model.HRPortalConstants;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {			
			String jwtToken = jwtUtil.extractJwtFromRequest(request);
			if (StringUtils.hasText(jwtToken) && jwtUtil.validateToken(jwtToken)) {
				UserDetails userDetails = new User(jwtUtil.getUsernameFromToken(jwtToken), "",
						jwtUtil.getRolesFromToken(jwtToken));
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, "", jwtUtil.getRolesFromToken(jwtToken));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("Cannot set Security Context");
			}
		} catch (ExpiredJwtException ex) {
			String isRefreshToken = request.getHeader(HRPortalConstants.IS_REFRESH_TOKEN);
			String requestUrl = request.getRequestURL().toString();
			// Allow for Refresh Token Creation if following conditions are true
			if (isRefreshToken != null && isRefreshToken.equals("true") && requestUrl.contains("refreshtoken")) {
				allowForRequestToken(ex, request);
			} else {
				request.setAttribute("exception", ex);
			}
		} catch (BadCredentialsException ex) {
			request.setAttribute("exception", ex);
		}
		filterChain.doFilter(request, response);
	}

	private void allowForRequestToken(ExpiredJwtException ex, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		request.setAttribute("claims", ex.getClaims());
	}

}
