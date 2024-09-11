package org.vuetiful.DNS.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import org.vuetiful.DNS.domain.BaseEntity;
import org.vuetiful.DNS.domain.member.entity.Member;
import org.vuetiful.DNS.domain.postImage.entity.PostImage;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Column(length = 1500)
    private String postContent;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PostImage> postImages;
}
