package org.vuetiful.DNS.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.vuetiful.DNS.domain.BaseEntity;
import org.vuetiful.DNS.domain.memberDmRoom.entity.MemberDmRoom;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false, length = 255)
    private String profileImageUrl;

    @Column(nullable = false, length = 10)
    @ColumnDefault("'local'")
    private String socialType;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    @Builder.Default
    // TODO: @JsonIgnore 필요한가?
    private List<MemberDmRoom> memberDmRooms = new ArrayList<>();

}
