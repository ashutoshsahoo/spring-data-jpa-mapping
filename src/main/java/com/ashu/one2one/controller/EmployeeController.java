package com.ashu.one2one.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashu.one2one.dto.CreateEmployee;
import com.ashu.one2one.dto.EmployeeDto;
import com.ashu.one2one.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/employees", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDto create(@RequestBody CreateEmployee createEmployee) {
	log.info("Create employee : {}", createEmployee);
	return employeeService.create(createEmployee);
    }

    @GetMapping("/{id}")
    public EmployeeDto view(@PathVariable(name = "id", required = true) Long id) {
	log.info("View employee- ID: {}", id);
	return employeeService.viewById(id);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
	log.info("All Employees");
	return employeeService.getAll();
    }
}
