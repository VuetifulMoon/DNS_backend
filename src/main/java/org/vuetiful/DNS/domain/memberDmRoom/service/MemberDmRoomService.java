package org.vuetiful.DNS.domain.memberDmRoom.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomListResponse;
import org.vuetiful.DNS.domain.memberDmRoom.repository.MemberDmRoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberDmRoomService {

    private final MemberDmRoomRepository memberDmRoomRepository;

    public List<DmRoomListResponse> getRoomListByMemberId(Integer memberId) {
        return memberDmRoomRepository.findRoomListByMemberId(memberId);
    }
}
