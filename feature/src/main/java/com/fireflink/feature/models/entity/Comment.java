package com.fireflink.feature.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Base{
    private String commentId;
    private List<Comment> reply;
    private boolean isPrivate;
}
