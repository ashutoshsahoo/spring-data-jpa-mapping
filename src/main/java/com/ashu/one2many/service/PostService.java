package com.ashu.one2many.service;

import com.ashu.one2many.dto.CreatePost;
import com.ashu.one2many.dto.PostDto;
import com.ashu.one2many.model.Post;

import java.util.List;

public interface PostService {

    PostDto create(CreatePost createPost);

    PostDto viewById(String id);

    List<PostDto> viewByUser(Long id);

    List<PostDto> viewAll();

    void delete(String id);

    PostDto update(String id,Post postUpdateRequest);
}
