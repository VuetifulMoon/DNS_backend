package org.vuetiful.DNS.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.vuetiful.DNS.domain.post.dto.PostRequest;
import org.vuetiful.DNS.domain.post.dto.PostResponse;
import org.vuetiful.DNS.domain.post.entity.Post;
import org.vuetiful.DNS.domain.post.repository.PostRepository;
import org.vuetiful.DNS.domain.postImage.entity.PostImage;
import org.vuetiful.DNS.domain.postImage.repository.PostImageRepository;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    public Slice<PostResponse> readPost(int lastIndex) {
        PageRequest pageRequest = PageRequest.of(lastIndex, 10);
        return postRepository.findSliceBy(pageRequest).map(post -> new PostResponse(post));
    }

    public void createPost(PostRequest postRequest) throws IOException {
        Post post = postRepository.save(postRequest.toEntity());
        postRequest.getImages().stream().forEach(image ->
                postImageRepository.save(PostImage.builder()
                                .post(post)
                        .postImageUrl("http://localhost:8080/postImage/"+image.getOriginalFilename())
                        .build())
        );

        for(int i=0; i<postRequest.getImages().size(); i++){
            String fileName = postRequest.getImages().get(i).getOriginalFilename();
            String fullPathName = "/Users/jeong-yong-an/Desktop/DNS_backend/src/main/resources/static/postImage/" + fileName;
            postRequest.getImages().get(i).transferTo(new File(fullPathName));
        }

    }

    public void modifyPost(int postId, PostRequest postRequest) throws IOException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + postId));

        post.getPostImages().stream().forEach(i ->
            postImageRepository.deleteById(i.getPostImageId()));

        postRequest.getImages().stream().forEach(image ->
                postImageRepository.save(PostImage.builder()
                        .post(post)
                        .postImageUrl("http://localhost:8080/postImage/"+image.getOriginalFilename())
                        .build())
        );

        post.update(postRequest);


        if (postRequest.getImages() != null){
            for(int i=0; i<postRequest.getImages().size(); i++){
                String fileName = postRequest.getImages().get(i).getOriginalFilename();
                String fullPathName = "/Users/jeong-yong-an/Desktop/DNS_backend/src/main/resources/static/postImage/" + fileName;
                postRequest.getImages().get(i).transferTo(new File(fullPathName));
            }
        }



        postRepository.save(post);
    }

    public void removePost(int postId) {
        postRepository.deleteById(postId);
    }
}
