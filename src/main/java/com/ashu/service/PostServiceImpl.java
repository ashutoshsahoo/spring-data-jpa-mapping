package com.ashu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashu.dto.CreatePost;
import com.ashu.dto.PostDto;
import com.ashu.exception.PostDoesNotExistException;
import com.ashu.model.Post;
import com.ashu.model.User;
import com.ashu.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;

    private final UserService userService;

    @Transactional
    @Override
    public PostDto create(CreatePost createPost) {
	User user = userService.findById(createPost.getUserId());
	Post post = new Post(user, createPost.getMessage());
	postRepo.saveAndFlush(post);
	return mapModelToDto(post);
    }

    @Transactional
    @Override
    public PostDto viewById(String id) {
	Optional<Post> post = postRepo.findById(id);
	if (!post.isPresent()) {
	    log.info("Post not found for requested id = {}", id);
	    throw new PostDoesNotExistException(id);
	}
	return mapModelToDto(post.get());
    }

    @Transactional
    @Override
    public List<PostDto> viewByUser(Long id) {
	User user = userService.findById(id);
	List<Post> posts = postRepo.findByUser(user);
	return posts.stream().map(this::mapModelToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<PostDto> viewAll() {
	List<Post> posts = postRepo.findAll();
	return posts.stream().map(this::mapModelToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
	// TODO Auto-generated method stub

    }

    @Override
    public PostDto update(Post post) {
	// TODO Auto-generated method stub
	return null;
    }

    private PostDto mapModelToDto(Post post) {
	return new PostDto(post.getId(), post.getPostTime(), post.getMessage(), post.getUser().getId());
    }
}
