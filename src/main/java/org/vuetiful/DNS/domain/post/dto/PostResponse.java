package org.vuetiful.DNS.domain.post.dto;

import lombok.*;
import org.vuetiful.DNS.domain.post.entity.Post;
import org.vuetiful.DNS.domain.postImage.dto.PostImageResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.vuetiful.DNS.domain.postImage.util.ImageUtil.convertImage;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostResponse {
    private Integer postId;

    private Integer memberId;

    private String nickname;

    private String profileUrl;

    private String postContent;

    private List<PostImageResponse> images;

    private LocalDateTime createdAt;

    public PostResponse(Post post) {
        this.postId = post.getPostId();
        this.memberId = post.getMember().getMemberId();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.nickname = post.getMember().getNickname();
        this.profileUrl = post.getMember().getProfileImageUrl();
        this.images = post.getPostImages().stream()
                .map(postImage -> new PostImageResponse(postImage.getPostImageId(), postImage.getPostImageUrl()))
                .collect(Collectors.toList());
    }
}
