package org.vuetiful.DNS.domain.dmRoom.dto;

import lombok.Builder;
import lombok.Getter;
import org.vuetiful.DNS.domain.dm.dto.AllMessages;
import org.vuetiful.DNS.domain.member.dto.MemberProfile;

import java.util.List;

@Getter
@Builder
public class DmRoomDetailResponse {

    private int dmRoomId;

    private MemberProfile memberProfile;

    private List<AllMessages> allMessages;

    private boolean hasNext;
}
