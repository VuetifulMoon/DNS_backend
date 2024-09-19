package org.vuetiful.DNS.domain.memberDmRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vuetiful.DNS.domain.memberDmRoom.entity.MemberDmRoom;
import org.vuetiful.DNS.domain.memberDmRoom.entity.MemberDmRoomId;

public interface MemberDmRoomRepository extends JpaRepository<MemberDmRoom, MemberDmRoomId> {

    @Query("SELECT mdr1.dmRoom.dmRoomId " +
            "FROM MemberDmRoom mdr1 " +
            "JOIN MemberDmRoom mdr2 ON mdr1.dmRoom.dmRoomId = mdr2.dmRoom.dmRoomId " +
            "WHERE mdr1.member.memberId = :memberId AND mdr2.member.memberId = :otherMemberId")
    Integer findDmRoomIdByMemberIds(@Param("memberId") Integer memberId, @Param("otherMemberId") Integer otherMemberId);
}
