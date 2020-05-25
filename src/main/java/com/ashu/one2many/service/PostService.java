package com.ashu.one2many.service;

import java.util.List;

import com.ashu.one2many.dto.CreatePost;
import com.ashu.one2many.dto.PostDto;
import com.ashu.one2many.model.Post;

public interface PostService {

	PostDto create(CreatePost createPost);

	PostDto viewById(String id);

	List<PostDto> viewByUser(Long id);

	List<PostDto> viewAll();

	void delete(Long id);

	PostDto update(Post post); // TODO
}
