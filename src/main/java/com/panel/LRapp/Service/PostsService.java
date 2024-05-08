package com.panel.LRapp.Service;

import com.panel.LRapp.Dto.PostsDTO;
import com.panel.LRapp.Entity.Posts;
import com.panel.LRapp.response.PostResponse;

import java.util.List;

public interface PostsService {
    PostResponse getPostById(int id);
    List<Posts> findAll();

    PostResponse createNewPost(PostsDTO postDto);

    PostResponse update(PostsDTO postDto);

    String deleteById(int id);
//    PostResponse updateLike(PostsDTO postDto);
//    PostResponse updateDisLike(PostsDTO postDto);
}
