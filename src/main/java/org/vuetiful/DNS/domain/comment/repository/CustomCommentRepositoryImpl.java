package org.vuetiful.DNS.domain.comment.repository;

import com.querydsl.core.BooleanBuilder;
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

    private final QComment qComment = QComment.comment;

    @Override
    public Slice<Comment> findSliceByPost_PostIdAndParentIsNull(int postId, Integer lastCommentId, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qComment.post.postId.eq(postId));
        builder.and(qComment.parent.isNull());

        if (lastCommentId != null) {
            builder.and(qComment.commentId.lt(lastCommentId));
        }

        JPAQuery<Comment> query = queryFactory.selectFrom(qComment)
                .where(builder)
                .orderBy(qComment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        List<Comment> results = query.fetch();

        boolean hasNext = results.size() > pageable.getPageSize();
        if (hasNext) {
            results.remove(results.size() - 1);
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }

    @Override
    public Slice<Comment> findSliceByParent_CommentId(int postId, int parentCommentId, Integer lastCommentId, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qComment.post.postId.eq(postId));
        builder.and(qComment.parent.commentId.eq(parentCommentId));

        if (lastCommentId != null) {
            builder.and(qComment.commentId.gt(lastCommentId));
        }

        JPAQuery<Comment> query = queryFactory.selectFrom(qComment)
                .where(builder)
                .limit(pageable.getPageSize() + 1);

        List<Comment> results = query.fetch();

        boolean hasNext = results.size() > pageable.getPageSize();
        if (hasNext) {
            results.remove(results.size() - 1);
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
