package com.ashu.one2many.service;

import com.ashu.one2many.dto.CreateUser;
import com.ashu.one2many.dto.UserDto;
import com.ashu.one2many.exception.UserDoesNotExistException;
import com.ashu.one2many.model.User;
import com.ashu.one2many.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Transactional
    @Override
    public UserDto create(CreateUser createUser) {
        User user = userRepo.saveAndFlush(new User(createUser.getName()));
        return mapModelToDto(user);
    }

    @Override
    public UserDto viewById(Long id) {
        User user = findById(id);
        return mapModelToDto(user);
    }


    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserDoesNotExistException(id));
    }


    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(this::mapModelToDto).toList();
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        this.userRepo.delete(user);
    }

    @Override
    public UserDto update(Long id, User userUpdateRequest) {
        User user = findById(id);
        user.setName(userUpdateRequest.getName());
        return mapModelToDto(this.userRepo.saveAndFlush(user));
    }

    private UserDto mapModelToDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}
