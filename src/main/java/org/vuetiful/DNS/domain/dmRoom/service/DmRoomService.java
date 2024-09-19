package org.vuetiful.DNS.domain.dmRoom.service;

import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomDetailResponse;
import org.vuetiful.DNS.domain.dmRoom.entity.DmRoom;
import org.vuetiful.DNS.domain.member.dto.MemberProfile;

public interface DmRoomService {

    DmRoomDetailResponse readDmRoomDetail(int dmRoomId, MemberProfile memberProfile, String lastMessageId, int size);

    DmRoom validateAndCreateDmRoom(int memberId, int otherMemberId);
}
