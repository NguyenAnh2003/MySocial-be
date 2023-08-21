package com.example.socailapi.post.dto;

import com.example.socailapi.category.dto.EmbeddedCategory;
import com.example.socailapi.user.dto.EmbeddedUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDTO {
    private String id;
    /* Denormalization */
    private EmbeddedUser user;
    private EmbeddedCategory category;
    private String title, description;
    private String imageURL;
    private LocalDateTime timestamp;
}