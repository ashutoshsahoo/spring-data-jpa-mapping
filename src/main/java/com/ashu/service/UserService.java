package com.ashu.service;

import java.util.List;

import com.ashu.dto.CreateUser;
import com.ashu.dto.UserDto;
import com.ashu.model.User;

public interface UserService {

    UserDto create(CreateUser createUser);

    UserDto viewById(Long id);

    List<UserDto> getAllUsers();

    void delete(Long id);

    UserDto update(User user);

    User findById(Long id);

}
