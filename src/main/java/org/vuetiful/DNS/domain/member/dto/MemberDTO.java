package org.vuetiful.DNS.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private int memberId;
    private String email;
    private String password;
    private String nickname;
    private String profileImageUrl;
    private String socialType;
}
