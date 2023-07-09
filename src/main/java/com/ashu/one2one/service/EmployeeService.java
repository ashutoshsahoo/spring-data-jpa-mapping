package com.ashu.one2one.service;

import com.ashu.one2one.dto.CreateEmployee;
import com.ashu.one2one.dto.EmployeeDto;
import com.ashu.one2one.model.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDto viewById(Long id);

    List<EmployeeDto> getAll();

    Employee findById(Long id);

    EmployeeDto create(CreateEmployee createEmployee);

}
