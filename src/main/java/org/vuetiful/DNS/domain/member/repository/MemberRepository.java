package org.vuetiful.DNS.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vuetiful.DNS.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 회원 찾기
    Member findByEmail(String email);
}
