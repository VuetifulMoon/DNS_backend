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
@EqualsAndHashCode
public class MemberDmRoomId implements Serializable {
    //TODO: 복합키를 정의한 클래스는 왜 직렬화가 되어있어야 하는가?
    //TODO: 복합키 설정하는 클래스는 반드시 equals()와 hashcode()를 정의해야하나?

    private Integer memberId;

    private Integer dmRoomId;
}
