package org.vuetiful.DNS.domain.dmRoom.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DmRoomListResponse {

    private Integer dmRoomId;
    private Integer memberId;
    private String profileImage;
    private String nickname;
    private String recentMessage;
    private LocalDateTime recentMessageTime;

    public DmRoomListResponse(Integer dmRoomId, Integer memberId, String profileImage, String nickname) {
        this.dmRoomId = dmRoomId;
        this.memberId = memberId;
        this.profileImage = profileImage;
        this.nickname = nickname;
    }

    public DmRoomListResponse() {
    }
}
