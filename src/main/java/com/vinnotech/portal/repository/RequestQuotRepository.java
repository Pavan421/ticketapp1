package com.vinnotech.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinnotech.portal.model.RequestQuot;

@Repository
public interface RequestQuotRepository extends JpaRepository<RequestQuot, Long> {

}
