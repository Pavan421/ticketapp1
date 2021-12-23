package com.vinnotech.portal.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.service.HRPortalConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtUtil {

	private String secret;
	private Long jwtExpirationInMs;
	private Long refreshExpirationDateInMs;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.expirationInMs}")
	public void setJwtExpirationInMs(Long jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

	@Value("${jwt.refreshExpirationInMs}")
	public void setRefreshExpirationDateInMs(Long refreshExpirationDateInMs) {
		this.refreshExpirationDateInMs = refreshExpirationDateInMs;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		if (roles.contains(new SimpleGrantedAuthority(HRPortalConstants.ROLE_ADMIN))) {
			claims.put("isAdmin", true);
		}
		if (roles.contains(new SimpleGrantedAuthority(HRPortalConstants.ROLE_HR))) {
			claims.put("isHr", true);
		}
		if (roles.contains(new SimpleGrantedAuthority(HRPortalConstants.ROLE_RECRUITER))) {
			claims.put("isRecruiter", true);
		}
		if (roles.contains(new SimpleGrantedAuthority(HRPortalConstants.ROLE_EMPLOYEE))) {
			claims.put("isEmployee", true);
		}
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public boolean validateToken(String authToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		List<SimpleGrantedAuthority> roles = null;
		Boolean isAdmin = claims.get("isAdmin", Boolean.class);
		Boolean isHr = claims.get("isHr", Boolean.class);
		Boolean isRecruiter = claims.get("isRecruiter", Boolean.class);
		Boolean isEmployee = claims.get("isEmployee", Boolean.class);

		if (isAdmin != null && isAdmin) {
			roles = Arrays.asList(new SimpleGrantedAuthority(HRPortalConstants.ROLE_ADMIN));
		}
		if (isHr != null && isHr) {
			roles = Arrays.asList(new SimpleGrantedAuthority(HRPortalConstants.ROLE_HR));
		}
		if (isRecruiter != null && isRecruiter) {
			roles = Arrays.asList(new SimpleGrantedAuthority(HRPortalConstants.ROLE_RECRUITER));
		}
		if (isEmployee != null && isEmployee) {
			roles = Arrays.asList(new SimpleGrantedAuthority(HRPortalConstants.ROLE_EMPLOYEE));
		}
		return roles;
	}
}
