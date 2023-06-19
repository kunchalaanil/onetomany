package com.onetomany.Repository;

import com.onetomany.Dto.CommentDto;
import com.onetomany.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);

}
