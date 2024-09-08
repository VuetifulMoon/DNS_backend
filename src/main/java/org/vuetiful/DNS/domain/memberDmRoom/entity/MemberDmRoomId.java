package org.vuetiful.DNS.domain.memberDmRoom.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberDmRoomId implements Serializable {
    //TODO: 복합키를 정의한 클래스는 왜 직렬화가 되어있어야 하는가?

    private Integer memberId;

    private Integer dmRoomId;
}
