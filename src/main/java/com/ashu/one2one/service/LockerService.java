package com.ashu.one2one.service;

import java.util.List;

import com.ashu.one2one.dto.CreateLocker;
import com.ashu.one2one.dto.LockerDto;
import com.ashu.one2one.model.Locker;

public interface LockerService {

	LockerDto create(CreateLocker createLocker);

	LockerDto viewByEmployee(Long id);

	List<LockerDto> viewAll();

	void delete(Long id);

	LockerDto update(Locker locker); // TODO

	LockerDto viewById(Long id);
}
