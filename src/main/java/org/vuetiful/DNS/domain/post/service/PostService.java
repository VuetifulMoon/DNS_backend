package org.vuetiful.DNS.domain.post.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vuetiful.DNS.domain.post.dto.PostRequest;
import org.vuetiful.DNS.domain.post.dto.PostResponse;
import org.vuetiful.DNS.domain.post.entity.Post;
import org.vuetiful.DNS.domain.post.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponse> readPost() {
        return postRepository.findAll().stream().map(post -> new PostResponse(post)).toList();
    }

    public void createPost(PostRequest postRequest) {
        postRepository.save(postRequest.toEntity());
    }

    public Integer modifyPost() {
        return null;
    }

    public void removePost(int postId) {
        postRepository.deleteById(postId);
    }
}
