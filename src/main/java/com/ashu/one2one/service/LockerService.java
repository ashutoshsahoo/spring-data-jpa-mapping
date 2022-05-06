package com.ashu.one2one.service;

import com.ashu.one2one.dto.CreateLocker;
import com.ashu.one2one.dto.LockerDto;
import com.ashu.one2one.model.Locker;

import java.util.List;

public interface LockerService {

    LockerDto create(CreateLocker createLocker);

    LockerDto viewByEmployee(Long id);

    List<LockerDto> viewAll();

    void delete(Long id);

    LockerDto update(Long id,Locker locker);

    LockerDto viewById(Long id);
}
