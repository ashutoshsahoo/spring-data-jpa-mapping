package com.ashu.one2many.controller;

import com.ashu.one2many.dto.CreateUser;
import com.ashu.one2many.dto.UserDto;
import com.ashu.one2many.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody CreateUser createUser) {
        log.info("Create user : {}", createUser);
        return userService.create(createUser);
    }

    @GetMapping("/{id}")
    public UserDto view(@PathVariable(name = "id") Long id) {
        log.info("View user- ID: {}", id);
        return userService.viewById(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        log.info("All Users");
        return userService.getAllUsers();
    }
}
