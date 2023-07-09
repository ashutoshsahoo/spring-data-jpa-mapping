package com.ashu.one2one.controller;

import com.ashu.one2one.dto.CreateLocker;
import com.ashu.one2one.dto.LockerDto;
import com.ashu.one2one.service.LockerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/lockers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class LockerController {

    private final LockerService lockerService;

    @PostMapping
    public LockerDto create(@RequestBody CreateLocker createLocker) {
        log.info("Create locker: {}", createLocker);
        return lockerService.create(createLocker);
    }

    @GetMapping("/{id}")
    public LockerDto viewById(@PathVariable(name = "id", required = true) Long id) {
        log.info("View locker-ID : {}", id);
        return lockerService.viewById(id);
    }

    @GetMapping("/employee/{id}")
    public LockerDto getLockersByEmployee(@PathVariable(name = "id", required = true) Long id) {
        log.info("Lockers by employee - ID : {}", id);
        return lockerService.viewByEmployee(id);
    }

    @GetMapping
    public List<LockerDto> viewAll() {
        log.info("All lockers");
        return lockerService.viewAll();
    }

}
