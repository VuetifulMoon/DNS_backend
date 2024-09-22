package org.vuetiful.DNS.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import org.vuetiful.DNS.domain.comment.entity.Comment;
import org.vuetiful.DNS.domain.member.dto.MemberProfile;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {
    private int commentId;
    private int postId;
    private MemberProfile memberProfile;
    private String commentContent;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer parentCommentId;
    private int childCommentCount;


    public static CommentResponse fromEntity(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .postId(comment.getPost().getPostId())
                .memberProfile(MemberProfile.fromEntity(comment.getMember()))
                .commentContent(comment.getCommentContent())
                .isDeleted(comment.isDeleted())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .parentCommentId(comment.getParent() != null ? comment.getParent().getCommentId() : null)
                .childCommentCount(comment.getChildCommentCount())
                .build();
    }
}
