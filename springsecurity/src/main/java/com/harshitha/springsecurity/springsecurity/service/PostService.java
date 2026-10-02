package com.harshitha.springsecurity.springsecurity.service;


import com.harshitha.springsecurity.springsecurity.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO inputPost);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long id);

    PostDTO updatePostById(Long id, PostDTO inputPost);
}
