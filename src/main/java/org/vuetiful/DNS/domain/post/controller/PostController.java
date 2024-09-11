package org.vuetiful.DNS.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vuetiful.DNS.domain.post.dto.PostRequest;
import org.vuetiful.DNS.domain.post.service.PostService;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getPost() {
        return ResponseEntity.ok().body(postService.readPost());
    }

    @PostMapping
    public ResponseEntity<?> postPost(@RequestBody PostRequest postRequest) {
        postService.createPost(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> patchPost() {
        return null;
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@RequestParam int postId) {
        postService.removePost(postId);
        return null;
    }
}
