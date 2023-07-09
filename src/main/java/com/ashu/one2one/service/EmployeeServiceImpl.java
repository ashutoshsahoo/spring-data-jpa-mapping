package com.ashu.one2one.service;

import com.ashu.one2one.dto.CreateEmployee;
import com.ashu.one2one.dto.EmployeeDto;
import com.ashu.one2one.exception.EmployeeNotFoundException;
import com.ashu.one2one.model.Employee;
import com.ashu.one2one.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository empRepo;

    @Transactional
    @Override
    public EmployeeDto viewById(Long id) {
        Employee employee = findById(id);
        return mapModelToDto(employee);
    }

    @Transactional
    @Override
    public List<EmployeeDto> getAll() {
        return empRepo.findAll().stream().map(this::mapModelToDto).toList();
    }

    @Transactional
    @Override
    public EmployeeDto create(CreateEmployee createEmployee) {
        Employee employee = empRepo.saveAndFlush(new Employee(createEmployee.getName()));
        return mapModelToDto(employee);
    }

    @Transactional
    @Override
    public Employee findById(Long id) {
        return empRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private EmployeeDto mapModelToDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName());
    }

}
