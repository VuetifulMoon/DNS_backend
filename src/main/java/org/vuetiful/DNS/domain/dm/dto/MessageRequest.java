package org.vuetiful.DNS.domain.dm.dto;

import lombok.Getter;
import lombok.Setter;
import org.vuetiful.DNS.domain.dm.entity.DirectMessage;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageRequest {

    private int dmRoomId;
    private int senderId;
    private String content;
    private LocalDateTime time;

    public static DirectMessage toEntity(MessageRequest messageRequest) {
        return DirectMessage.builder()
                .dmRoomId(messageRequest.getDmRoomId())
                .senderId(messageRequest.getSenderId())
                .content(messageRequest.getContent())
                .time(messageRequest.getTime())
                .build();
    }
}
