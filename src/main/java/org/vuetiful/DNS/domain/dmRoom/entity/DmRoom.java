package org.vuetiful.DNS.domain.dmRoom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.vuetiful.DNS.domain.BaseEntity;
import org.vuetiful.DNS.domain.memberDmRoom.entity.MemberDmRoom;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "dmRoom")
    @ToString.Exclude
    @Builder.Default
    // TODO: @JsonIgnore 필요한가?
    private List<MemberDmRoom> memberDmRooms = new ArrayList<>();

}
