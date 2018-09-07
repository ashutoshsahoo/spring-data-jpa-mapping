package com.ashu.one2one.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.one2one.model.Employee;
import com.ashu.one2one.model.Locker;

public interface LockerRepository extends JpaRepository<Locker, Long> {

    Locker findByEmployee(Employee employee);
}
