package com.vinnotech.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinnotech.portal.model.UserReg;

@Repository
public interface UserRepository extends JpaRepository<UserReg, Long> {
	UserReg findByUsername(String username);
}