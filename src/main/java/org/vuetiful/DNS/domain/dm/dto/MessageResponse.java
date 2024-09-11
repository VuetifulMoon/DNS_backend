package org.vuetiful.DNS.domain.dm.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponse {

    private String content;
    private LocalDateTime time;

    //TODO: messageId 추가한 뒤, 메세지 삭제 기능 추가
}
