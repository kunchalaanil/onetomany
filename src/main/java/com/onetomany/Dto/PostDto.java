package com.onetomany.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
@Data
public class PostDto {
    private Long id;

    private String content;
    private String description;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Content must be between 1 and 100 characters")
    private String title;
    private List<CommentDto> comments;
}
