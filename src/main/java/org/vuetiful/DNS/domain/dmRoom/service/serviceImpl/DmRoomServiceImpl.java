package org.vuetiful.DNS.domain.dmRoom.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vuetiful.DNS.domain.dm.dto.AllMessages;
import org.vuetiful.DNS.domain.dm.repository.CustomDirectMessageRepository;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomDetailResponse;
import org.vuetiful.DNS.domain.dmRoom.entity.DmRoom;
import org.vuetiful.DNS.domain.dmRoom.repository.DmRoomRepository;
import org.vuetiful.DNS.domain.dmRoom.service.DmRoomService;
import org.vuetiful.DNS.domain.member.dto.MemberProfile;
import org.vuetiful.DNS.domain.member.entity.Member;
import org.vuetiful.DNS.domain.memberDmRoom.entity.MemberDmRoom;
import org.vuetiful.DNS.domain.memberDmRoom.repository.MemberDmRoomRepository;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class DmRoomServiceImpl implements DmRoomService {

    private final CustomDirectMessageRepository customDirectMessageRepository;
    private final DmRoomRepository dmRoomRepository;
    private final MemberDmRoomRepository memberDmRoomRepository;

    @Override
    public DmRoomDetailResponse readDmRoomDetail(int dmRoomId, MemberProfile memberProfile, String lastMessageId, int size) {
        Slice<AllMessages> allMessagesSlice = customDirectMessageRepository.findMessages(dmRoomId, lastMessageId, size);

        return DmRoomDetailResponse.builder()
                .dmRoomId(dmRoomId)
                .memberProfile(memberProfile)
                .allMessages(allMessagesSlice.getContent())
                .hasNext(allMessagesSlice.hasNext())
                .build();
    }

    @Override
    public DmRoom validateAndCreateDmRoom(int memberId, int otherMemberId) {
        Integer dmRoomId = memberDmRoomRepository.findDmRoomIdByMemberIds(memberId, otherMemberId);

        if (dmRoomId == null) {
            DmRoom dmRoom = new DmRoom(new ArrayList<>());

            Member member1 = new Member(memberId);
            Member member2 = new Member(otherMemberId);

            MemberDmRoom memberDmRoom1 = new MemberDmRoom(member1, dmRoom);
            MemberDmRoom memberDmRoom2 = new MemberDmRoom(member2, dmRoom);

            dmRoom.getMemberDmRooms().add(memberDmRoom1);
            dmRoom.getMemberDmRooms().add(memberDmRoom2);

            return dmRoomRepository.save(dmRoom);
        }
        return dmRoomRepository.getReferenceById(dmRoomId);
    }
}
