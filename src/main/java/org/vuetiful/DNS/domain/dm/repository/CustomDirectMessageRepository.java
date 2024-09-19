package org.vuetiful.DNS.domain.dm.repository;

import org.springframework.data.domain.Slice;
import org.vuetiful.DNS.domain.dm.dto.AllMessages;

public interface CustomDirectMessageRepository {

    Slice<AllMessages> findMessages(int dmRoomId, String lastMessageId, int limit);
}
