package com.harshitha.springsecurity.springsecurity.controllers;

import com.harshitha.springsecurity.springsecurity.dto.PostDTO;
import com.harshitha.springsecurity.springsecurity.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO inputPost){
        PostDTO postDTO = postService.createPost(inputPost);
        return postDTO;
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public PostDTO updatePostById(@RequestBody PostDTO inputPost,@PathVariable Long id){
        return postService.updatePostById(id, inputPost);
    }

}
