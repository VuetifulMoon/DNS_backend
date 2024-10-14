package org.vuetiful.DNS.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vuetiful.DNS.domain.post.dto.PostRequest;
import org.vuetiful.DNS.domain.post.service.PostService;

import java.io.File;
import java.util.List;

import java.io.IOException;

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
    public ResponseEntity<?> postPost(@RequestBody PostRequest postRequest) throws IOException {
        for(int i=0; i<postRequest.getImages().size(); i++) {
            String fileName = postRequest.getImages().get(i).getOriginalFilename();
            String fullPathName = "/Users/jeong-yong-an/Desktop/DNS_backend/src/main/resources/static/postImage/" + fileName;
            postRequest.getImages().get(i).transferTo(new File(fullPathName));
        }

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
