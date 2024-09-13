package org.vuetiful.DNS.domain.post.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vuetiful.DNS.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Slice<Post> findSliceBy(Pageable pageable);
}
