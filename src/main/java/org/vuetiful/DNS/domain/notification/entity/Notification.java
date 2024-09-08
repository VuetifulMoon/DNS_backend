package org.vuetiful.DNS.domain.notification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.vuetiful.DNS.domain.member.entity.Member;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Column(nullable = false, length = 100)
    private String notificationContent;

    @Column
    private boolean readStatus;
}
