package org.vuetiful.DNS.domain.postImage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vuetiful.DNS.domain.postImage.entity.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Integer> {
}
