package com.vinnotech.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinnotech.portal.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
