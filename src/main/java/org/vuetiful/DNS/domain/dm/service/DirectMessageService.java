package org.vuetiful.DNS.domain.dm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.vuetiful.DNS.domain.dm.dto.MessageRequest;
import org.vuetiful.DNS.domain.dm.entity.DirectMessage;
import org.vuetiful.DNS.domain.dm.repository.DirectMessageRepository;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class DirectMessageService {

    private final DirectMessageRepository directMessageRepository;

    public void saveMessage(MessageRequest messageRequest) {
        DirectMessage directMessage = MessageRequest.toEntity(messageRequest);

        directMessageRepository.save(directMessage);
    }
}
