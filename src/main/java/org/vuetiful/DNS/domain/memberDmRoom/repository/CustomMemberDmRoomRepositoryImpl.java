package org.vuetiful.DNS.domain.memberDmRoom.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.vuetiful.DNS.domain.dm.repository.DirectMessageRepository;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomListResponse;
import org.vuetiful.DNS.domain.dmRoom.entity.QDmRoom;
import org.vuetiful.DNS.domain.member.dto.MemberProfile;
import org.vuetiful.DNS.domain.member.entity.QMember;
import org.vuetiful.DNS.domain.memberDmRoom.entity.QMemberDmRoom;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomMemberDmRoomRepositoryImpl implements CustomMemberDmRoomRepository {
    private final JPAQueryFactory queryFactory;
    private final DirectMessageRepository directMessageRepository;

    private final QMemberDmRoom qMemberDmRoom = QMemberDmRoom.memberDmRoom;
    private final QMember qMember = QMember.member;
    private final QDmRoom qDmRoom = QDmRoom.dmRoom;

    /**
     * 사용자 memberId가 속한 모든 채팅방의 상대방 프로필과 최근 메세지 조회
     * @param memberId 사용자 memberId
     * @return dmRoomId, 상대방 프로필, 최근 메세지와 시간
     */
    public List<DmRoomListResponse> findRoomList(int memberId) {
        List<DmRoomListResponse> results = queryFactory.select(Projections.constructor(DmRoomListResponse.class,
                qDmRoom.dmRoomId))
                .from(qMemberDmRoom)
                .join(qMemberDmRoom.dmRoom, qDmRoom)
                .where(qMemberDmRoom.member.memberId.eq(memberId))
                .fetch();

        results.forEach(response -> {
            int dmRoomId = response.getDmRoomId();

            MemberProfile otherMemberProfile = findOtherMemberProfile(memberId, dmRoomId);
            response.setMemberId(otherMemberProfile.getMemberId());
            response.setProfileImageUrl(otherMemberProfile.getProfileImageUrl());
            response.setNickname(otherMemberProfile.getNickname());

            directMessageRepository.findTop1ByDmRoomIdOrderByTimeDesc(dmRoomId)
                    .ifPresentOrElse(
                            recentMessage -> {
                                response.setRecentMessage(recentMessage.getContent());
                                response.setRecentMessageTime(recentMessage.getTime());
                            },
                            () -> response.setRecentMessage("최근 메세지가 없습니다.")
                            //TODO: 에러 던지기
                    );
        });

        return results;
    }

    /**
     * 같은 dmRoomId를 가졌지만 주어진 사용자 memberId가 아닌 다른 memberId의 프로필 이미지와 닉네임 조회
     * @param memberId 사용자 memberId
     * @param dmRoomId 채팅방 dmRoomId
     * @return 상대방 프로필 (memberId, profileImageUrl, nickname)
     */
    public MemberProfile findOtherMemberProfile(int memberId, int dmRoomId) {
        return queryFactory
                .select(Projections.fields(MemberProfile.class,
                        qMember.memberId,
                        qMember.profileImageUrl,
                        qMember.nickname))
                .from(qMemberDmRoom)
                .join(qMemberDmRoom.member, qMember)
                .where(qMemberDmRoom.dmRoom.dmRoomId.eq(dmRoomId)
                        .and(qMemberDmRoom.member.memberId.ne(memberId)))
                .fetchOne();
    }
}
