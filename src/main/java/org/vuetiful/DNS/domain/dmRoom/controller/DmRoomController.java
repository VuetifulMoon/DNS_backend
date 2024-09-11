package org.vuetiful.DNS.domain.dmRoom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomListResponse;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomResponse;
import org.vuetiful.DNS.domain.memberDmRoom.service.MemberDmRoomService;

import java.util.List;

@RestController
@RequestMapping("/dm-rooms")
@RequiredArgsConstructor
public class DmRoomController {
    //TODO: 스프링 시큐리티 구현 이후, otherMemberId 변경

    private final MemberDmRoomService memberDmRoomService;

    // 채팅방 생성
    @PostMapping
    public ResponseEntity<DmRoomResponse> createRoom() {
        return null;
    }

    /**
     * 채팅방 리스트 조회
     * @param memberId 사용자 memberId
     * @return dmRoomId, 상대방 memberId, 상대방 프로필 이미지, 상대방 닉네임, 최근 메세지
     */
    @GetMapping
    public ResponseEntity<List<DmRoomListResponse>> getAllRoomList(Integer memberId) {
        List<DmRoomListResponse> response = memberDmRoomService.getRoomListByMemberId(memberId);

        return ResponseEntity.ok().body(response);
    }

    // 채팅방 삭제
    @DeleteMapping("/{dmRoomId}")
    public String delete(@PathVariable String dmRoomId) {
        return null;
    }

    // 채팅방 상세 조회
    @GetMapping("/{dmRoomId}")
    public String findById(@PathVariable String dmRoomId) {
        return null;
    }

    // 채팅 메세지 삭제
    @DeleteMapping("/{dmRoomId}/dm-messages/{dmMessageId}")
    public String delete(@PathVariable String dmRoomId, @PathVariable String dmMessageId) {
        return null;
    }
}
