package com.ashu.one2many.service;

import com.ashu.one2many.dto.CreateUser;
import com.ashu.one2many.dto.UserDto;
import com.ashu.one2many.model.User;

import java.util.List;

public interface UserService {

    UserDto create(CreateUser createUser);

    UserDto viewById(Long id);

    List<UserDto> getAllUsers();

    void delete(Long id);

    UserDto update(Long id, User user);

    User findById(Long id);

}
