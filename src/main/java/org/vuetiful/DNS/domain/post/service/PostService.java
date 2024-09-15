package org.vuetiful.DNS.domain.post.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    public Slice<PostResponse> readPost(int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return postRepository.findSliceBy(pageRequest).map(post -> new PostResponse(post));
    }

    public void createPost(PostRequest postRequest) {
        postRepository.save(postRequest.toEntity());
    }

    public void modifyPost(int postId, PostRequest postRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + postId));

        post.update(postRequest);

        postRepository.save(post);
    }

    public void removePost(int postId) {
        postRepository.deleteById(postId);
    }
}
