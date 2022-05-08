package com.ashu.one2many.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.one2many.model.Post;
import com.ashu.one2many.model.User;

public interface PostRepository extends JpaRepository<Post, UUID> {

	List<Post> findByUser(User user);
}
