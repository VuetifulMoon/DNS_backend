package org.vuetiful.DNS.domain.dmRoom.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DmRoomListResponse {

    private int dmRoomId;
    private int memberId;
    private String profileImageUrl;
    private String nickname;
    private String recentMessage;
    private LocalDateTime recentMessageTime;

    public DmRoomListResponse(int dmRoomId, int memberId, String profileImageUrl, String nickname) {
        this.dmRoomId = dmRoomId;
        this.memberId = memberId;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
    }

    public DmRoomListResponse() {
    }
}
