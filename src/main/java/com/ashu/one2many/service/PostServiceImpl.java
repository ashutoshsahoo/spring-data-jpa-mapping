package com.ashu.one2many.service;

import com.ashu.one2many.dto.CreatePost;
import com.ashu.one2many.dto.PostDto;
import com.ashu.one2many.exception.PostDoesNotExistException;
import com.ashu.one2many.mapper.PostMapper;
import com.ashu.one2many.model.Post;
import com.ashu.one2many.model.User;
import com.ashu.one2many.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;

    private final UserService userService;

    private final PostMapper postMapper;

    @Transactional
    @Override
    public PostDto create(CreatePost createPost) {
        User user = userService.findById(createPost.getUserId());
        Post post = new Post(user, createPost.getMessage());
        postRepo.saveAndFlush(post);
        return postMapper.postToPostDto(post);
    }

    @Transactional
    @Override
    public PostDto viewById(String id) {
        Post post = findById(id);
        return postMapper.postToPostDto(post);
    }


    @Transactional
    @Override
    public List<PostDto> viewByUser(Long id) {
        User user = userService.findById(id);
        List<Post> posts = postRepo.findByUser(user);
        return posts.stream().map(postMapper::postToPostDto).toList();
    }

    @Transactional
    @Override
    public List<PostDto> viewAll() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(postMapper::postToPostDto).toList();
    }

    @Transactional
    @Override
    public void delete(String id) {
        Post post = findById(id);
        this.postRepo.delete(post);
    }

    @Transactional
    @Override
    public PostDto update(String id, Post postUpdateRequest) {
        Post post = findById(id);
        post.setMessage(postUpdateRequest.getMessage());
        return postMapper.postToPostDto(postRepo.saveAndFlush(post));
    }

    private Post findById(String id) {
        return postRepo.findById(UUID.fromString(id)).orElseThrow(() -> new PostDoesNotExistException(id));
    }


}
