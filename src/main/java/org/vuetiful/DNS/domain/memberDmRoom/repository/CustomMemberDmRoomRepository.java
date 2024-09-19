package org.vuetiful.DNS.domain.memberDmRoom.repository;

import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomListResponse;
import org.vuetiful.DNS.domain.member.dto.MemberProfile;

import java.util.List;

public interface CustomMemberDmRoomRepository {
    List<DmRoomListResponse> findRoomList(int memberId);
    MemberProfile findOtherMemberProfile(int memberId, int dmRoomId);
}
