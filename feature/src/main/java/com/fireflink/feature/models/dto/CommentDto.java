package com.fireflink.feature.models.dto;

import com.fireflink.feature.models.entity.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NotNull
    private List<Comment> reply;
    private Boolean isPrivate;
}
