package com.ashu.one2many.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.one2many.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
