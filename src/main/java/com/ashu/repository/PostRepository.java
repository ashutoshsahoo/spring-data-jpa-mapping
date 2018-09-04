package com.ashu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.model.Post;
import com.ashu.model.User;

public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findByUser(User user);
}
