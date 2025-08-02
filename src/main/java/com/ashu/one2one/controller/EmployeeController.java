package com.ashu.one2one.controller;

import com.ashu.one2one.dto.CreateEmployee;
import com.ashu.one2one.dto.EmployeeDto;
import com.ashu.one2one.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/employees")
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
