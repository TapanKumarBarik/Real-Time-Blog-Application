package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    List<PostDTO> getAllPosts();
    PostDTO getPostByID(long id);
    PostDTO updatePost(PostDTO postDTO,long id);
    void deletePostById(long id);
}
