package org.vuetiful.DNS.domain.dmRoom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vuetiful.DNS.domain.dm.service.DirectMessageService;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomDetailRequest;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomDetailResponse;
import org.vuetiful.DNS.domain.dmRoom.dto.DmRoomListResponse;
import org.vuetiful.DNS.domain.dmRoom.entity.DmRoom;
import org.vuetiful.DNS.domain.dmRoom.service.DmRoomService;
import org.vuetiful.DNS.domain.member.dto.MemberProfile;
import org.vuetiful.DNS.domain.memberDmRoom.service.MemberDmRoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DmRoomController {
    //TODO: 스프링 시큐리티 구현 이후, memberId 변경

    private final MemberDmRoomService memberDmRoomService;
    private final DmRoomService dmRoomService;
    private final DirectMessageService directMessageService;

    /**
     * 채팅방 리스트 조회
     * @param memberId 사용자 memberId
     * @return dmRoomId, 상대방 memberId, 상대방 프로필 이미지, 상대방 닉네임, 최근 메세지
     */
    @GetMapping("/dm-rooms")
    public List<DmRoomListResponse> getAllRoomList(@RequestParam int memberId) {
        return memberDmRoomService.readRoomList(memberId);
    }

    /**
     * 채팅방 상세 조회 - 리스트에서 선택
     * @param dmRoomDetailRequest 선택한 채팅방 dmRoomId, 상대방 프로필
     * @param lastMessageId 마지막으로 로드된 메세지 Id
     * @param size 반환할 메시지의 최대 수
     * @return dmRoomId, 상대방 프로필, 전체 메세지
     */
    @GetMapping("/dm-rooms/{dmRoomId}")
    public DmRoomDetailResponse getMessages(@RequestParam DmRoomDetailRequest dmRoomDetailRequest,
                                            @RequestParam(required = false) String lastMessageId,
                                            @RequestParam(defaultValue = "30") int size) {
        return dmRoomService.readDmRoomDetail(
                dmRoomDetailRequest.getDmRoomId(), dmRoomDetailRequest.getMemberProfile(), lastMessageId, size);
    }

    /**
     * 채팅방 상세 조회 - 회원 지정 선택
     * @param memberProfile 선택한 회원 프로필
     * @param memberId 사용자 memberId
     * @return dmRoomId, 상대방 프로필, 전체 메세지
     */
    @GetMapping("/members/{memberId}/dm-rooms")
    public ResponseEntity<Integer> getMessagesByMemberId(@RequestBody MemberProfile memberProfile, @RequestParam int memberId) {
        DmRoom dmRoom = dmRoomService.validateAndCreateDmRoom(memberId, memberProfile.getMemberId());

        return ResponseEntity.ok(dmRoom.getDmRoomId());
    }

    /**
     * 채팅방 삭제
     * @param dmRoomId
     * @param memberId
     * @return
     */
    @DeleteMapping("/dm-rooms/{dmRoomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int dmRoomId, @RequestParam int memberId) {
        memberDmRoomService.deleteMemberDmRoom(memberId, dmRoomId);

        return ResponseEntity.noContent().build();
    }

    /**
     * 채팅 메세지 삭제
     * @param dmRoomId
     * @param messageId
     * @return
     */
    @DeleteMapping("/dm-rooms/{dmRoomId}/dm-messages/{messageId}")
    public ResponseEntity<Void> deleteMessages(@PathVariable int dmRoomId, @PathVariable String messageId) {
        directMessageService.deleteMessage(dmRoomId, messageId);

        return ResponseEntity.noContent().build();
    }
}
