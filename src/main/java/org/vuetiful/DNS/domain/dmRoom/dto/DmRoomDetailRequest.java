package org.vuetiful.DNS.domain.dmRoom.dto;

import lombok.Builder;
import lombok.Getter;
import org.vuetiful.DNS.domain.member.dto.MemberProfile;

@Getter
@Builder
public class DmRoomDetailRequest {

    private int dmRoomId;
    private MemberProfile memberProfile;
}
