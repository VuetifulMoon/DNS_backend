package org.vuetiful.DNS.domain.dm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.vuetiful.DNS.domain.dm.entity.DirectMessage;

import java.util.Optional;

@Repository
public interface DirectMessageRepository extends MongoRepository<DirectMessage, String> {

    Optional<DirectMessage> findTop1ByDmRoomIdOrderByTimeDesc(Integer dmRoomId);
}
