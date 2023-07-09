package com.ashu.one2many.repository;

import com.ashu.one2many.model.Post;
import com.ashu.one2many.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByUser(User user);
}
