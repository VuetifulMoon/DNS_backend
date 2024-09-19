package org.vuetiful.DNS.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import org.vuetiful.DNS.domain.member.entity.Member;

@Getter
@Builder
public class MemberProfile {

    private int memberId;
    private String profileImageUrl;
    private String nickname;

    public MemberProfile(int memberId, String profileImageUrl, String nickname) {
        this.memberId = memberId;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
    }

    public static MemberProfile fromEntity(Member member) {
        return MemberProfile.builder()
                .memberId(member.getMemberId())
                .profileImageUrl(member.getProfileImageUrl())
                .nickname(member.getNickname())
                .build();
    }
}
