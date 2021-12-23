package com.vinnotech.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinnotech.portal.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "select * from employee where is_employee_deleted =?1", nativeQuery = true)
	Page<Employee> findByEmpWithDeletedStatus(boolean isEmpDeleted, Pageable pageable);

}
