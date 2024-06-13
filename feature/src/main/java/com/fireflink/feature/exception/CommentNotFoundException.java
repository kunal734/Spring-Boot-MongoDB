package com.fireflink.feature.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentNotFoundException extends  RuntimeException{
    private String message=" Comment Not Found Exception";

    @Override
    public String getMessage() {
        return message;
    }
}
