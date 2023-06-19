package com.onetomany.Contoller;

import com.onetomany.Dto.PostDto;
import com.onetomany.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postservice;

@PostMapping
    public ResponseEntity<?>createPost(@Valid @RequestBody PostDto postdto, BindingResult result){

   if(result.hasErrors()){
       return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
   }


        PostDto post = postservice.createPost(postdto);

        ResponseEntity<PostDto> pos = new ResponseEntity<>(post, HttpStatus.OK);
        return pos;


    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postservice.getAllPosts();

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }




}





