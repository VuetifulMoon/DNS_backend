package org.vuetiful.DNS.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.vuetiful.DNS.domain.BaseEntity;
import org.vuetiful.DNS.domain.member.entity.Member;
import org.vuetiful.DNS.domain.post.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    // 순환 참조 - 부모
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nestedCommentId")
    private Comment parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @ToString.Exclude
    private List<Comment> child;

    @Column(nullable = false)
    private int childCommentCount = 0;

    @Column(nullable = false, length = 300)
    private String commentContent;

    @Column
    private LocalDateTime deletedAt;

    @Column
    private boolean isDeleted;


    public void updateComment(String commentContent) {
        this.commentContent = commentContent;
    }

    public void deleteComment() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    public void incrementCount() {
        this.childCommentCount++;
    }

    public void decrementCount() {
        if (this.childCommentCount > 0) {
            this.childCommentCount--;
        }
    }
}
