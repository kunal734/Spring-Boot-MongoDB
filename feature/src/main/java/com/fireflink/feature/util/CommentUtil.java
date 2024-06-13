package com.fireflink.feature.util;

import com.fireflink.feature.models.dto.CommentDto;
import com.fireflink.feature.models.entity.Comment;
import com.fireflink.feature.models.entity.User;

import java.util.Objects;

public class CommentUtil {

    private CommentUtil(){}

    public static Comment initComment(User user, CommentDto commentDto){
        Comment comment = new Comment();
        comment.setCreatedEntity(user.getName(), user.getEmail());
        comment.setReply(commentDto.getReply());
      if(Objects.nonNull(commentDto.getIsPrivate()))
          comment.setPrivate(commentDto.getIsPrivate());
      else
          comment.setPrivate(false);
        return comment;
    }
}
