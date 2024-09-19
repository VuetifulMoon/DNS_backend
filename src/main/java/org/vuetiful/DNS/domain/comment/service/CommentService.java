package org.vuetiful.DNS.domain.comment.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.vuetiful.DNS.domain.comment.dto.CommentRequest;
import org.vuetiful.DNS.domain.comment.dto.CommentResponse;
import org.vuetiful.DNS.domain.comment.dto.SliceResponse;
import org.vuetiful.DNS.domain.comment.entity.Comment;
import org.vuetiful.DNS.domain.comment.repository.CommentRepository;
import org.vuetiful.DNS.domain.comment.repository.CustomCommentRepository;
import org.vuetiful.DNS.domain.member.entity.Member;
import org.vuetiful.DNS.domain.member.repository.MemberRepository;
import org.vuetiful.DNS.domain.post.entity.Post;
import org.vuetiful.DNS.domain.post.repository.PostRepository;
import org.vuetiful.DNS.global.exception.ErrorCode;
import org.vuetiful.DNS.global.exception.GlobalException;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final CustomCommentRepository customCommentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 사용자 유효성 검증
    private Member validateMember(int memberId) {
        try { return memberRepository.getReferenceById(memberId); }
        catch (EntityNotFoundException e) { throw new GlobalException(ErrorCode.MEMBER_NOT_FOUND); }
    }

    // 게시글 유효성 검증
    private Post validatePost(int postId) {
        try { return postRepository.getReferenceById(postId); }
        catch (EntityNotFoundException e) { throw new GlobalException(ErrorCode.POST_NOT_FOUND); }
    }

    private void validatePostId(int postId) {
        if (!postRepository.existsById(postId)) { throw new GlobalException(ErrorCode.POST_NOT_FOUND); }
    }

    // 댓글 유효성 검증
    private Comment validateComment(int postId, int commentId, int memberId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new GlobalException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getPost().getPostId().equals(postId)) { throw new GlobalException(ErrorCode.INVALID_PARAMETER); }

        if (!comment.getMember().getMemberId().equals(memberId)) { throw new GlobalException(ErrorCode.FORBIDDEN); }

        return comment;
    }

    // 최상위 댓글 조회
    public SliceResponse<CommentResponse> readTopLevelComments(int postId, Integer lastCommentId , int size) {
        validatePostId(postId);

        PageRequest pageRequest = PageRequest.of(0, size, Sort.by("createdAt").descending());

        Slice<Comment> commentsSlice = customCommentRepository.findSliceByPost_PostIdAndParentIsNull(postId, lastCommentId, pageRequest);

        if (commentsSlice.isEmpty() && lastCommentId != null) {
            throw new GlobalException(ErrorCode.OUT_OF_RANGE);
        }

        return new SliceResponse<>(commentsSlice.map(CommentResponse::fromEntity));
    }

    // 대댓글 조회
    public Page<CommentResponse> readNestedComments(int postId, int parentCommentId, int page, int size) {
        validatePostId(postId);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt"));

        Page<Comment> nestedComments = commentRepository.findByParent_CommentId(parentCommentId, pageRequest);

        if (nestedComments.isEmpty() && page > 0) { throw new GlobalException(ErrorCode.OUT_OF_RANGE); }

        return nestedComments.map(CommentResponse::fromEntity);
    }

    // 댓글 등록
    public void createComment(int postId, CommentRequest request, Integer parentCommentId) {
        Post post = validatePost(postId);
        Member member = validateMember(request.getMemberId());

        Comment parentComment = parentCommentId != null ? commentRepository.findById(parentCommentId)
                .map(comment -> {
                    comment.incrementCount();
                    return comment;
                })
                .orElseThrow(() -> new GlobalException(ErrorCode.PARENT_COMMENT_NOT_FOUND)) : null;

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .parent(parentComment)
                .commentContent(request.getCommentContent())
                .build();

        commentRepository.save(comment);
    }

    // 댓글 수정
    public void updateComment(int postId, int commentId, CommentRequest request) {
        Comment comment = validateComment(postId, commentId, request.getMemberId());

        comment.updateComment(request.getCommentContent());

        commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(int postId, int commentId, int memberId) {
        Comment comment = validateComment(postId, commentId, memberId);

        if (comment.getParent() != null) {
            comment.getParent().decrementCount();
        }

        comment.deleteComment();

        commentRepository.save(comment);
    }
}
