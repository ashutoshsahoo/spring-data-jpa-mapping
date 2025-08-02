package com.ashu.one2one.service;

import com.ashu.one2one.dto.CreateLocker;
import com.ashu.one2one.dto.LockerDto;
import com.ashu.one2one.exception.LockerNotFoundException;
import com.ashu.one2one.model.Employee;
import com.ashu.one2one.model.Locker;
import com.ashu.one2one.repository.LockerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LockerServiceImpl implements LockerService {

    private final LockerRepository lockerRepo;

    private final EmployeeService employeeService;

    @Transactional
    @Override
    public LockerDto create(CreateLocker createLocker) {
        Employee employee = employeeService.findById(createLocker.getEmployeeId());
        Locker locker = lockerRepo.save(new Locker(employee, createLocker.getLockerNo()));
        return mapModelToDto(locker);
    }

    @Override
    public LockerDto viewById(Long id) {
        Locker locker = findById(id);
        return mapModelToDto(locker);
    }

    @Override
    public LockerDto viewByEmployee(Long id) {
        // Employee employee = employeeService.findById(id);
        // Locker locker = lockerRepo.findByEmployee(employee);
        // Locker locker = findById(id);
        // return mapModelToDto(locker);
        // Using @MapsId, so can find locker using employee id.
        return viewById(id);
    }

    @Override
    public List<LockerDto> viewAll() {
        List<Locker> lockers = lockerRepo.findAll();
        return lockers.stream().map(this::mapModelToDto).toList();
    }

    @Override
    public void delete(Long id) {
        Locker locker = findById(id);
        this.lockerRepo.delete(locker);
    }

    @Override
    public LockerDto update(Long id, Locker locker) {
        Locker lockerFromRepo = findById(id);
        lockerFromRepo.setLockerNo(locker.getLockerNo());
        return mapModelToDto(this.lockerRepo.saveAndFlush(lockerFromRepo));
    }

    private Locker findById(Long id) {
        return lockerRepo.findById(id).orElseThrow(() -> new LockerNotFoundException(id));
    }

    private LockerDto mapModelToDto(Locker locker) {
        return new LockerDto(locker.getId(), locker.getLockerNo(), locker.getEmployee().getId());
    }
}
