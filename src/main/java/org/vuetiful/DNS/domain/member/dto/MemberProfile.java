package org.vuetiful.DNS.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.vuetiful.DNS.domain.member.entity.Member;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfile {

    private int memberId;
    private String profileImageUrl;
    private String nickname;

    public static MemberProfile fromEntity(Member member) {
        return MemberProfile.builder()
                .memberId(member.getMemberId())
                .profileImageUrl(member.getProfileImageUrl())
                .nickname(member.getNickname())
                .build();
    }
}
