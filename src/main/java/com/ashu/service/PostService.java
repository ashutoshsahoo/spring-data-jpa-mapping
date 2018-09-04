package com.ashu.service;

import java.util.List;

import com.ashu.dto.CreatePost;
import com.ashu.dto.PostDto;
import com.ashu.model.Post;

public interface PostService {

    PostDto create(CreatePost createPost);

    PostDto viewById(String id);

    List<PostDto> viewByUser(Long id);

    List<PostDto> viewAll();

    void delete(Long id);

    PostDto update(Post post); // TODO
}
