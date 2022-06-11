package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

private PostService postService;
@Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create post API

    /*
    {
    "title":"post1",
    "description":"description1",
    "content":"content1",
    "dateCreated":"2022-06-11T14:45:15",
    "dateModified":"2022-06-11T14:45:15"
}
     */
    @PostMapping("/create")
    public ResponseEntity<PostDTO>createPost(@RequestBody PostDTO postDTO ){
    return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
}
