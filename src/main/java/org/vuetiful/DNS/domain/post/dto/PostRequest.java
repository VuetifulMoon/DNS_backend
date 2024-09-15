package org.vuetiful.DNS.domain.post.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import org.vuetiful.DNS.domain.member.entity.Member;
import org.vuetiful.DNS.domain.post.entity.Post;
import org.vuetiful.DNS.domain.postImage.entity.PostImage;

import java.util.List;

import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostRequest {

    private Integer memberId;

    private String postContent;

    private List<MultipartFile> images;


    public Post toEntity() {
        return Post.builder()
                .postContent(postContent)
                .member(Member.builder()
                        .memberId(memberId)
                        .build())
                .postImages(images.stream().map(image ->
                                PostImage.builder()
                                        .postImageUrl("http://localhost:8080/postImage/"+image.getOriginalFilename())
                                        .build()
                        ).collect(Collectors.toList()))
                .build();
    }
}
