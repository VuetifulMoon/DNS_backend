package org.vuetiful.DNS.domain.dm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.vuetiful.DNS.domain.dm.dto.MessageRequest;
import org.vuetiful.DNS.domain.dm.dto.MessageResponse;
import org.vuetiful.DNS.domain.dm.service.DirectMessageService;

@RestController
@RequiredArgsConstructor
public class DirectMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final DirectMessageService directMessageService;

    @MessageMapping("/dm-message")
    @SendTo("/sub/dm")
    public MessageResponse sendMessage(MessageRequest messageRequest) {
        messagingTemplate.convertAndSend("/sub/dm/" + messageRequest.getDmRoomId(), messageRequest);

        directMessageService.saveMessage(messageRequest);

        return MessageResponse.builder()
                .content(messageRequest.getContent())
                .time(messageRequest.getTime())
                .build();
    }
}

