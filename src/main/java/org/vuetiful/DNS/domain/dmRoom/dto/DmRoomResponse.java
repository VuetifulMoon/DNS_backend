package org.vuetiful.DNS.domain.dmRoom.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DmRoomResponse {
    private int dmRoomId;
    private List<Integer> memberIds;
}
