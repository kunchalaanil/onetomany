package com.onetomany.Service;

import com.onetomany.Dto.CommentDto;
import com.onetomany.Entity.Comment;
import com.onetomany.Entity.Post;
import com.onetomany.Exception.Bloag;
import com.onetomany.Exception.ResourceNotFoundException;
import com.onetomany.Repository.CommentRepository;
import com.onetomany.Repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceimpl implements CommentService{
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    public CommentServiceimpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());


    }

    @Override
    public CommentDto saveComment(Long postId, CommentDto commentdto) {

        Post ps = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "no record found", postId));
        Comment comment = mapToEntity(commentdto);
        comment.setPost(ps);
        Comment en = commentRepository.save(comment);
        CommentDto comm = mapToDto(en);
        return comm;
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {


        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));


        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () ->new ResourceNotFoundException("Comment", "id", commentId));


        if(!comment.getPost().getId().equals(post.getId())){


            throw new Bloag(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }






   return mapToDto(comment);

        }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {


        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));


        if(!comment.getPost().getId().equals(post.getId())){
            throw new Bloag(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {


        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post is not therir","id",postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
        new ResourceNotFoundException("Comment not therir", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new Bloag(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
        commentRepository.delete(comment);

    }


    Comment mapToEntity(CommentDto commentdto){
        Comment comment=new Comment();
        comment.setName(commentdto.getName());
        comment.setEmail(commentdto.getEmail());
        comment.setBody(commentdto.getBody());
        return comment;

    }

     CommentDto mapToDto(Comment comment){
        CommentDto dto=new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;

     }


}
