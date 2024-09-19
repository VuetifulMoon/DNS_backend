package org.vuetiful.DNS.domain.memberDmRoom.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomListResponse;
import org.vuetiful.DNS.domain.memberDmRoom.entity.MemberDmRoom;
import org.vuetiful.DNS.domain.memberDmRoom.entity.MemberDmRoomId;
import org.vuetiful.DNS.domain.memberDmRoom.repository.CustomMemberDmRoomRepository;
import org.vuetiful.DNS.domain.memberDmRoom.repository.MemberDmRoomRepository;
import org.vuetiful.DNS.global.exception.ErrorCode;
import org.vuetiful.DNS.global.exception.GlobalException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberDmRoomService {

    private final MemberDmRoomRepository memberDmRoomRepository;
    private final CustomMemberDmRoomRepository customMemberDmRoomRepository;

    public List<DmRoomListResponse> readRoomList(int memberId) {
        return customMemberDmRoomRepository.findRoomList(memberId);
    }

    public void deleteMemberDmRoom(int memberId, int dmRoomId) {
        MemberDmRoomId memberDmRoomId = new MemberDmRoomId(memberId, dmRoomId);

        try {
            MemberDmRoom memberDmRoom = memberDmRoomRepository.getReferenceById(memberDmRoomId);
            memberDmRoomRepository.delete(memberDmRoom);
        }
        catch (EntityNotFoundException e) {
            throw new GlobalException(ErrorCode.DM_ROOM_NOT_FOUND);
        }
    }
}
