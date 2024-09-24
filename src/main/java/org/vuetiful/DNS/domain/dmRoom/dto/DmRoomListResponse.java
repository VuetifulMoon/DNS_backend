package org.vuetiful.DNS.domain.dmRoom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DmRoomListResponse {

    private int dmRoomId;
    private int memberId;
    private String profileImageUrl;
    private String nickname;
    private String recentMessage;
    private LocalDateTime recentMessageTime;

    public DmRoomListResponse(int dmRoomId) {
        this.dmRoomId = dmRoomId;
    }

}
