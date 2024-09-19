package org.vuetiful.DNS.domain.dm.repository;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.vuetiful.DNS.domain.dm.dto.AllMessages;
import org.vuetiful.DNS.domain.dm.entity.DirectMessage;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomDirectMessageRepositoryImpl implements CustomDirectMessageRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Slice<AllMessages> findMessages(int dmRoomId, String lastMessageId, int limit) {
        Query query = new Query()
                .addCriteria(Criteria.where("dmRoomId").is(dmRoomId))
                .with(Sort.by(Sort.Direction.DESC, "_id"))
                .limit(limit + 1);

        if (lastMessageId != null) {
            query.addCriteria(Criteria.where("_id").lt(new ObjectId(lastMessageId)));
        }

        List<DirectMessage> messages = mongoTemplate.find(query, DirectMessage.class);
        List<AllMessages> allMessages = messages.stream()
                .map(AllMessages::fromEntity)
                .collect(Collectors.toList());

        boolean hasNext = allMessages.size() > limit;
        if (hasNext) { allMessages.remove(allMessages.size() - 1); }

        return new SliceImpl<>(allMessages, PageRequest.of(0, limit), hasNext);
    }
}
