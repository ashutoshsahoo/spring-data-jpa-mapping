package com.ashu.one2one.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ashu.one2one.dto.CreateLocker;
import com.ashu.one2one.dto.LockerDto;
import com.ashu.one2one.exception.LockerNotFoundException;
import com.ashu.one2one.model.Employee;
import com.ashu.one2one.model.Locker;
import com.ashu.one2one.repository.LockerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LockerServiceImpl implements LockerService {

    private final LockerRepository lockerRepo;

    private final EmployeeService employeeService;

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
	return lockers.stream().map(this::mapModelToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
	// TODO Auto-generated method stub
    }

    @Override
    public LockerDto update(Locker locker) {
	// TODO Auto-generated method stub
	return null;
    }

    private Locker findById(Long id) {
	Optional<Locker> isLocker = lockerRepo.findById(id);
	if (!isLocker.isPresent()) {
	    log.info("Locker not found for requested id = {}", id);
	    throw new LockerNotFoundException(id);
	}
	return isLocker.get();
    }

    private LockerDto mapModelToDto(Locker locker) {
	return new LockerDto(locker.getId(), locker.getLockerNo(), locker.getEmployee().getId());
    }
}
