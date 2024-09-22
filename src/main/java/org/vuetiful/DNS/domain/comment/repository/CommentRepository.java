package org.vuetiful.DNS.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vuetiful.DNS.domain.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
