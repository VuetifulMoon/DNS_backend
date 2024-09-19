package org.vuetiful.DNS.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.vuetiful.DNS.domain.comment.entity.Comment;
import org.vuetiful.DNS.domain.comment.entity.QComment;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Comment> findSliceByPost_PostIdAndParentIsNull(int postId, Integer lastCommentId, Pageable pageable) {
        QComment qComment = QComment.comment;

        JPAQuery<Comment> query = queryFactory.selectFrom(qComment)
                .where(qComment.post.postId.eq(postId)
                        .and(qComment.parent.isNull()))
                .orderBy(qComment.createdAt.desc());

        if (lastCommentId != null) {
            query.where(qComment.commentId.lt(lastCommentId));
        }

        List<Comment> results = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = results.size() > pageable.getPageSize();
        if (hasNext) {
            results.remove(results.size() - 1);
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
