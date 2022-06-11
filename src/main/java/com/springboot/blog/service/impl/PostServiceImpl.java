package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        postDTO.setDateCreated(new Date());
        postDTO.setDateModified(new Date());
        Post newPost =postRepository.save(mapToPost(postDTO));
        //convert entity to dto
        return mapToPostDTO(newPost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post>post=postRepository.findAll();
        List<PostDTO>newPost=new ArrayList<>();
        for (Post value : post) {
            newPost.add(mapToPostDTO(value));
        }
        return newPost;
    }

    @Override
    public PostDTO getPostByID(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        return mapToPostDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));

        post.setContent(postDTO.getContent());
        post.setDateModified(new Date());
        post.setDescription(postDTO.getDescription());
        post.setTitle(postDTO.getTitle());
        Post newPost =postRepository.save(post);
        return mapToPostDTO(newPost);
    }

    //helper
    //Convert post to postDTO
    private PostDTO mapToPostDTO(Post newPost){
        PostDTO newPostDTO=new PostDTO();
        newPostDTO.setContent(newPost.getContent());
        newPostDTO.setDateCreated(newPost.getDateCreated());
        newPostDTO.setDateModified(newPost.getDateModified());
        newPostDTO.setDescription(newPost.getDescription());
        newPostDTO.setId(newPost.getId());
        newPostDTO.setTitle(newPost.getTitle());
        return newPostDTO;
    }
    //helper
    //Convert postDTO to post
    private Post mapToPost(PostDTO postDTO){
        Post post=new Post();
        post.setContent(postDTO.getContent());
        post.setDateCreated(postDTO.getDateCreated());
        post.setDateModified(postDTO.getDateModified());
        post.setDescription(postDTO.getDescription());
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        return post;
    }
}
