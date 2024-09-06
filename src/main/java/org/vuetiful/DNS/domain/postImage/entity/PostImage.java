package org.vuetiful.DNS.domain.postImage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.vuetiful.DNS.domain.post.entity.Post;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @Column(nullable = false, length = 255)
    private String postImageUrl;
}
