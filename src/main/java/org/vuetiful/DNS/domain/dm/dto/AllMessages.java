package org.vuetiful.DNS.domain.dm.dto;

import lombok.Builder;
import lombok.Getter;
import org.vuetiful.DNS.domain.dm.entity.DirectMessage;

import java.time.LocalDateTime;

@Getter
@Builder
public class AllMessages {

    private String id;
    private int senderId;
    private String content;
    private LocalDateTime time;

    public static AllMessages fromEntity(DirectMessage dm) {
        return AllMessages.builder()
                .id(dm.getId())
                .senderId(dm.getSenderId())
                .content(dm.getContent())
                .time(dm.getTime())
                .build();
    }
}
