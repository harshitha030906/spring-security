package com.harshitha.springsecurity.springsecurity.service;


import com.harshitha.springsecurity.springsecurity.dto.PostDTO;
import com.harshitha.springsecurity.springsecurity.entities.PostEntity;
import com.harshitha.springsecurity.springsecurity.exceptions.ResourceNotFoundException;
import com.harshitha.springsecurity.springsecurity.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceimpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceimpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDTO createPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostDTO> posts = postEntities.stream()
                                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                                .collect(Collectors.toList());
        return posts;
    }

    @Override
    public PostDTO getPostById(@RequestParam Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The post with Id is not found"));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePostById(@RequestParam Long id, PostDTO inputPost) {
        PostEntity olderPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        inputPost.setId(id);
        modelMapper.map(inputPost, olderPost);
        PostEntity postEntity = postRepository.save(olderPost);
        return modelMapper.map(postEntity, PostDTO.class);
    }

}
