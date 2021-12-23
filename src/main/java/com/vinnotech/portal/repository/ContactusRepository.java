package com.vinnotech.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinnotech.portal.model.ContactUS;

@Repository
public interface ContactusRepository extends JpaRepository<ContactUS, Long>{

}


