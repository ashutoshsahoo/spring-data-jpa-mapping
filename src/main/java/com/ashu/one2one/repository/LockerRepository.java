package com.ashu.one2one.repository;

import com.ashu.one2one.model.Employee;
import com.ashu.one2one.model.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker, Long> {

    Locker findByEmployee(Employee employee);
}
