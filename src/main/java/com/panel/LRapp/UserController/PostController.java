package com.panel.LRapp.UserController;


import com.panel.LRapp.Dto.PostsDTO;
import com.panel.LRapp.Entity.Posts;
import com.panel.LRapp.Service.PostsService;
import com.panel.LRapp.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/posts")
public class PostController {
    @Autowired
    private PostsService postsService;

    @PostMapping("/create")
    public PostResponse createNewPost(@RequestBody PostsDTO postDto)  {
        return postsService.createNewPost(postDto);
    }

    @PutMapping("/update")
    public PostResponse updatePost(@RequestBody PostsDTO postDto){
        return postsService.update(postDto);
    }

    @DeleteMapping("/delete")
    public String deletePost(@RequestParam("id") int id){
        return postsService.deleteById(id);
    }

    @GetMapping("/getPost")
    public PostResponse getPostById(@RequestParam("id") int id){
        return postsService.getPostById(id);
    }

    @GetMapping("/getAllPosts")
    public List<Posts> findAllPosts(){
        return postsService.findAll();
    }
}
