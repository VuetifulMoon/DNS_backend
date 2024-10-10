package org.vuetiful.DNS.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vuetiful.DNS.domain.post.dto.PostRequest;
import org.vuetiful.DNS.domain.post.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getPost(@RequestParam int page) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(postService.readPost(page));
    }

    @PostMapping
    public ResponseEntity<?> postPost(
            @RequestParam Integer memberId,
            @RequestParam String postContent,
            @RequestPart List<MultipartFile> images) {

        PostRequest postRequest = PostRequest.builder()
                .memberId(memberId)
                .postContent(postContent)
                .images(images)
                .build();

        postService.createPost(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PatchMapping("/{postId}")
    public ResponseEntity<?> patchPost(@PathVariable int postId, PostRequest postRequest) {
        postService.modifyPost(postId, postRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        postService.removePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
