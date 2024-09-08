package org.vuetiful.DNS.domain.dmRoom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.vuetiful.DNS.domain.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class DmRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dmRoomId;

}
