package com.onetomany.Service;

import com.onetomany.Dto.PostDto;
import com.onetomany.Entity.Post;
import com.onetomany.Repository.PostRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceimpl implements PostService {


    public PostServiceimpl(PostRepository postrepository, ModelMapper maper) {
        this.postrepository = postrepository;
        this.maper = maper;
    }

    private PostRepository postrepository;
    private ModelMapper maper;


    @Override
    public PostDto createPost(PostDto postdto) {
        Post post = mapToEntity(postdto);
        Post re = postrepository.save(post);
        PostDto po = mapToDto(re);
        return po;


    }

    @Override
    public List<PostDto> getAllPosts() {

        List<Post> posts = postrepository.findAll();


        return  posts.stream().map(Post->mapToDto(Post)).collect(Collectors.toList());
    }


















    Post mapToEntity(PostDto postdto){
//
//        Post post=new Post();
//        post.setTitle(postdto.getTitle());
//        post.setContent(postdto.getContent());
//        post.setDescription(postdto.getDescription());
//        return post;

         Post post = maper.map(postdto, Post.class);
         return post;


     }
   PostDto mapToDto(Post post){
//        PostDto postdto=new PostDto();
//        postdto.setId(post.getId());
//        postdto.setTitle(post.getTitle());
//        postdto.setContent(post.getContent());
//        postdto.setDescription(post.getDescription());
//        return postdto;

       PostDto postDto = maper.map(post, PostDto.class);
       return postDto;

   }
}
