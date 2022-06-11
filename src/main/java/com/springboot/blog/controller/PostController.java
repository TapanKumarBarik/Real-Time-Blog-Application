package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    "content":"content1"
}
     */
    @PostMapping("/create")
    public ResponseEntity<PostDTO>createPost(@RequestBody PostDTO postDTO ){
    return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }


    //Get All Posts

    @GetMapping
    public ResponseEntity<List<PostDTO>>getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    //Get Post by ID

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO>getPostByID(@PathVariable(name="id")long id){
        return ResponseEntity.ok(postService.getPostByID(id));
    }

    //Update Post by ID

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO>UpdatePost( @RequestBody PostDTO postDTO, @PathVariable(name="id")long id){
        return ResponseEntity.ok(postService.updatePost(postDTO,id));
    }

    //delete post

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePostById( @PathVariable(name="id")long id){

        postService.deletePostById(id);
        return ResponseEntity.ok("Successfully deleted the post with "+id);
    }
}
