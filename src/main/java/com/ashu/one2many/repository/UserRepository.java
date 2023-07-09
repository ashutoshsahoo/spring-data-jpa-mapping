package com.ashu.one2many.repository;

import com.ashu.one2many.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
