package com.ashu.one2many.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashu.one2many.dto.CreatePost;
import com.ashu.one2many.dto.PostDto;
import com.ashu.one2many.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class PostController {

	private final PostService postService;

	@PostMapping
	public PostDto create(@RequestBody CreatePost createPost) {
		log.info("Create post: {}", createPost);
		return postService.create(createPost);
	}

	@GetMapping("/{id}")
	public PostDto viewById(@PathVariable(name = "id", required = true) String id) {
		log.info("View post-ID : {}", id);
		return postService.viewById(id);
	}

	@GetMapping("/user/{id}")
	public List<PostDto> getPostsByUser(@PathVariable(name = "id", required = true) Long id) {
		log.info("Posts by User - ID : {}", id);
		return postService.viewByUser(id);
	}

	@GetMapping
	public List<PostDto> viewAll() {
		log.info("All Posts ");
		return postService.viewAll();
	}

}
