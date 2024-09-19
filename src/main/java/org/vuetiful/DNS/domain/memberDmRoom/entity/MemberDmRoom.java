package org.vuetiful.DNS.domain.memberDmRoom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.vuetiful.DNS.domain.dmRoom.entity.DmRoom;
import org.vuetiful.DNS.domain.member.entity.Member;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberDmRoom {

    @EmbeddedId
    private MemberDmRoomId memberDmRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @MapsId("dmRoomId")
    @JoinColumn(name = "dm_room_id", nullable = false)
    private DmRoom dmRoom;


    public MemberDmRoom(Member member, DmRoom dmRoom) {
        this.member = member;
        this.dmRoom = dmRoom;
        this.memberDmRoomId = new MemberDmRoomId(member.getMemberId(), dmRoom.getDmRoomId());
    }
}
