package com.vinnotech.portal.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.model.UserReg;
import com.vinnotech.portal.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;

		UserReg userReg = userRepository.findByUsername(username);

		if (userReg != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(userReg.getRole()));
			return new User(userReg.getUsername(), userReg.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);
	}

}
