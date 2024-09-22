package org.vuetiful.DNS.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vuetiful.DNS.domain.comment.dto.CommentRequest;
import org.vuetiful.DNS.domain.comment.dto.CommentResponse;
import org.vuetiful.DNS.domain.comment.dto.SliceResponse;
import org.vuetiful.DNS.domain.comment.service.CommentService;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 조회
    @GetMapping
    public SliceResponse<CommentResponse> getComments(@PathVariable int postId,
                                                      @RequestParam(required = false) Integer lastCommentId,
                                                      @RequestParam(defaultValue = "10") int size) {
       return commentService.readTopLevelComments(postId, lastCommentId, size);
    }

    // 대댓글 조회
    @GetMapping("/{commentId}")
    public SliceResponse<CommentResponse> getComments(@PathVariable int postId,
                                             @PathVariable int commentId,
                                             @RequestParam(required = false) Integer lastCommentId,
                                             @RequestParam(defaultValue = "10") int size) {
        return commentService.readNestedComments(postId, commentId, lastCommentId, size);
    }

    // 댓글 등록
    @PostMapping
    public ResponseEntity<String> postComment(@PathVariable int postId,
                                              @RequestBody CommentRequest request,
                                              @RequestParam(required = false) Integer parentCommentId) {
        commentService.createComment(postId, request, parentCommentId);

        return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 등록되었습니다.");
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<String> patchComment(@PathVariable int postId,
                                               @PathVariable int commentId, @RequestBody CommentRequest request) {
        commentService.updateComment(postId, commentId, request);

        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int postId, @PathVariable int commentId, @RequestParam int memberId) {
        commentService.deleteComment(postId, commentId, memberId);

        return ResponseEntity.noContent().build();
    }
}
