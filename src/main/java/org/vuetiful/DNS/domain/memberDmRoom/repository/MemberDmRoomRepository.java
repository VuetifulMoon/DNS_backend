package org.vuetiful.DNS.domain.memberDmRoom.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.vuetiful.DNS.domain.dm.entity.DirectMessage;
import org.vuetiful.DNS.domain.dm.repository.DirectMessageRepository;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomListResponse;
import org.vuetiful.DNS.domain.dmRoom.entity.QDmRoom;
import org.vuetiful.DNS.domain.member.entity.QMember;
import org.vuetiful.DNS.domain.memberDmRoom.entity.QMemberDmRoom;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDmRoomRepository {
    private final JPAQueryFactory queryFactory;
    private final DirectMessageRepository directMessageRepository;

    public List<DmRoomListResponse> findRoomListByMemberId(Integer memberId) {

        QMemberDmRoom qMemberDmRoom = QMemberDmRoom.memberDmRoom;
        QMember qMember = QMember.member;
        QDmRoom qDmRoom = QDmRoom.dmRoom;

        List<DmRoomListResponse> results = queryFactory.select(Projections.constructor(DmRoomListResponse.class,
                qDmRoom.dmRoomId,
                qMember.memberId,
                qMember.profileImageUrl,
                qMember.nickname))
                .from(qMemberDmRoom)
                .join(qMemberDmRoom.member, qMember)
                .join(qMemberDmRoom.dmRoom, qDmRoom)
                .where(qMember.memberId.ne(memberId)
                        .and(qMemberDmRoom.dmRoom.dmRoomId.in(
                                JPAExpressions.select(qMemberDmRoom.dmRoom.dmRoomId)
                                        .from(qMemberDmRoom)
                                        .where(qMemberDmRoom.member.memberId.eq(memberId))
                        )))
                .fetch();

        for (DmRoomListResponse response : results) {
            Integer dmRoomId = response.getDmRoomId();
            Optional<DirectMessage> recentMessage = directMessageRepository.findTop1ByDmRoomIdOrderByTimeDesc(dmRoomId);

            if (recentMessage.isPresent()) {
                response.setRecentMessage(recentMessage.get().getContent());
                response.setRecentMessageTime(recentMessage.get().getTime());
            } else {
                response.setRecentMessage("최근 메세지가 없습니다.");
                //TODO: 예외 던지기
            }
        }

        return results;
    }
}
