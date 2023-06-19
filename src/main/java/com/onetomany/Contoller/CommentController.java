package com.onetomany.Contoller;

import com.onetomany.Dto.CommentDto;
import com.onetomany.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    @Autowired
    private CommentService commentservice;

    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommentDto> saveComment(@PathVariable Long postId, @RequestBody CommentDto commentdto) {

        CommentDto savedone = commentservice.saveComment(postId, commentdto);

        return new ResponseEntity<>(savedone, HttpStatus.CREATED);


    }

    @GetMapping("/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId) {

        return commentservice.getCommentsByPostId(postId);


    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value =
            "postId") Long postId,
                                                     @PathVariable(value = "id") Long commentId) {

        CommentDto commentDto = commentservice.getCommentById(postId,
                commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentservice.updateComment(postId,
                commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                               @PathVariable(value = "id") Long commentId){


        commentservice.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment delete  successfully", HttpStatus.OK);



}
}
