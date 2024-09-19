package org.vuetiful.DNS.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentRequest {

    private int memberId;
    private String commentContent;
}
