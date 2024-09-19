package org.vuetiful.DNS.domain.comment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.vuetiful.DNS.domain.comment.entity.Comment;

public interface CustomCommentRepository {

    // 최상위 댓글 조회
    Slice<Comment> findSliceByPost_PostIdAndParentIsNull(int page, Integer commentId, Pageable pageable);
}
