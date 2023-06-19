package com.onetomany.Service;

import com.onetomany.Dto.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postdto);


    List<PostDto> getAllPosts();


}
