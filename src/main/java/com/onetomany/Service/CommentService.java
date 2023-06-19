package com.onetomany.Service;

import com.onetomany.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByPostId(long postId);


    CommentDto saveComment(Long postId, CommentDto commentdto);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

    void deleteComment(Long postId, Long commentId);
}
