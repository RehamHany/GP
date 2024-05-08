package com.panel.LRapp.Service;

import com.panel.LRapp.Dto.PostsDTO;
import com.panel.LRapp.Entity.Posts;
import com.panel.LRapp.Repo.PostsRepo;
import com.panel.LRapp.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService{
    @Autowired
    private PostsRepo postsRepo;

    @Override
    public PostResponse getPostById(int id) {
        if (postsRepo.findById(id).isEmpty()){
            return new PostResponse("post not found", null);
        }

        else{
            return new PostResponse("post found", postsRepo.findById(id).get());
        }
    }

    @Override
    public List<Posts> findAll() {
        return postsRepo.findAll();
    }

    @Override
    public PostResponse createNewPost(PostsDTO postDto) {
        Posts p=new Posts(postDto.getContent(),0,0);
        return new PostResponse("Post added successfully",postsRepo.save(p));
    }

    @Override
    public PostResponse update(PostsDTO postDto) {
        Optional<Posts> p=postsRepo.findById(postDto.getId());
        p.get().setContent(postDto.getContent());
        p.get().setLike(postDto.getLike());
        p.get().setDisLike(postDto.getDisLike());
        return new PostResponse("updated",postsRepo.save(p.get()));
    }

    @Override
    public String deleteById(int id) {

            if (postsRepo.findById(id).isEmpty()){


            return "post not found to delete";
        }
        postsRepo.deleteById(id);
        return "deleted";
    }

//    @Override
//    public PostResponse updateLike(PostsDTO postDto) {
//        return null;
//    }
//
//    @Override
//    public PostResponse updateDisLike(PostsDTO postDto) {
//        return null;
//    }
}
