package com.ashu.one2many.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashu.one2many.dto.CreateUser;
import com.ashu.one2many.dto.UserDto;
import com.ashu.one2many.exception.UserDoesNotExistException;
import com.ashu.one2many.model.User;
import com.ashu.one2many.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    @Transactional
    @Override
    public UserDto viewById(Long id) {
	User user = findById(id);
	return mapModelToDto(user);
    }

    @Transactional
    @Override
    public User findById(Long id) {
	Optional<User> user = userRepo.findById(id);
	if (!user.isPresent()) {
	    log.info("User not found for requested id = {}", id);
	    throw new UserDoesNotExistException(id);
	}
	return user.get();
    }

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
	return userRepo.findAll().stream().map(this::mapModelToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
	// TODO Auto-generated method stub

    }

    @Override
    public UserDto update(User user) {
	// TODO Auto-generated method stub
	return null;
    }

    private UserDto mapModelToDto(User user) {
	return new UserDto(user.getId(), user.getName());
    }
}
