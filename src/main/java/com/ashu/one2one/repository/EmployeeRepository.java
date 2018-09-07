package com.ashu.one2one.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.one2one.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
